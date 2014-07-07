package com.projects.haru;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.Toast;

import com.projects.haru.component.ActivityBase;
import com.projects.haru.datamanager.AsyncDataLoader.OnDataLoadListener;
import com.projects.haru.datamanager.HelloDataManager;
import com.projects.haru.dto.HelloDto;
import com.projects.haru.ui.HelloListView;
import com.projects.haru.ui.HelloListView.UserActionListener;
import com.skplanet.haru.R;

public class MainActivity extends ActivityBase{
    
	private HelloListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }
    
    @Override
    protected void initLayout() {
    	setContentView(R.layout.activity_main);
    	mListView = (HelloListView)findViewById(R.id.activity_main_hello_listview);
    	mListView.setUserActionLisetner(mUserActionListener);
    }
    
    private void loadData() {
    	HelloDataManager.getInstance().loadHelloList(mOnDataLoadListener);
    }
    
    private UserActionListener mUserActionListener = new UserActionListener() {
		
		@Override
		public void goDetailPage(long id) {
			Toast.makeText(MainActivity.this, id+"의 상세페이지로 이동하자", Toast.LENGTH_SHORT).show();
			
		}
	};
	
	private OnDataLoadListener<ArrayList<HelloDto>> mOnDataLoadListener = new OnDataLoadListener<ArrayList<HelloDto>>() {
		
		@Override
		public void onDataLoad(ArrayList<HelloDto> data) {
			mListView.addList(data);
			if(null == mListView.getAdapter()) {
				mListView.setAdapter();
			} else {
				mListView.notifyDataSetChanged();
			}
		}
		
		@Override
		public void onDataLoadFail(Exception e) {
			Toast.makeText(MainActivity.this, "데이터 로드 실패", Toast.LENGTH_SHORT).show();
		}
	};

}
