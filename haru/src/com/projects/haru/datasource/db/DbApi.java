/**
 * 
 */
package com.projects.haru.datasource.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.projects.haru.datasource.db.HelloTable.HelloInfo;

/**
 * @author zeropol2
 *
 */
public class DbApi {

	private static final String DATABASE_NAME = "Haru.db";
	//1.0시작 버전
	private static final int DATABASE_ORIGIN_VERSION = 1;
	
	private DBHelper mDbHelper = null;
	private Context mContext = null;

	class DBHelper extends SQLiteOpenHelper{
		public DBHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(QuerySet.QUERY_CREATE_HELLO_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}

	}
	
	
	public DbApi(Context context) {
		mContext = context;
		mDbHelper = new DBHelper(mContext, DATABASE_NAME, null, DATABASE_ORIGIN_VERSION);
	}
	
	private static DbApi sInstance = null;

	public static DbApi getInstance(Context context) {
		if (sInstance == null) {
			synchronized (DbApi.class) { // 성능을 고려하여 if 문 안쪽에 synchronized
												// 구문을 배치
				if (sInstance == null) { // 최상위 if 구문이 asynch 문 이기 때문에 한번 더 검사해야
											// 한다.
					sInstance = new DbApi(context);
				}
			}
		}
		return sInstance;
	}
	
	public static DbApi getInstance() {
		return sInstance;
	}
	
	public synchronized long insertHello(HelloInfo info) {
		return HelloTable.insert(mDbHelper, info);
	}
	
	public synchronized HelloInfo selectHelloById(long id) {
		return HelloTable.selectById(mDbHelper, id);
	}
	
	public synchronized ArrayList<HelloInfo> selectHelloAll() {
		return HelloTable.selectAll(mDbHelper);
	}
	
	public synchronized int updateHello(long id, HelloInfo info){
		return HelloTable.update(mDbHelper, id, info);
	}
	
	public synchronized int deleteHello(long id) {
		return HelloTable.delete(mDbHelper, id);
	}

}
