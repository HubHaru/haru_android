/**
 * 
 */
package com.projects.haru.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.projects.haru.R;

/**
 * @author zeropol2
 *
 */
public class CustomTextView extends TextView {
	private Context mContext = null;
	private volatile static Typeface tfNormal = null; // normal
	
	/**
	 * @param context
	 */
	public CustomTextView(Context context) {
		super(context);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		setCustomFont(attrs);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
		setCustomFont(attrs);
	}
	

	private void init(Context context) {
		mContext = context;
		// Ignore if within Eclipse
		if (isInEditMode()) {
			return;
		}

		// 폰트 생성
		if (tfNormal == null) {
			synchronized (this) {
				tfNormal = Typeface.createFromAsset(mContext.getAssets(), "fonts/NanumBarunGothic.otf");
			}
		}

		// 기본 폰트 설정
		setTypeface(tfNormal);
	}

	private void setCustomFont(AttributeSet attrs) {
		// Ignore if within Eclipse
		if (isInEditMode()) {
			return;
		}

		String font = null;
		if (attrs != null) {
			TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextView);
			for (int i = 0; i < typedArray.getIndexCount(); i++) {
				int attr = typedArray.getIndex(i);
				if (attr == R.styleable.CustomTextView_customFont) {
					font = typedArray.getString(0);
					break;
				}
			}
			typedArray.recycle();
		}

		// 설정된 폰트 체크
		if (font != null) {
			Typeface tf = Typeface.createFromAsset(mContext.getAssets(), "fonts/"+font);
			setTypeface(tf);
		} else {
			setTypeface(tfNormal);
		}
	}

}
