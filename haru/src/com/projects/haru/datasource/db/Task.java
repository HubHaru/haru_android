package com.projects.haru.datasource.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: HongjinKim (audiosignal@gmail.com)
 * Date: 7/13/14
 * Time: 3:34 PM
 *
 * <p>HaRu 앱의 테스크 객체, 테이블 형태를 정의한 클래스
 *
 * <p>ActiveAndroid를 사용하므로 HaruTask는 별도의 insert, update, delete를 사용하는 대신 AA의 save, delete를 활용한다.
 */
@Table(name = "tasks", id = "task_id")
public class Task extends Model {
//    public Task() {
//        super();
//        tId = UUID.randomUUID().toString();
//    }
//
//    /**
//     * 테스크 자연키
//     */
//    @Column(name = "task_id", unique = true, notNull = true, index = true)
//    private String tId;
//
//    /**
//     * 테스크가 속한 날짜 파티션
//     */
//    @Column(name = "partition_date", notNull = true, index = true)
//    public Calendar partitionDate;
//
//    /**
//     * 테스크 내용
//     */
//    @Column(name = "contents", notNull = true)
//    public String Contents;
//
//    /**
//     * 테스크 시작 시각
//     */
//    @Column(name = "begin_time", notNull = true)
//    public Calendar beginTime;
//
//    /**
//     * 테스크 종료 시각
//     */
//    @Column(name = "end_time", notNull = true)
//    public Calendar endTime;
//
//    /**
//     * 테스크 완료 여부
//     */
//    @Column(name = "is_completed", notNull = true)
//    public Boolean isCompleted;
//
//    /**
//     * 테스크 배경색. 16진수값 문자열
//     */
//    @Column(name = "color", notNull = true)
//    public String color;
//
//    /**
//     *
//     * @param someDate 의 HaruTasks를 불러옴
//     * @return beginTime 순으로 정렬된, someDate에 속하는 Task 리스트
//     */
//    public static List<Task> getByDate(Calendar someDate) {
//        // AA TypeSerializer가 아이러니하게도 Select에 대해서는 변환해주지 않으므로 셀프 변환
//        return new Select()
//                .from(Task.class)
//                .where("partition_date = ?", someDate.getTimeInMillis())
//                .orderBy("begin_time ASC")
//                .execute();
//    }
}
