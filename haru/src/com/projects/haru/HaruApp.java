/**
 * 
 */
package com.projects.haru;

import com.projects.haru.datasource.db.DbApi;
import com.projects.haru.datasource.db.HelloTable.HelloInfo;

import android.app.Application;

/**
 * @author zeropol2
 *
 */
public class HaruApp extends Application {
    
	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}
	
	private void init() {
		DbApi.getInstance(this);
	}
}
