package com.share.music.utils;

import java.io.File;

import android.database.sqlite.SQLiteDatabase;

public class DBUtils {

	private static SQLiteDatabase db = null;

	public static SQLiteDatabase getSQLiteDataBase() {
		if (null == db) {

			String path = Constants.FILE_PATH + "/" + Constants.FILE_NAME_DB;
			File f = new File(path);
			db = SQLiteDatabase.openOrCreateDatabase(f, null);
		}

		return db;
	}

	
	public static void close(){
		
		if(null!=db){
			db.close();
		}
	}
}
