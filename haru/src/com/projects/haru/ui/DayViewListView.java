///**
// * 
// */
//
//package com.projects.haru.ui;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.LinkedList;
//import java.util.List;
//
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AbsListView;
//import android.widget.AbsListView.OnScrollListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.BaseAdapter;
//import android.widget.LinearLayout;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//
//import com.projects.haru.R;
//import com.projects.haru.dto.TaskDto;
//import com.projects.haru.ui.widget.CustomTextView;
//
///**
// * @author zeropol2
// */
//public class DayViewListView extends LinearLayout {
//
//    private static final String TAG = DayViewListView.class.getSimpleName();
//
//    private Context mContext;
//    private LinearLayout mLayout;
//    private LinkedList<TaskDto> mDataList;
//    private ListView mListView;
//    private HelloAdapter mAdapter;
//    private LayoutInflater mInflater;
//
//    private UserActionListener mUserActionListener;
//
//    public interface UserActionListener {
//        public void goDetailPage(long id);
//    }
//
//    private int mLastFirstVisibleItem = 0;
//    protected OnScrollDirectionListener mOnScrollDirectionListener = null;
//
//    public interface OnScrollDirectionListener {
//        public void onScrollUp();
//
//        public void onScrollDown();
//
//        public void onScrollStop();
//    }
//
//    /**
//     * @param context
//     */
//    public DayViewListView(Context context) {
//        super(context);
//        init(context);
//    }
//
//    /**
//     * @param context
//     * @param attrs
//     */
//    public DayViewListView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context);
//    }
//
//    /**
//     * @param context
//     * @param attrs
//     * @param defStyle
//     */
//    @TargetApi(11)
//    public DayViewListView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        init(context);
//    }
//
//    private void init(Context context) {
//        mContext = context;
//        mDataList = new LinkedList<TaskDto>();
//        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        this.setOrientation(LinearLayout.VERTICAL);
//        LayoutInflater inflater = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mLayout = (LinearLayout)
//                inflater.inflate(R.layout.day_view_listview, this, true);
//        mListView =
//                (ListView) mLayout.findViewById(R.id.day_view_listview_listview);
//        mListView.setOnItemClickListener(mOnItemClickListener);
//        mListView.setOnScrollListener(mOnScrollListener);
//
//    }
//
//    public void addParallaxedHeaderView(View v) {
//        mListView.addHeaderView(v, null, true);
//    }
//
//    public void addList(List<TaskDto> list) {
//        mDataList.addAll(list);
//    }
//
//    public void add(TaskDto dto) {
//        mDataList.add(dto);
//    }
//
//    public void addFirstList(ArrayList<TaskDto> itemList) {
//        mDataList.addAll(0, itemList);
//    }
//
//    public void addFirst(TaskDto dto) {
//        mDataList.addFirst(dto);
//    }
//
//    public void removeListAll() {
//        mDataList = new LinkedList<TaskDto>();
//    }
//
//    public void changeData(int index, TaskDto dto) {
//        mDataList.set(index, dto);
//    }
//
//    public void setAdapter() {
//        mAdapter = new HelloAdapter();
//        mListView.setAdapter(mAdapter);
//    }
//
//    public ListAdapter getAdapter() {
//        return mAdapter;
//    }
//
//    public void notifyDataSetChanged() {
//        mAdapter.notifyDataSetChanged();
//    }
//
//    public void setSelection(int position) {
//        mListView.setSelection(position);
//    }
//
//    public void setUserActionLisetner(UserActionListener l) {
//        mUserActionListener = l;
//    }
//
//    public void setOnScrollDirectionListener(OnScrollDirectionListener l) {
//        mOnScrollDirectionListener = l;
//    }
//
//    private class HelloAdapter extends BaseAdapter {
//
//        private CustomTextView mTitle;
//        private CustomTextView mStartTime;
//        private CustomTextView mEndTime;
//        private CustomTextView mColor;
//        private CustomTextView mIsComplted;
//
//        public HelloAdapter() {
//            super();
//        }
//
//        public void setData(TaskDto dto) {
//            setTitle(dto.title);
//            setStartTime(dto.startTime);
//            setEndTime(dto.endTime);
//            setColor(dto.color);
//            setIsCompleted(dto.isCompleted);
//        }
//
//        private void setTitle(String title) {
//            mTitle.setText(title);
//        }
//
//        private void setStartTime(Calendar startTime) {
//            mStartTime.setText(startTime.get(Calendar.HOUR_OF_DAY)+":"+startTime.get(Calendar.MINUTE));
//        }
//
//        private void setEndTime(Calendar endTime) {
//            mEndTime.setText(endTime.get(Calendar.HOUR_OF_DAY)+":"+endTime.get(Calendar.MINUTE));
//        }
//
//        private void setColor(int color) {
//            mColor.setText(color + "");
//        }
//
//        private void setIsCompleted(boolean isCompleted) {
//            mIsComplted.setText(isCompleted + "");
//        }
//
//        @Override
//        public int getCount() {
//            return mDataList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return mDataList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (null == convertView) {
//                // convertView = new DayViewItem(mContext);
//                convertView = mInflater.inflate(R.layout.day_view_item, null);
//            }            
//
//            mTitle = (CustomTextView) convertView.findViewById(R.id.day_view_item_title);
//            mStartTime = (CustomTextView) convertView.findViewById(R.id.day_view_item_start_time);
//            mEndTime = (CustomTextView) convertView.findViewById(R.id.day_view_item_end_time);
//            mColor = (CustomTextView) convertView.findViewById(R.id.day_view_item_color);
//            mIsComplted = (CustomTextView) convertView.findViewById(R.id.day_view_item_complete);
//
//            setData(mDataList.get(position));
//            // ((DayViewItem)convertView).setData(mDataList.get(position));
//            // if(0 == position%2) {
//            // convertView.setBackgroundColor(0xff535353);
//            // } else {
//            // convertView.setBackgroundColor(0xff444444);
//            // }
//            return convertView;
//        }
//    }
//
//    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//
//            Log.e(TAG, "called mOnItemClickListener()");
//            if (null != mUserActionListener) {
//                mUserActionListener.goDetailPage(mDataList.get(position
//                        - mListView.getHeaderViewsCount()).id);
//            }
//        }
//    };
//
//    private OnClickListener mOnClickListener = new OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            Log.e(TAG, "called mOnClickListener()");
//            mUserActionListener.goDetailPage(mDataList.get(mListView.indexOfChild(v)
//                    - mListView.getHeaderViewsCount()).id);
//        }
//    };
//
//    private OnScrollListener mOnScrollListener = new OnScrollListener() {
//
//        @Override
//        public void onScrollStateChanged(AbsListView view, int scrollState) {
//            switch (scrollState) {
//                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
//                    if (null != mOnScrollDirectionListener) {
//                        mOnScrollDirectionListener.onScrollStop();
//                    }
//                    break;
//                case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
//                    break;
//                case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
//                    break;
//            }
//        }
//
//        @Override
//        public void onScroll(AbsListView view, int firstVisibleItem,
//                int visibleItemCount, int totalItemCount) {
//            if (null != mOnScrollDirectionListener) {
//                if (firstVisibleItem > mLastFirstVisibleItem) {
//                    mOnScrollDirectionListener.onScrollDown();
//                } else if (firstVisibleItem < mLastFirstVisibleItem) {
//                    mOnScrollDirectionListener.onScrollUp();
//                }
//            }
//
//            mLastFirstVisibleItem = firstVisibleItem;
//        }
//    };
//
//}
