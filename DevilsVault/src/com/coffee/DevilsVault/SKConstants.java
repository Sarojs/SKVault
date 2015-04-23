package com.coffee.DevilsVault;

/**
 * @author sarojsharma
 * 
 */

public class SKConstants{
	
	public static String APP_TAG = "DevilsEntity";
	
	public static void init(android.content.Context context){
		APP_TAG = context.getPackageName();
	}
	
	public class Tabs{
		public static final int ALL_ITEMS_TAB = 0;
		public static final int ADD_NEW_ITEMS_TAB = 1;
	}
	public class ActivityExtras{
		public static final String ENTITY_IN_ACTION = "entity_in_action";
	}
}


/*
enum Tabs {
	
	ALL_ITEMS_TAB(0), ADD_NEW_ITEMS_TAB(1);

	private int tabID;

	Tabs(int tabID) {
		this.tabID = tabID;
	}

	public int getTabID() {
		return tabID;
	}
}
*/
/*
 * enum Tabs {
 * 
 * ALL_ITEMS_TAB(0), ADD_NEW_ITEMS_TAB(1);
 * 
 * private int tabNumber;
 * 
 * private Tabs(int value){ this.tabNumber = value; }
 * 
 * private int getValues(){ return this.tabNumber; } };
 */
