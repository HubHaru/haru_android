/**
 * 
 */
package com.projects.haru.datasource.db;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.projects.haru.datasource.db.DbApi.DBHelper;


/**
 * @author zeropol2
 *
 */
public class HelloTable {

	static final String TABLE_NAME = "hello";
	static enum Columns {
		Id(BaseColumns._ID),
		Title("title"),
		Content("content");
		
		String val;
		private Columns(String val) {
			this.val = val;
		}
		String getValue() {
			return this.val;
		}
	}
	
	public static class HelloInfo implements Serializable{
		private static final long serialVersionUID = -6732832463383594250L;
		
		public long id = -1;
		public String title;
		public String content;
		
		ContentValues getContentValues() {
			ContentValues contentValues = new ContentValues();
//			contentValues.put(Columns.Id.getValue(), id);
			contentValues.put(Columns.Title.getValue(), title);
			contentValues.put(Columns.Content.getValue(), content);
			return contentValues;
		}
		
		void setColumnValues(Columns key, String value) {
			if(Columns.Id.equals(key)) {
				this.id = Long.valueOf(value);
			} else if(Columns.Title.equals(key)) {
				this.title = value;
			} else if(Columns.Content.equals(key)) {
				this.content = value;
			} 
		}
	}
	
	static synchronized long insert(DBHelper dbHelper, HelloInfo info) {
		long result = -1;
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		if(db != null){
			ContentValues values = info.getContentValues();
			result = db.insert(TABLE_NAME, null, values);
			db.close();
		}
		return result;
	}
	
	static synchronized HelloInfo selectById(DBHelper dbHelper, long id) {
		HelloInfo result = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		if(db != null){
			String[] args = {String.valueOf(id)};
			Cursor cursor = db.rawQuery(QuerySet.HELLO_QUERY_TIMETABLE_SELECT_BY_ID, args);
			if(cursor.moveToNext()){
				result = new HelloInfo();
				for (Columns col : Columns.values()) {
					int colIdx = cursor.getColumnIndex(col.getValue());
					String value = cursor.getString(colIdx);
					result.setColumnValues(col, value);
				}
			}
			if( cursor != null){
				cursor.close();
			}
			db.close();
		}
		return result;
	}
	
	static synchronized ArrayList<HelloInfo> selectAll(DBHelper dbHelper) {
		ArrayList<HelloInfo> result = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		if(db != null){
			Cursor cursor = db.rawQuery(QuerySet.HELLO_QUERY_TIMETABLE_SELECT, null);
			result = new ArrayList<HelloInfo>();
			while(cursor.moveToNext()){
				HelloInfo info = new HelloInfo();
				for (Columns col : Columns.values()) {
					int colIdx = cursor.getColumnIndex(col.getValue());
					String value = cursor.getString(colIdx);
					info.setColumnValues(col, value);
				}
				result.add(info);
			}
			if( cursor != null){
				cursor.close();
			}
			db.close();
		}
		return result;
	}
	
	static synchronized int update(DBHelper dbHelper, long id, HelloInfo info){
		int result = -1;
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		if (db != null) {

			ContentValues values = info.getContentValues();
			String[] args = { String.valueOf(id) };
			result = db.update(TABLE_NAME, values, Columns.Id.getValue() + "=?", args);
			db.close();
		}
		
		return result;
	}
	
	static synchronized int delete(DBHelper dbHelper, long id){
		int result = -1;
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (db != null) {
			String[] args = { String.valueOf(id) };
			result = db.delete(TABLE_NAME, Columns.Id.getValue() + "=?", args);
			db.close();
		}
		return result;
	}

}
