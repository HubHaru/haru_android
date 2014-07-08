/**
 * 
 */
package com.projects.haru;


import android.app.Application;

import com.projects.haru.datasource.db.DbApi;
import com.projects.haru.datasource.db.HelloTable.HelloInfo;
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
		//초기데이터가 있어야 하니깐 일단 임시데이터 박아두자
		HelloInfo info = new HelloInfo();
		info.title = "타이틀1";
		info.content = "내용1";
		DbApi.getInstance().insertHello(info);
		info.title = "타이틀2";
		info.content = "내용2";
		DbApi.getInstance().insertHello(info);
		info.title = "타이틀3";
		info.content = "내용3";
		DbApi.getInstance().insertHello(info);
	}
}
