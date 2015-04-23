package com.coffee.DevilsVault;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.saroj.SKEncryption.AES128;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Singleton class object
	public static DatabaseHelper singletonDBHandler;

	// AES128 Encryptor object
	private AES128 cryptor;

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DevilsDatabse";

	// Table Names
	private static final String TABLE_VAULT = "Vault";
	private static final String TABLE_VAULT_MASTER = "VAULT_MASTER";
	
	// Common column names TABLE_VAULT
	private static final String KEY_ID = "id";
	private static final String KEY_CREATED_AT = "created_at";
	private static final String KEY_MODIFIED_AT = "modified_at";
	private static final String KEY_TITLE = "title";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_URL = "url";
	private static final String KEY_NOTE = "note";
	private static final String CREATE_TABLE_VAULT = "CREATE TABLE "
			+ TABLE_VAULT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_CREATED_AT + " DATETIME," + KEY_MODIFIED_AT + " DATETIME,"
			+ KEY_TITLE + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_EMAIL
			+ " TEXT," + KEY_USERNAME + " TEXT," + KEY_PASSWORD + " TEXT,"
			+ KEY_URL + " TEXT," + KEY_NOTE + " TEXT" + ")";
	
	// Common column names TABLE_VAULT_MASTER	
	private static final String KEY_IDED = "Ided";
	private static final String KEY_MASTER = "Master";
	private static final String KEY_SLAVE = "Slave";
	private static final String CREATE_TABLE_VAULT_MASTER = "CREATE TABLE "
			+ TABLE_VAULT + "("+ KEY_IDED + " TEXT" + KEY_MASTER + " TEXT" + KEY_SLAVE + " TEXT" +")";
	
	
	public static DatabaseHelper setupSharedInstance(Context context,
			String secretKey) throws Exception {
		if (singletonDBHandler != null) {
			throw new RuntimeException(
					"singletonDBHandler was already initialized. Trying to re-initialize. "
							+ "Check for repeatation of 'setupSharedInstance' method call.");
		}
		singletonDBHandler = new DatabaseHelper(context, secretKey);
		return singletonDBHandler;
	}

	public static DatabaseHelper sharedInstance() {
		if (singletonDBHandler == null) {
			throw new RuntimeException(
					"singletonDBHandler is not initialized yet. "
							+ "Please initialize by calling 'setupSharedInstance' method first.");
		}
		return singletonDBHandler;
	}

	public DatabaseHelper(Context context, String secretKey) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		try {
			cryptor = new AES128(secretKey);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e(LOG,
					"DatabaseHelper constructor failed to initialize 'cryptor', reason:"
							+ e.getLocalizedMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
		db.execSQL(CREATE_TABLE_VAULT);
		db.execSQL(CREATE_TABLE_VAULT_MASTER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VAULT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VAULT_MASTER);
		onCreate(db);
	}

	public void demo(String plain) {
		try {
			String encoded = cryptor.encrypt(plain);
			String decoded = cryptor.decrypt(encoded);
			Log.e(LOG, encoded);
			Log.e(LOG, decoded);

		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Creating an Entity
	 * 
	 * @return id of newly created entity
	 * @param id
	 *            the id to set
	 */
	public long createDevilsEntity(DevilsEntity entity) {

		SQLiteDatabase db = this.getWritableDatabase();

		long todo_id = Integer.MIN_VALUE;

		ContentValues values = new ContentValues();

		long timeInterval = new Date().getTime();
		values.put(KEY_CREATED_AT, ""+timeInterval);
		values.put(KEY_MODIFIED_AT, ""+timeInterval);

		try {
			String title = cryptor.encrypt(entity.getTitle());
			String description = cryptor.encrypt(entity.getDescription());
			String email = cryptor.encrypt(entity.getEmail());
			String username = cryptor.encrypt(entity.getUsername());
			String password = cryptor.encrypt(entity.getPassword());
			String url = cryptor.encrypt(entity.getUrl());
			String note = cryptor.encrypt(entity.getNote());

			values.put(KEY_TITLE, title);
			values.put(KEY_DESCRIPTION, description);
			values.put(KEY_EMAIL, email);
			values.put(KEY_USERNAME, username);
			values.put(KEY_PASSWORD, password);
			values.put(KEY_URL, url);
			values.put(KEY_NOTE, note);

			// insert row
			todo_id = db.insert(TABLE_VAULT, null, values);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return todo_id;
	}

	/**
	 * Creating an Entity
	 * 
	 * @return boolean represnting if update was successful.
	 * @param DevilsEntity
	 */
	public boolean updateDevilsEntity(DevilsEntity entity) {

		ContentValues values = new ContentValues();
		int isSuccess = 0;
		
		try {
			String title = cryptor.encrypt(entity.getTitle());
			String description = cryptor.encrypt(entity.getDescription());
			String email = cryptor.encrypt(entity.getEmail());
			String username = cryptor.encrypt(entity.getUsername());
			String password = cryptor.encrypt(entity.getPassword());
			String url = cryptor.encrypt(entity.getUrl());
			String note = cryptor.encrypt(entity.getNote());

			values.put(KEY_TITLE, title);
			values.put(KEY_DESCRIPTION, description);
			values.put(KEY_EMAIL, email);
			values.put(KEY_USERNAME, username);
			values.put(KEY_PASSWORD, password);
			values.put(KEY_URL, url);
			values.put(KEY_NOTE, note);

			long timeInterval = new Date().getTime();
			values.put(KEY_MODIFIED_AT, timeInterval);
			
			// Update row
			String selection = KEY_ID + " LIKE ?"; // where ID column = rowId (that is, selectionArgs)
		    String[] selectionArgs = { String.valueOf(entity.id) };
		    
		    SQLiteDatabase db = this.getWritableDatabase();
		    isSuccess = db.update(TABLE_VAULT, values, selection, selectionArgs);
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (isSuccess!=0);
	}
	
	/**
	 * @return get DevilsEntity matching given entityID;
	 * @param id
	 *            the id to set
	 */
	public DevilsEntity getDevilsEntity(long entity_id) {

		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_VAULT + " WHERE "
				+ KEY_ID + " = " + entity_id;

		Log.e(LOG, selectQuery);

		Cursor c = db.rawQuery(selectQuery, null);

		if (c != null)
			c.moveToFirst();

		DevilsEntity de = new DevilsEntity();

		try {
			de.setId(c.getInt(c.getColumnIndex(KEY_ID)));
			de.setCreated_at((c.getString(c.getColumnIndex(KEY_CREATED_AT))));
			de.setModified_at((c.getString(c.getColumnIndex(KEY_MODIFIED_AT))));
			de.setTitle(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_TITLE))));
			de.setDescription(cryptor.decrypt(c.getString(c
					.getColumnIndex(KEY_DESCRIPTION))));
			de.setEmail(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_EMAIL))));
			de.setUsername(cryptor.decrypt(c.getString(c
					.getColumnIndex(KEY_USERNAME))));
			de.setPassword(cryptor.decrypt(c.getString(c
					.getColumnIndex(KEY_PASSWORD))));
			de.setUrl(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_URL))));
			de.setNote(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_NOTE))));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			de = null;
			e.printStackTrace();
		}

		return de;
	}

	/**
	 * getting all DevilsEntity
	 * */
	public List<DevilsEntity> getAllDevilsEntities() {
		List<DevilsEntity> entities = new java.util.ArrayList<DevilsEntity>();
		String selectQuery = "SELECT  * FROM " + TABLE_VAULT +" ORDER BY "+ KEY_MODIFIED_AT + " DESC";

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		try {

			// looping through all rows and adding to list
			if (c.moveToFirst()) {
				do {
					DevilsEntity de = new DevilsEntity();
					de.setId(c.getInt(c.getColumnIndex(KEY_ID)));
					de.setCreated_at((c.getString(c.getColumnIndex(KEY_CREATED_AT))));
					de.setModified_at((c.getString(c.getColumnIndex(KEY_MODIFIED_AT))));
					de.setTitle(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_TITLE))));
					de.setDescription(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_DESCRIPTION))));
					de.setEmail(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_EMAIL))));
					de.setUsername(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_USERNAME))));
					de.setPassword(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_PASSWORD))));
					de.setUrl(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_URL))));
					de.setNote(cryptor.decrypt(c.getString(c.getColumnIndex(KEY_NOTE))));
					// adding to list
					entities.add(de);

				} while (c.moveToNext());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entities;
	}

	/**
	 * Delete all Items
	 */
	public boolean deleteAllItems() {

		SQLiteDatabase db = this.getReadableDatabase();

		// String deleteQuery = "DELETE  * FROM " + TABLE_VAULT ;
		// Log.e(LOG, deleteQuery);
		// Cursor c = db.rawQuery(deleteQuery, null);

		db.delete(TABLE_VAULT, null, null);

		return true;
	}
	
	/**
	 * Delete the given entity from database.
	 */
	public boolean deleteEntity(DevilsEntity entity) {

		int isSuccess = 0;
		
		try {
			
			// Update row
			String selection = KEY_ID + " LIKE ?"; // where ID column = rowId (that is, selectionArgs)
		    String[] selectionArgs = { String.valueOf(entity.id) };
		    
		    SQLiteDatabase db = this.getWritableDatabase();
		    isSuccess = db.delete(TABLE_VAULT, selection, selectionArgs);
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (isSuccess!=0);
	}
}
