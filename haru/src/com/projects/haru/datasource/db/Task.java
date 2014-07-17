package com.projects.haru.datasource.db;

import android.provider.BaseColumns;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: HongjinKim (audiosignal@gmail.com)
 * Date: 7/13/14
 * Time: 3:34 PM
 *
 * <p>HaRu 앱의 테스크 객체, 테이블 형태를 정의한 클래스
 */
@Table(name = "tasks", id = BaseColumns._ID)
public class Task extends Model {
    public static final String DATE = "date";
    public static final String TITLE = "title";
    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";
    public static final String IS_COMPLETED = "is_completed";
    public static final String COLOR = "color";
    public static final String CREATE_TIME = "create_time";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    /**
     * 테스크가 속한 날짜 파티션. YYYYMMDD 문자열
     */
    @Column(name = DATE, notNull = true, index = true)
    public String date;

    /**
     * 테스크 내용
     */
    @Column(name = TITLE, notNull = true)
    public String title;

    /**
     * 테스크 시작 시각
     */
    @Column(name = START_TIME, notNull = true)
    public Calendar startTime;

    /**
     * 테스크 종료 시각
     */
    @Column(name = END_TIME, notNull = true)
    public Calendar endTime;

    /**
     * 테스크 완료 여부
     */
    @Column(name = IS_COMPLETED, notNull = true)
    public boolean isCompleted;

    /**
     * 테스크 배경색. 16진수값 문자열
     */
    @Column(name = COLOR, notNull = true)
    public int color;

    @Column(name = CREATE_TIME, notNull = true)
    public Calendar createTime;

    void set(Task o) {
        this.date = o.date;
        this.title = o.title;
        this.startTime = o.startTime;
        this.endTime = o.endTime;
        this.isCompleted = o.isCompleted;
        this.color = o.color;
        this.createTime = o.createTime;
    }
}
