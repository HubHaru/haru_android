/**
 * 
 */
package com.projects.haru.ui;

import java.util.ArrayList;
import java.util.LinkedList;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.projects.haru.R;

/**
 * @author zeropol2
 *
 */
public class HelloListView extends LinearLayout {
	private Context mContext;
	private LinearLayout mLayout;
	private LinkedList<HelloDto> mDataList;
	private ListView mListView;
	private HelloAdapter mAdapter;
	
	private UserActionListener mUserActionListener;
	public interface UserActionListener {
		public void goDetailPage(long id);
	}
	
	/**
	 * @param context
	 */
	public HelloListView(Context context) {
		super(context);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public HelloListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	@TargetApi(11)
	public HelloListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context) {
		mContext = context;
		mDataList = new LinkedList<HelloDto>();
		this.setOrientation(LinearLayout.VERTICAL);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mLayout = (LinearLayout) inflater.inflate(R.layout.hello_listview,this, true);
		mListView = (ListView)mLayout.findViewById(R.id.hello_listview_listview);
		mListView.setOnItemClickListener(mOnItemClickListener);
	}
	
	public void addList(ArrayList<HelloDto> list) {
		mDataList.addAll(list);
	}
	
	public void add(HelloDto dto) {
		mDataList.add(dto);
	}
	
	public void addFirstList(ArrayList<HelloDto> itemList) {
		mDataList.addAll(0, itemList);
	}
	
	public void addFirst(HelloDto dto) {
		mDataList.addFirst(dto);
	}
	
	public void removeListAll() {
		mDataList = new LinkedList<HelloDto>();
	}
	
	public void changeData(int index, HelloDto dto) {
		mDataList.set(index, dto);
	}
	
	public void setAdapter() {
		mAdapter = new HelloAdapter();
		mListView.setAdapter(mAdapter);
	}
	
	public ListAdapter getAdapter() {
		return mAdapter;
	}
	
	public void notifyDataSetChanged() {
		mAdapter.notifyDataSetChanged();
	}
	
	public void setSelection(int position) {
		mListView.setSelection(position);
	}
	
	public void setUserActionLisetner(UserActionListener l) {
		mUserActionListener = l;
	}
	
	private class HelloAdapter extends BaseAdapter {
		
		public HelloAdapter() {
			super();
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

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(null == convertView) {
				convertView = new HelloItem(mContext);
			}
			((HelloItem)convertView).setData(mDataList.get(position));
			return convertView;
		}
	}
	
	private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			if(null != mUserActionListener) {
				mUserActionListener.goDetailPage(mDataList.get(position).id);
			}
        }
	};

}
