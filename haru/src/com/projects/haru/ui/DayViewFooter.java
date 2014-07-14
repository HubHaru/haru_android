/**
 * 
 */
package com.projects.haru.ui;

import com.projects.haru.R;
import com.projects.haru.ui.widget.CustomTextView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author zeropol2
 *
 */
public class DayViewFooter extends RelativeLayout {
	private RelativeLayout mLayout;
	private CustomTextView mSetting;
	private CustomTextView mAdd;
	private UserActionListener mUserActionListener;
	public interface UserActionListener {
		public void goSetting();
		public void goAdd();
	}
	/**
	 * @param context
	 */
	public DayViewFooter(Context context) {
		super(context);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public DayViewFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public DayViewFooter(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context) {
		this.setPadding(getResources().getDimensionPixelSize(R.dimen.px40), getResources().getDimensionPixelSize(R.dimen.px40),
				getResources().getDimensionPixelSize(R.dimen.px40), getResources().getDimensionPixelSize(R.dimen.px40));
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mLayout = (RelativeLayout) inflater.inflate(R.layout.day_view_footer,this, true);
		mSetting = (CustomTextView)mLayout.findViewById(R.id.day_view_fotter_setting);
		mAdd = (CustomTextView)mLayout.findViewById(R.id.day_view_fotter_add);
		
		mSetting.setOnClickListener(mOnClickListener);
		mAdd.setOnClickListener(mOnClickListener);
	}
	
	public void setUserActionListener(UserActionListener l ){
		mUserActionListener = l;
	}
	
	private OnClickListener mOnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(null != mUserActionListener) {
				if(v.getId() == mSetting.getId()) {
					mUserActionListener.goSetting();
				} else if(v.getId() == mAdd.getId()) {
					mUserActionListener.goAdd();
				}
			}
		}
	};

}
