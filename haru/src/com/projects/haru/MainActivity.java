package com.projects.haru;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.projects.haru.component.ActivityBase;
import com.projects.haru.datamanager.AsyncDataLoader.OnDataLoadListener;
import com.projects.haru.datamanager.TaskDataManager;
import com.projects.haru.dto.TaskDto;
import com.projects.haru.ui.DayViewFooter;
import com.projects.haru.ui.DayViewListView;
import com.projects.haru.ui.DayViewListView.OnScrollDirectionListener;
import com.projects.haru.ui.DayViewListView.UserActionListener;
import com.projects.haru.ui.DayViewTopBar;

public class MainActivity extends ActivityBase{
    
	private DayViewTopBar mTopBar;
	private DayViewListView mListView;
	private DayViewFooter mFooter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }
    
    @Override
    protected void initLayout() {
    	setContentView(R.layout.activity_main);
    	mListView = (DayViewListView)findViewById(R.id.activity_main_day_view_listview);
    	mListView.setUserActionLisetner(mUserActionListener);
    	mTopBar = new DayViewTopBar(this);
    	mTopBar.setData(Calendar.getInstance());
    	mTopBar.setUserActionListener(mTopBarUserActionListener);
    	mListView.addParallaxedHeaderView(mTopBar);
    	mFooter = (DayViewFooter)findViewById(R.id.activity_main_day_view_footer);
    	mFooter.setUserActionListener(mFooterUserActionListener);
    	mListView.setOnScrollDirectionListener(mOnScrollDirectionListener);
    }
    
    private void loadData() {
    	TaskDataManager.getInstance().loadTaskList(mOnDataLoadListener, Calendar.getInstance());
    }
    
    private UserActionListener mUserActionListener = new UserActionListener() {
		
		@Override
		public void goDetailPage(long id) {
			Toast.makeText(MainActivity.this, id+"의 상세페이지로 이동하자", Toast.LENGTH_SHORT).show();
			
		}
	};

    private OnDataLoadListener<ArrayList<TaskDto>> mOnDataLoadListener = new OnDataLoadListener<ArrayList<TaskDto>>() {
		
		@Override
		public void onDataLoad(ArrayList<TaskDto> data) {
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
	
	private DayViewTopBar.UserActionListener mTopBarUserActionListener = new DayViewTopBar.UserActionListener() {
		
		@Override
		public void goWeekView(Calendar cal) {
			Toast.makeText(MainActivity.this, "주별 보기로 이동할겁니다만,,크큭", Toast.LENGTH_SHORT).show();
		}
	};
	
	private DayViewFooter.UserActionListener mFooterUserActionListener = new DayViewFooter.UserActionListener() {
		
		@Override
		public void goSetting() {
			Toast.makeText(MainActivity.this, "설정 페이지로 이동할겁니다만,,크큭", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public void goAdd() {
			Toast.makeText(MainActivity.this, "추가 페이지로 이동할겁니다만,,크큭", Toast.LENGTH_SHORT).show();
		}
	};
	
	private DayViewListView.OnScrollDirectionListener mOnScrollDirectionListener = new OnScrollDirectionListener() {
		
		@Override
		public void onScrollUp() {
			if(View.GONE == mFooter.getVisibility()) {
				mFooter.setVisibility(View.VISIBLE);
				Animation fadeInAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
				mFooter.startAnimation(fadeInAnim);
			}
		}
		
		@Override
		public void onScrollDown() {
			if(View.VISIBLE == mFooter.getVisibility()) {
				Animation fadeOutAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
				mFooter.startAnimation(fadeOutAnim);
				mFooter.setVisibility(View.GONE);
			}
		}
		
		@Override
		public void onScrollStop() {
			if(View.GONE == mFooter.getVisibility()) {
				mFooter.setVisibility(View.VISIBLE);
				Animation fadeInAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
				mFooter.startAnimation(fadeInAnim);
			}
		}
	};

}
