package com.projects.haru.datamanager;

import com.projects.haru.datasource.db.Hello;
import com.projects.haru.dto.HelloDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HongjinKim (audiosignal@gmail.com)
 * Date: 7/8/14
 * Time: 4:38 AM
 */
// TODO: ActiveAndroid Model 을 사용한 AsyncLoader로 HelloDataManager를 대체할 수 있으니 적용 여부 고민할 것
public class HelloManager {
    private static HelloManager sInstance = null;

    public static HelloManager getInstance() {
        if (sInstance == null) {
            synchronized (HelloManager.class) { // 성능을 고려하여 if 문 안쪽에 synchronized
                // 구문을 배치
                if (sInstance == null) { // 최상위 if 구문이 asynch 문 이기 때문에 한번 더 검사해야
                    // 한다.
                    sInstance = new HelloManager();
                }
            }
        }
        return sInstance;
    }

    public void loadHelloList(AsyncDataLoader.OnDataLoadListener<ArrayList<HelloDto>> l) {
        new HelloListLoader(l).execute();
    }

    private class HelloListLoader extends AsyncDataLoader<ArrayList<HelloDto>> {

        public HelloListLoader(OnDataLoadListener<ArrayList<HelloDto>> l) {
            super(l);
        }

        @Override
        protected ArrayList<HelloDto> doTask() throws Exception{
            ArrayList<HelloDto> result = new ArrayList<HelloDto>();
            List<Hello> infoList = Hello.selectAll();
            for(Hello info : infoList) {
                result.add(convertToHelloDto(info));
            }
            return result;
        }

        private HelloDto convertToHelloDto(Hello from) {
            HelloDto to = new HelloDto();
            to.id = from.id;
            to.title = from.title;
            to.content = from.content;
            return to;
        }
    }
}
