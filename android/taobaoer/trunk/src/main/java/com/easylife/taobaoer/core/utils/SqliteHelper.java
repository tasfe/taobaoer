package com.easylife.taobaoer.core.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteHelper extends SQLiteOpenHelper {
	private String createtableSQL="create table if not exists product(" +// 
			"twitter_id integer primary key," +
			"title varchar(20)," +
			"twitter_goods_id integer," +
			"pic_url varchar(100)," +
			"j_pic_url varchar(300)," +
			"q_pic_url varchar(300)," +
			"pic_width integer," +
			"pic_height integer)";
	
	public SqliteHelper(Context context, String name, CursorFactory factory,int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	/**
	 * ������
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(createtableSQL);
		 Log.e("Database" ,"onCreate" );
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
