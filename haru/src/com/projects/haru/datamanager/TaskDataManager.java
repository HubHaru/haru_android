/**
 * 
 */
package com.projects.haru.datamanager;

import java.util.ArrayList;
import java.util.Calendar;

import com.projects.haru.datamanager.AsyncDataLoader.OnDataLoadListener;
import com.projects.haru.dto.TaskDto;

/**
 * @author zeropol2
 *
 */
public class TaskDataManager {

	private static TaskDataManager sInstance = null;

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
	
	public void loadTaskList(OnDataLoadListener<ArrayList<TaskDto>> l, Calendar cal) {
		new TaskListLoader(l, cal).execute();
	}
	
    
    private class TaskListLoader extends AsyncDataLoader<ArrayList<TaskDto>> {
		private Calendar cal;
		public TaskListLoader(OnDataLoadListener<ArrayList<TaskDto>> l, Calendar cal) {
			super(l);
			this.cal = cal;
		}
//		@Override
//        protected List<Task> doTask(Calendar... cals) throws IllegalArgumentException {
//            if (cals.length == 1) {
//                return Task.getByDate(cals[0]);
//            } else {
//                throw new IllegalArgumentException("파라미터 개수가 1개가 아님");
//            }
//        }

		@Override
		protected ArrayList<TaskDto> doTask() throws Exception{
			//TODO 홍진이형이 DbApi만들어주면 실데이터로 교체하자
			ArrayList<TaskDto> result = new ArrayList<TaskDto>();
			for(int i=0;i<30;i++) {
				TaskDto dto = new TaskDto();
				dto.id = i;
				dto.title ="해야할일"+i;
				dto.startTime = String.format("%02d:%02d",cal.get(Calendar.HOUR_OF_DAY)+i,cal.get(Calendar.MINUTE));
				dto.endTime = String.format("%02d:%02d",cal.get(Calendar.HOUR_OF_DAY)+i+1,cal.get(Calendar.MINUTE));
				dto.color = 0x123456;
				dto.isCompleted = false;
				result.add(dto);
			}
			return result;
		}
	}

//    /**
//     * 테스크 DB insert 메소드
//     * @param task DB에 추가할 테스크
//     */
//    public void insertTask(Task task) {
//        // AA는 insert, update 가 똑같음
//        task.save();
//    }
//
//    /**
//     * 테스크 DB update 메소드. task의 tId와 동일한 tId를 가진 DB의 테스크를 task로 업데이트
//     * @param task DB에 업데이트할 테스크
//     */
//    public void updateTask(Task task) {
//        // AA는 insert, update 가 똑같음
//        task.save();
//    }
}
