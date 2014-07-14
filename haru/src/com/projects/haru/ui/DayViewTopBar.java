/**
 * 
 */
package com.projects.haru.ui;

import java.util.Calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.projects.haru.R;
import com.projects.haru.ui.widget.CustomTextView;

/**
 * @author zeropol2
 *
 */
public class DayViewTopBar extends LinearLayout {
	private LinearLayout mLayout;
	private CustomTextView mDate;
	private CustomTextView mDayOfWeek;
	private Calendar mCalendar;
	
	private UserActionListener mUserActionListener;
	public interface UserActionListener {
		public void goWeekView(Calendar cal);
	}
	/**
	 * @param context
	 */
	public DayViewTopBar(Context context) {
		super(context);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public DayViewTopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public DayViewTopBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context) {
		this.setBackgroundColor(0xff1E1E1E);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mLayout = (LinearLayout) inflater.inflate(R.layout.day_view_topbar,this, true);
		mDate = (CustomTextView)mLayout.findViewById(R.id.day_view_topbar_date);
		mDayOfWeek = (CustomTextView)mLayout.findViewById(R.id.day_view_topbar_dayofweek);
		this.setOnClickListener(mOnClickListener);
	}
	
	public void setData(Calendar data) {
		mCalendar = data;
		setDate(String.format("%02d.%02d.%02d", data.get(Calendar.YEAR),data.get(Calendar.MONTH)+1,data.get(Calendar.DAY_OF_MONTH)));
		setDayOfWeek(convertToDayOfWeek(data.get(Calendar.DAY_OF_WEEK)));
	}
	
	private void setDate(String date) {
		mDate.setText(date);
	}
	
	private void setDayOfWeek(String dayOfWeek) {
		mDayOfWeek.setText(dayOfWeek);
	}
	
	private String convertToDayOfWeek(int dayOfWeek) {
		if(Calendar.SUNDAY == dayOfWeek) {
			return "SUN";
		} else if(Calendar.MONDAY == dayOfWeek) {
			return "MON";
		} else if(Calendar.TUESDAY == dayOfWeek) {
			return "TUE";
		} else if(Calendar.WEDNESDAY == dayOfWeek) {
			return "WED";
		} else if(Calendar.THURSDAY == dayOfWeek) {
			return "THU";
		} else if(Calendar.FRIDAY == dayOfWeek) {
			return "FRI";
		} else if(Calendar.SATURDAY == dayOfWeek) {
			return "SAT";
		}
		return null;
	}
	
	public void setUserActionListener(UserActionListener l) {
		mUserActionListener = l;
	}
	
	private OnClickListener mOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(null != mUserActionListener) {
				mUserActionListener.goWeekView(mCalendar);
			}
		}
	};
	
}
