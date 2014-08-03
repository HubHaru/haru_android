/**
 * 
 */
package com.projects.haru.datamanager;

import com.projects.haru.datamanager.AsyncDataManager.OnDataLoadListener;
import com.projects.haru.datasource.db.DbApi;
import com.projects.haru.datasource.db.Task;
import com.projects.haru.dto.TaskDto;

import java.util.ArrayList;
import java.util.List;

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
	
	public void loadTaskList(OnDataLoadListener<List<TaskDto>> l, String date) {
		new TaskListLoader(l).execute(date);
	}

    private class TaskListLoader extends AsyncDataManager<String, List<TaskDto>> {
		public TaskListLoader(OnDataLoadListener<List<TaskDto>> l) {
			super(l);
		}

		@Override
		protected List<TaskDto> doTask(String... dates) throws IllegalArgumentException {
			if (dates.length != 1) {
                throw new IllegalArgumentException();
            }

            List<Task> taskList = DbApi.selectByDate(dates[0]);
//            List<TaskDto> taskDtoList = new ArrayList<TaskDto>(taskList.size());
//            for (Task task : taskList) {
//                taskDtoList.add(convertToTaskDto(task));
//            }   
            
            List<TaskDto> taskDtoList = new ArrayList<TaskDto>();
            for (int i=0; i<10; i++) {
                Task task = taskList.get(0);
                task.title = "테스트 제목" + (i+1);
                taskDtoList.add(convertToTaskDto(task));
            }

            return taskDtoList;
		}

        private TaskDto convertToTaskDto(Task task) {
            TaskDto taskDto = new TaskDto();
            taskDto.id =            task.getId();
            taskDto.date =          task.date;
            taskDto.title =         task.title;
            taskDto.startTime =     task.startTime;
            taskDto.endTime =       task.endTime;
            taskDto.isCompleted =   task.isCompleted;
            taskDto.color =         task.color;
            taskDto.createTime =    task.createTime;
            return taskDto;
        }
	}

    public void writeTask(OnDataLoadListener<Long> l, TaskDto taskDto) {
        new TaskWriter(l).execute(taskDto);
    }

    private class TaskWriter extends AsyncDataManager<TaskDto, Long> {
        public TaskWriter(OnDataLoadListener<Long> l) {
            super(l);
        }

        @Override
        protected Long doTask(TaskDto... taskDto) throws IllegalArgumentException {
            if (taskDto.length != 1) {
                throw new IllegalArgumentException();
            }
            return DbApi.insert(convertToTask(taskDto[0]));
        }

        private Task convertToTask(TaskDto taskDto) {
            Task task = new Task();
            task.date = taskDto.date;
            task.title = taskDto.title;
            task.startTime = taskDto.startTime;
            task.endTime = taskDto.endTime;
            task.isCompleted = taskDto.isCompleted;
            task.color = taskDto.color;
            task.createTime = taskDto.createTime;

            return task;
        }
    }

    public void modifyTask(OnDataLoadListener<Long> l, TaskDto taskDto) {
        new TaskModifier(l).execute(taskDto);
    }

    private class TaskModifier extends AsyncDataManager<TaskDto, Long> {
        public TaskModifier(OnDataLoadListener<Long> l) {
            super(l);
        }

        @Override
        protected Long doTask(TaskDto... taskDto) throws IllegalArgumentException {
            if (taskDto.length != 1) {
                throw new IllegalArgumentException();
            }
            return DbApi.update(taskDto[0].createTime, convertToTask(taskDto[0]));
        }
        
        private Task convertToTask(TaskDto taskDto) {
            Task task = new Task();
            task.date = taskDto.date;
            task.title = taskDto.title;
            task.startTime = taskDto.startTime;
            task.endTime = taskDto.endTime;
            task.isCompleted = taskDto.isCompleted;
            task.color = taskDto.color;
            task.createTime = taskDto.createTime;
            return task;
        }
    }
}
