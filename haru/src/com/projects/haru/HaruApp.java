/**
 * 
 */
package com.projects.haru;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.projects.haru.datasource.db.DbApi;
import com.projects.haru.datasource.db.Task;

import java.util.Calendar;
import java.util.Date;


/**
 * @author zeropol2
 *
 */
public class HaruApp extends Application {
    
	@Override
	public void onCreate() {
		super.onCreate();

        ActiveAndroid.beginTransaction();
        try {
            //FIXME 일단 더미 삽입
            Task task = new Task();
            task.title = "테스트 제목1";
            task.date = Task.DATE_FORMAT.format(new Date());
            task.startTime = Calendar.getInstance();
            task.endTime = Calendar.getInstance();
            task.createTime = Calendar.getInstance();
            task.color = 0xffffffff;
            task.isCompleted = true;
            DbApi.insert(task);

             task.title = "테스트 제목2";
            task.date = Task.DATE_FORMAT.format(new Date());
            task.startTime = Calendar.getInstance();
            task.endTime = Calendar.getInstance();
            task.createTime = Calendar.getInstance();
            task.color = 0xfffffff1;
            task.isCompleted = true;
            DbApi.insert(task);

             task.title = "테스트 제목3";
            task.date = Task.DATE_FORMAT.format(new Date());
            task.startTime = Calendar.getInstance();
            task.endTime = Calendar.getInstance();
            task.createTime = Calendar.getInstance();
            task.color = 0xfffffff4;
            task.isCompleted = true;
            DbApi.insert(task);

             task.title = "테스트 제목4";
            task.date = Task.DATE_FORMAT.format(new Date());
            task.startTime = Calendar.getInstance();
            task.endTime = Calendar.getInstance();
            task.createTime = Calendar.getInstance();
            task.color = 0xfffff3ff;
            task.isCompleted = true;
            DbApi.insert(task);

            task.title = "테스트 제목5";
            task.date = Task.DATE_FORMAT.format(new Date());
            task.startTime = Calendar.getInstance();
            task.endTime = Calendar.getInstance();
            task.createTime = Calendar.getInstance();
            task.color = 0xfff3ffff;
            task.isCompleted = true;
            DbApi.insert(task);

             task.title = "테스트 제목6";
            task.date = Task.DATE_FORMAT.format(new Date());
            task.startTime = Calendar.getInstance();
            task.endTime = Calendar.getInstance();
            task.createTime = Calendar.getInstance();
            task.color = 0xf2ffffff;
            task.isCompleted = true;
            DbApi.insert(task);
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
	}
}
