package com.share.PACManager;

import com.share.PACManager.NavGridView.OnGridItemListener;
import com.share.PACManager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class QueryActivity extends Activity {

	private NavGridView gridview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.query_layout);
        
        gridview = (NavGridView) findViewById(R.id.id_gridview);
        gridview.setNumColumns(2);
        initGridView();
    }
    
    private void initGridView()
    {
    	gridview.addItem(1, "企业信息查询", R.drawable.icon);
    	gridview.addItem(2, "民警信息查询", R.drawable.police);
    	gridview.addItem(3, "人员信息查询", R.drawable.icon);
     	
    	gridview.setOnGridItemListener(new OnGridItemListener(){

    		@Override
    		public void onClickItem(int id) {
    			clickItem(id);    			
    		}        	
        });
    };
    
    private void clickItem(int id)
    {
    	Intent intent = new Intent();
    	intent.setClass(this, QuickSearchActivity.class);
    	startActivity(intent);
    }
}