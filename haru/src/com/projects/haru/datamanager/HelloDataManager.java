/**
 * 
 */
package com.projects.haru.datamanager;

import java.util.ArrayList;

import com.projects.haru.datamanager.AsyncDataLoader.OnDataLoadListener;
import com.projects.haru.datasource.db.DbApi;
import com.projects.haru.datasource.db.HelloTable.HelloInfo;
import com.projects.haru.dto.HelloDto;

/**
 * @author zeropol2
 *
 */
public class HelloDataManager {

	private static HelloDataManager sInstance = null;

	public static HelloDataManager getInstance() {
		if (sInstance == null) {
			synchronized (HelloDataManager.class) { // 성능을 고려하여 if 문 안쪽에 synchronized
												// 구문을 배치
				if (sInstance == null) { // 최상위 if 구문이 asynch 문 이기 때문에 한번 더 검사해야
											// 한다.
					sInstance = new HelloDataManager();
				}
			}
		}
		return sInstance;
	}
	
	public void loadHelloList(OnDataLoadListener<ArrayList<HelloDto>> l) {
		new HelloListLoader(l).execute();
	}
	
	private class HelloListLoader extends AsyncDataLoader<ArrayList<HelloDto>> {
		
		public HelloListLoader(OnDataLoadListener<ArrayList<HelloDto>> l) {
			super(l);
		}

		@Override
		protected ArrayList<HelloDto> doTask() throws Exception{
			ArrayList<HelloDto> result = new ArrayList<HelloDto>();
			ArrayList<HelloInfo> infoList = DbApi.getInstance().selectHelloAll();
			for(HelloInfo info : infoList) {
				result.add(convertToHelloDto(info));
			}
			return result;
		}
		
		private HelloDto convertToHelloDto(HelloInfo from) {
			HelloDto to = new HelloDto();
			to.id = from.id;
			to.title = from.title;
			to.content = from.content;
			return to;
		}
	}
}
