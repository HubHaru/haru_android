/**
 * 
 */
package com.projects.haru.component;

import android.app.Activity;

/**
 * @author zeropol2
 *
 */
public abstract class ActivityBase extends Activity {

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initLayout();
	}
	
	protected abstract void initLayout();
}
