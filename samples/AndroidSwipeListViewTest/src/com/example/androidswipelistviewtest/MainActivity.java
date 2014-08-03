
package com.example.androidswipelistviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.fortysevendeg.swipelistview.SwipeListView;

public class MainActivity extends Activity {
    
    SwipeListView mListView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String[] array = new String[] {
                "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test",
                "Test"
        };
        
        mListView = (SwipeListView) findViewById(R.id.list);

        mListView.setAdapter(new ArrayAdapter<String>(this,
                R.layout.list_item,
                R.id.text1, array));
    }
}
