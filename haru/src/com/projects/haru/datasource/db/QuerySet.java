/**
 * 
 */
package com.projects.haru.datasource.db;


/**
 * @author zeropol2
 *
 */
public class QuerySet {

	public static final String QUERY_CREATE_HELLO_TABLE = 
			"create table "+ HelloTable.TABLE_NAME + "(" +
					HelloTable.Columns.Id.getValue() + " integer primary key autoincrement, " +
					HelloTable.Columns.Title.getValue() + " text, "+
					HelloTable.Columns.Content.getValue() + " text);";

	private static final String HELLO_CONDITION_TIMETABLE_BY_ID = " WHERE "+HelloTable.Columns.Id.getValue()+"=?";
			
	
	public static final String HELLO_QUERY_TIMETABLE_SELECT = "select * from "+HelloTable.TABLE_NAME;
	public static final String HELLO_QUERY_TIMETABLE_SELECT_BY_ID = HELLO_QUERY_TIMETABLE_SELECT + HELLO_CONDITION_TIMETABLE_BY_ID;

}
