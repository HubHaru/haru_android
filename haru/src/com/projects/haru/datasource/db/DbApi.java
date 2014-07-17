package com.projects.haru.datasource.db;

import com.activeandroid.query.Select;

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HongjinKim (audiosignal@gmail.com)
 * Date: 7/14/14
 * Time: 7:29 PM
 */
public class DbApi {
    public static Long insert(Task task) {
        return task.save();
    }

    public static List<Task> selectAll() {
        return new Select()
                .from(Task.class)
                .orderBy(Task.START_TIME + " ASC")
                .execute();
    }

    public static List<Task> selectByDate(String date) {
        return new Select()
                .from(Task.class)
                .where(Task.DATE + " = ?", date)
                .orderBy(Task.START_TIME + " ASC")
                .execute();
    }

    public static Task selectById(long id) {
        return Task.load(Task.class, id);
    }

    public static Task selectByCreateTime(Calendar createTime) {
        return new Select()
                .from(Task.class)
                .where(Task.CREATE_TIME + " = ?", createTime.getTimeInMillis())
                .executeSingle();
    }

    public static Long update(Task task) {
        return task.save();
    }

    public static Long update(Calendar createTime, Task newTask) {
        Task oldTask = new Select()
                .from(Task.class)
                .where(Task.CREATE_TIME + " = ?", createTime.getTimeInMillis())
                .executeSingle();

        oldTask.date = newTask.date;
        oldTask.title = newTask.title;
        oldTask.startTime = newTask.startTime;
        oldTask.endTime = newTask.endTime;
        oldTask.isCompleted = newTask.isCompleted;
        oldTask.color = newTask.color;
        oldTask.createTime = newTask.createTime;

        return oldTask.save();
    }

    public static void delete(Task task) {
        task.delete();
    }

    public static void deleteById(long id) {
        Task.delete(Task.class, id);
    }

    public static void delete(Calendar createTime) {
        Task task = new Select()
                .from(Task.class)
                .where(Task.CREATE_TIME + " = ?", createTime.getTimeInMillis())
                .executeSingle();

        task.delete();
    }
}
