package com.share.PACManager;

import com.share.PACManager.NavGridView.OnGridItemListener;
import com.share.PACManager.R;

import android.app.Activity;
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
        initGridView();
    }
    
    private void initGridView()
    {
    	gridview.addItem(1, "1111", R.drawable.icon);
    	gridview.addItem(2, "2222", R.drawable.icon);
    	gridview.addItem(3, "3333", R.drawable.police);
    	gridview.addItem(4, "4444", R.drawable.icon);
    	gridview.addItem(5, "5555", R.drawable.icon);
    	
    	gridview.setOnGridItemListener(new OnGridItemListener(){

    		@Override
    		public void onClickItem(int id) {
    			clickItem(id);    			
    		}        	
        });
    };
    
    private void clickItem(int id)
    {
    	Toast.makeText(this, "OnClick: " + id, Toast.LENGTH_SHORT).show();
    }
}