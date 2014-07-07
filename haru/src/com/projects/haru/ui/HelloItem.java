/**
 * 
 */
package com.projects.haru.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.projects.haru.dto.HelloDto;
import com.projects.haru.ui.widget.CustomTextView;
import com.skplanet.haru.R;

/**
 * @author zeropol2
 *
 */
public class HelloItem extends LinearLayout {
	private LinearLayout mLayout;
	private CustomTextView mTitle;
	private CustomTextView mContent;
	/**
	 * @param context
	 */
	public HelloItem(Context context) {
		super(context);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public HelloItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	@TargetApi(11)
	public HelloItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context) {
		this.setOrientation(LinearLayout.VERTICAL);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mLayout = (LinearLayout) inflater.inflate(R.layout.hello_item,this, true);
		mTitle = (CustomTextView)mLayout.findViewById(R.id.hello_item_title);
		mContent = (CustomTextView)mLayout.findViewById(R.id.hello_item_content);
	}
	
	public void setData(HelloDto dto) {
		setTitle(dto.title);
		setContent(dto.content);
	}
	
	private void setTitle(String title) {
		mTitle.setText(title);
	}
	
	private void setContent(String content) {
		mContent.setText(content);
	}

}
