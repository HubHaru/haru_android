package com.projects.haru.datamanager;

import com.projects.haru.datasource.db.Task;

import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HongjinKim (audiosignal@gmail.com)
 * Date: 7/13/14
 * Time: 4:48 PM
 */
public class TaskDataManager {
    private static TaskDataManager sInstance = null;

    /**
     * 싱글톤 패턴 TaskDataManager 생성자
     * @return 유일한 TaskDataManager 인스턴스를 반환
     */
    public static TaskDataManager getInstance() {
        if (sInstance == null) {
            synchronized (TaskDataManager.class) { // 성능을 고려하여 if 문 안쪽에 synchronized
                // 구문을 배치
                if (sInstance == null) { // 최상위 if 구문이 asynch 문 이기 때문에 한번 더 검사해야
                    // 한다.
                    sInstance = new TaskDataManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 일별 테스크 리스트를 백그라운드에서 로드하는 메소드
     * @param listener 스레드 실행 후 호출될 이벤트 리스너
     * @param cal 의 테스크 리스트를 백그라운드에서 로드
     */
    public void loadTaskList(AsyncDataLoader.OnDataLoadListener<List<Task>> listener, Calendar cal) {
        new HaruTaskListLoader(listener).execute(cal);
    }

    /**
     * AsncDataLoader의 onPostExecute
     */
    private class HaruTaskListLoader extends AsyncDataLoader<Calendar, List<Task>> {
        public HaruTaskListLoader(AsyncDataLoader.OnDataLoadListener<List<Task>> listener) {
            super(listener);
        }

        /**
         *
         * @param cals [0]의 테스크 리스트를 백그라운드에서 로드
         * @return 로드된 테스크 리스트
         * @throws RuntimeException cals 개수가 1 개가 아닐 경우 발생
         */
        @Override
        protected List<Task> doTask(Calendar... cals) throws IllegalArgumentException {
            if (cals.length == 1) {
                return Task.getByDate(cals[0]);
            } else {
                throw new IllegalArgumentException("파라미터 개수가 1개가 아님");
            }
        }
    }

    /**
     * 테스크 DB insert 메소드
     * @param task DB에 추가할 테스크
     */
    public void insertTask(Task task) {
        // AA는 insert, update 가 똑같음
        task.save();
    }

    /**
     * 테스크 DB update 메소드. task의 tId와 동일한 tId를 가진 DB의 테스크를 task로 업데이트
     * @param task DB에 업데이트할 테스크
     */
    public void updateTask(Task task) {
        // AA는 insert, update 가 똑같음
        task.save();
    }
}
