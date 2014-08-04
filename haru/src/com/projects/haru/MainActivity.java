
package com.projects.haru;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fortysevendeg.swipelistview.OnItemDeleteListener;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.projects.haru.component.ActivityBase;
import com.projects.haru.datamanager.AsyncDataManager.OnDataLoadListener;
import com.projects.haru.datamanager.TaskDataManager;
import com.projects.haru.dto.TaskDto;
import com.projects.haru.ui.DayViewFooter;
import com.projects.haru.ui.DayViewTopBar;
import com.projects.haru.ui.widget.FontFitTextView;
import com.projects.haru.ui.widget.HelloAdapter;

public class MainActivity extends ActivityBase {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static int MINIMIZE_SIZE = 60;

    // private DayViewTopBar mTopBar;

    private FontFitTextView mTopBar;
    private LinearLayout mTopLayout;
    
    private View mHeader;
    private View mViewHolder;

    private SwipeListView mListView;
    private DayViewFooter mFooter;

    // yk
    private List<TaskDto> mDataList;
    private HelloAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        loadData();
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_main);
        mListView = (SwipeListView) findViewById(R.id.day_view_listview_listview);

        // mTopBar = new DayViewTopBar(this);
        // mTopBar.setData(Calendar.getInstance());
        // mTopBar.setUserActionListener(mTopBarUserActionListener);

        mTopBar = (FontFitTextView) findViewById(R.id.day_view_topbar_date);
        mTopLayout = (LinearLayout) findViewById(R.id.day_view_topbar);
        

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(
                LAYOUT_INFLATER_SERVICE);
        mHeader = inflater.inflate(R.layout.header, null);
        mViewHolder = mHeader.findViewById(R.id.placeholder);
        mListView.addHeaderView(mHeader);

        // mListView.addHeaderView(mTopBar);
        mFooter = (DayViewFooter) findViewById(R.id.activity_main_day_view_footer);
        mFooter.setUserActionListener(mFooterUserActionListener);

        mDataList = new ArrayList<TaskDto>();
        mListAdapter = new HelloAdapter(this, mDataList);
        mListAdapter.setOnDeleteItemClickListener(mOnDeleteItemClickListener);
        mListView.setAdapter(mListAdapter);
        mListView.setOnItemDeleteListener(new OnItemDeleteListener() {

            @Override
            public void onRemove(int position) {
                mDataList.remove(position);
                mListAdapter.notifyDataSetChanged();
            }
        });
        mListView.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                    int totalItemCount) {

                int resizeHeight = mViewHolder.getHeight() + mHeader.getTop();

                if (resizeHeight <= MINIMIZE_SIZE)
                    resizeHeight = MINIMIZE_SIZE;

                Log.e(TAG, "resizeHeight: " + resizeHeight);
                Log.e(TAG, "mPlaceHolder.getHeight(): " + mViewHolder.getHeight());

                LayoutParams headerLayoutParams = mTopLayout.getLayoutParams();
                headerLayoutParams.height = resizeHeight;
                mTopLayout.setLayoutParams(headerLayoutParams);

                int resizeWidth = mTopBar.getWidth();
                if (mViewHolder.getHeight() != 0)
                    resizeWidth = resizeWidth * resizeHeight / mViewHolder.getHeight() / 5;

                LayoutParams layoutParams = mTopBar.getLayoutParams();
                layoutParams.height = resizeHeight;
                mTopBar.setLayoutParams(layoutParams);
                mTopBar.refitText(mTopBar.getText().toString(), resizeWidth);
            }
        });
    }

    private void loadData() {
        TaskDataManager.getInstance().loadTaskList(mOnDataLoadListener,
                TaskDto.DATE_FORMAT.format(Calendar.getInstance().getTime()));
    }

    private OnDataLoadListener<List<TaskDto>> mOnDataLoadListener = new OnDataLoadListener<List<TaskDto>>() {

        @Override
        public void onDataLoad(List<TaskDto> data) {
            mDataList.addAll(data);
            mListAdapter.notifyDataSetChanged();
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

    OnClickListener mOnDeleteItemClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getTag() == null || !(v.getTag() instanceof Integer)) {
                return;
            }
            int position = (Integer) v.getTag();
            Log.e(TAG, "position: " + position);
            mListView.getSwipeListViewTouchListener().dismiss(position);
        }
    };

}
