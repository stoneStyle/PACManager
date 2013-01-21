package com.share.PACManager;

import com.share.PACManager.R;
import com.share.PACManager.NavGridView.OnGridItemListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class SettingActivity extends Activity {

	private NavGridView gridview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting_layout);
        
        gridview = (NavGridView) findViewById(R.id.id_gridview);
        gridview.setNumColumns(3);
        initGridView();
    }
    
    private void initGridView()
    {
    	gridview.addItem(1, "注册民警信息", R.drawable.police);
    	gridview.addItem(2, "注册企业信息", R.drawable.icon);
    	gridview.addItem(3, "注册设备信息", R.drawable.icon);
    	gridview.addItem(4, "同步字典信息", R.drawable.icon);
    	gridview.addItem(5, "同步企业信息", R.drawable.icon);
    	gridview.addItem(6, "同步民警信息", R.drawable.police);
    	gridview.addItem(7, "设备信息下载", R.drawable.icon);
    	gridview.addItem(8, "通知通告下载", R.drawable.icon);
    	gridview.addItem(9, "紧急通讯", R.drawable.icon);
    	gridview.addItem(10, "软件升级", R.drawable.icon);
    	
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