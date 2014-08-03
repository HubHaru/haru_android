
package com.projects.haru.ui.widget;

import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projects.haru.R;
import com.projects.haru.dto.TaskDto;

public class HelloAdapter extends BaseAdapter {

    private CustomTextView mTitle;
    private CustomTextView mStartTime;
    private CustomTextView mEndTime;
    private CustomTextView mColor;
    private CustomTextView mIsComplted;
    
    private TextView mDelete;

    private Context mContext;
    private List<TaskDto> mDataList;
    private LayoutInflater mInflater;
    private View.OnClickListener mOnDeletedItemClickListener;

    public HelloAdapter(Context context, List<TaskDto> data) {
        mContext = context;
        mDataList = data;
    }

    public void setData(TaskDto dto) {
        setTitle(dto.title);
        setStartTime(dto.startTime);
        setEndTime(dto.endTime);
        setColor(dto.color);
        setIsCompleted(dto.isCompleted);
    }

    private void setTitle(String title) {
        mTitle.setText(title);
    }

    private void setStartTime(Calendar startTime) {
        mStartTime.setText(startTime.get(Calendar.HOUR_OF_DAY) + ":"
                + startTime.get(Calendar.MINUTE));
    }

    private void setEndTime(Calendar endTime) {
        mEndTime.setText(endTime.get(Calendar.HOUR_OF_DAY) + ":" + endTime.get(Calendar.MINUTE));
    }

    private void setColor(int color) {
        mColor.setText(color + "");
    }

    private void setIsCompleted(boolean isCompleted) {
        mIsComplted.setText(isCompleted + "");
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItem(int position) {
        mDataList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.day_view_item, null);

        mTitle = (CustomTextView) convertView.findViewById(R.id.day_view_item_title);
        mStartTime = (CustomTextView) convertView.findViewById(R.id.day_view_item_start_time);
        mEndTime = (CustomTextView) convertView.findViewById(R.id.day_view_item_end_time);
        mColor = (CustomTextView) convertView.findViewById(R.id.day_view_item_color);
        mIsComplted = (CustomTextView) convertView.findViewById(R.id.day_view_item_complete);
        
        mDelete = (TextView) convertView.findViewById(R.id.day_view_item_back_delete);
        mDelete.setTag(position);
        mDelete.setOnClickListener(mOnDeletedItemClickListener);
        
        setData(mDataList.get(position));
        return convertView;
    }

    public void setOnDeleteItemClickListener(View.OnClickListener listener) {
        mOnDeletedItemClickListener = listener;
    }
}
