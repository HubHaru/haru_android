
package com.example.androidswipelistviewtest;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class ListAdapter extends ArrayAdapter<String> {

    private TextView mTitle;

    private Context mContext;

    private List<String> mDataList;

    private View.OnClickListener mOnDeletedItemClickListener;

    public ListAdapter(Context context, int layoutResource, int textViewResourceId,
            List<String> items) {
        super(context, layoutResource, textViewResourceId, items);
        mDataList = items;
        mContext = context;
    }
    
    public void removeItem(int position) {
        mDataList.remove(position);
        notifyDataSetChanged();
    }

    public void setData(String dto) {
        setTitle(dto);
    }

    private void setTitle(String title) {
        mTitle.setText(title);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public String getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.list_item, null);

        mTitle = (TextView) convertView.findViewById(R.id.text1);

        TextView delete = (TextView) convertView.findViewById(R.id.delete);
        delete.setTag(position);
//        delete.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "delete item: " + v.getTag(), Toast.LENGTH_SHORT).show();
//            }
//        });
        delete.setOnClickListener(mOnDeletedItemClickListener);
        setData(getItem(position));
        return convertView;
    }

    public void setOnDeleteItemClickListener(View.OnClickListener listener) {
        mOnDeletedItemClickListener = listener;
    }
}
