/**
 * 
 */
package com.projects.haru.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import com.projects.haru.R;
import com.projects.haru.dto.TaskDto;
import com.projects.haru.ui.widget.CustomTextView;

import java.util.Calendar;

/**
 * @author zeropol2
 *
 */
public class DayViewItem extends LinearLayout {
	private LinearLayout mLayout;
	private CustomTextView mTitle;
	private CustomTextView mStartTime;
	private CustomTextView mEndTime;
	private CustomTextView mColor;
	private CustomTextView mIsComplted;
	/**
	 * @param context
	 */
	public DayViewItem(Context context) {
		super(context);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public DayViewItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	@TargetApi(11)
	public DayViewItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if(MeasureSpec.EXACTLY == widthMode) {	
        	int width = MeasureSpec.getSize(widthMeasureSpec);
        	adjustSize(width);
        } 
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	private void adjustSize(int width) {
		AbsListView.LayoutParams lp = (AbsListView.LayoutParams)this.getLayoutParams();
		lp.height = width *155 / 340 ; //340:155
		this.setLayoutParams(lp);
	}

	
	private void init(Context context) {
		this.setMinimumHeight(400);
		this.setOrientation(LinearLayout.VERTICAL);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mLayout = (LinearLayout) inflater.inflate(R.layout.day_view_item,this, true);
		mTitle = (CustomTextView)mLayout.findViewById(R.id.day_view_item_title);
		mStartTime = (CustomTextView)mLayout.findViewById(R.id.day_view_item_start_time);
		mEndTime = (CustomTextView)mLayout.findViewById(R.id.day_view_item_end_time);
		mColor = (CustomTextView)mLayout.findViewById(R.id.day_view_item_color);
		mIsComplted = (CustomTextView)mLayout.findViewById(R.id.day_view_item_complete);
	}
	
	public void setData(TaskDto taskDto) {
		setTitle(taskDto.title);
		setStartTime(taskDto.startTime);
		setEndTime(taskDto.endTime);
		setColor(taskDto.color);
		setIsCompleted(taskDto.isCompleted);
	}
	
	private void setTitle(String title) {
		mTitle.setText(title);
	}
	
	private void setStartTime(Calendar startTime) {
		mStartTime.setText(startTime.get(Calendar.HOUR_OF_DAY)+":"+startTime.get(Calendar.MINUTE));
	}
	
	private void setEndTime(Calendar endTime) {
		mEndTime.setText(endTime.get(Calendar.HOUR_OF_DAY)+":"+endTime.get(Calendar.MINUTE));
	}
	
	private void setColor(int color) {
		mColor.setText(color+"");
	}
	
	private void setIsCompleted(boolean isCompleted) {
		mIsComplted.setText(isCompleted+"");
	}

}
