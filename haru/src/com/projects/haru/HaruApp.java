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

            Task task1 = new Task();
            task1.title = "테스트 제목2";
            task1.date = Task.DATE_FORMAT.format(new Date());
            task1.startTime = Calendar.getInstance();
            task1.endTime = Calendar.getInstance();
            task1.createTime = Calendar.getInstance();
            task1.color = 0xfffffff1;
            task1.isCompleted = true;
            DbApi.insert(task1);

            Task task2 = new Task();
            task2.title = "테스트 제목3";
            task2.date = Task.DATE_FORMAT.format(new Date());
            task2.startTime = Calendar.getInstance();
            task2.endTime = Calendar.getInstance();
            task2.createTime = Calendar.getInstance();
            task2.color = 0xfffffff4;
            task2.isCompleted = true;
            DbApi.insert(task2);

            Task task3 = new Task();
            task3.title = "테스트 제목4";
            task3.date = Task.DATE_FORMAT.format(new Date());
            task3.startTime = Calendar.getInstance();
            task3.endTime = Calendar.getInstance();
            task3.createTime = Calendar.getInstance();
            task3.color = 0xfffff3ff;
            task3.isCompleted = true;
            DbApi.insert(task3);

            Task task4 = new Task();
            task4.title = "테스트 제목5";
            task4.date = Task.DATE_FORMAT.format(new Date());
            task4.startTime = Calendar.getInstance();
            task4.endTime = Calendar.getInstance();
            task4.createTime = Calendar.getInstance();
            task4.color = 0xfff3ffff;
            task4.isCompleted = true;
            DbApi.insert(task4);

            Task task5 = new Task();
            task5.title = "테스트 제목6";
            task5.date = Task.DATE_FORMAT.format(new Date());
            task5.startTime = Calendar.getInstance();
            task5.endTime = Calendar.getInstance();
            task5.createTime = Calendar.getInstance();
            task5.color = 0xf2ffffff;
            task5.isCompleted = true;
            DbApi.insert(task5);
            ActiveAndroid.setTransactionSuccessful();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
	}
}
