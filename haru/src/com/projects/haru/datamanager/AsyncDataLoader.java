/**
 * 
 */
package com.projects.haru.datamanager;

import android.os.AsyncTask;

/**
 * @author zeropol2
 * 
 */
public abstract class AsyncDataLoader<T> extends AsyncTask<Void, Void, T> {
	public interface OnDataLoadListener<T> {
		public void onDataLoad(T data);
		public void onDataLoadFail(Exception e);
	}
	private OnDataLoadListener<T> mOnDataLoadListener = null;
	
	public AsyncDataLoader(OnDataLoadListener<T> l) {
		mOnDataLoadListener = l;
	}
	
	protected abstract T doTask() throws Exception;
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected T doInBackground(Void... unused) {
		try{
			return doTask();
		} catch(Exception e) {
			e.printStackTrace();
			if(null != mOnDataLoadListener) {
				mOnDataLoadListener.onDataLoadFail(e);
			}
			return null;
		}
	}

	@Override
	protected void onPostExecute(T result) {
		if(null != result && null != mOnDataLoadListener) {
			mOnDataLoadListener.onDataLoad(result);
		}
	}

}
