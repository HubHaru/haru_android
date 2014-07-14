/**
 * 
 */
package com.projects.haru.dto;

import java.io.Serializable;

/**
 * @author zeropol2
 *
 */
public class TaskDto implements Serializable{
	private static final long serialVersionUID = -663717517283553089L;
	public long id;
	public String title;
	public String startTime;
	public String endTime;
	public int color;
	public boolean isCompleted;

}
