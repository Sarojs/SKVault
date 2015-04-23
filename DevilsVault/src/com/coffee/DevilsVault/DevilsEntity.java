package com.coffee.DevilsVault;

import android.os.Parcel;
import android.os.Parcelable;

public class DevilsEntity implements Parcelable {
	
	int id;
	String created_at;
	String modified_at;
	String title;
	String description;
	String email;
	String username;
	String password;
	String url;
	String note;

	// constructors
	public DevilsEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public DevilsEntity(String created_at, String modified_at, String title, String description, String email, String username
			, String password, String url, String note) {
		this.created_at= created_at;
		this.modified_at= modified_at;
		this.title= title;
		this.description= description;
		this.email = email;
		this.username= username;
		this.password= password;
		this.url= url;
		this.note= note;
	}

	// Initializer from Parcel object
	
	public DevilsEntity(Parcel parcel) {
        this.id = parcel.readInt();
        this.created_at = parcel.readString();
        this.modified_at = parcel.readString();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.email  = parcel.readString();
        this.username = parcel.readString();
        this.password = parcel.readString();
        this.url = parcel.readString();
        this.note = parcel.readString();
    }
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}

	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	/**
	 * @return the modified_at
	 */
	public String getModified_at() {
		return modified_at;
	}

	/**
	 * @param modified_at the modified_at to set
	 */
	public void setModified_at(String modified_at) {
		this.modified_at = modified_at;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/******
	 * Implement Parcelable Interface methods
	 *****/
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int arg1) {
		parcel.writeInt(id);
        parcel.writeString(created_at);
        parcel.writeString(modified_at);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(email);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(url);
        parcel.writeString(note);
	}
	
	public static Creator<DevilsEntity> CREATOR = new Creator<DevilsEntity>() {
        public DevilsEntity createFromParcel(Parcel parcel) {
            return new DevilsEntity(parcel);
        }
        public DevilsEntity[] newArray(int size) {
            return new DevilsEntity[size];
        }
    };
}
