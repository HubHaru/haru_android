/**
 * 
 */
package com.projects.haru.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author zeropol2
 *
 */
public class TaskDto implements Serializable{
	private static final long serialVersionUID = -663717517283553089L;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	public Long id;
	public String title;
    public String date;
	public Calendar startTime;
	public Calendar endTime;
	public int color;
	public boolean isCompleted;
    public Calendar createTime;

    @Override
    public String toString() {
        return title;
    }
}
