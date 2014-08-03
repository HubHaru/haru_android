
package com.example.androidswipelistviewtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.fortysevendeg.swipelistview.OnItemDeleteListener;
import com.fortysevendeg.swipelistview.SwipeListView;

public class MainActivity extends Activity {

    private SwipeListView mListView;
    private ListAdapter mListAdapter;
    private List<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mDataList.add("Test" + (i + 1));
        }

        mListView = (SwipeListView) findViewById(R.id.list);

        mListAdapter = new ListAdapter((Context) this,
                R.layout.list_item,
                R.id.text1, mDataList);
        mListAdapter.setOnDeleteItemClickListener(mOnDeleteItemClickListener);

        mListView.setAdapter(mListAdapter);
        mListView.setOnItemDeleteListener( new OnItemDeleteListener() {
            
            @Override
            public void onRemove(int position) {
                mDataList.remove(position);
                mListAdapter.notifyDataSetChanged();
            }
        });
    }

    OnClickListener mOnDeleteItemClickListener = new View.OnClickListener() {
        
        @Override
        public void onClick(View v) {
            if (v.getTag() == null || !(v.getTag() instanceof Integer)) {
                return;
            }
            int position = (Integer) v.getTag();
            mListView.getSwipeListViewTouchListener().dismiss(position);
        }
    };
}
