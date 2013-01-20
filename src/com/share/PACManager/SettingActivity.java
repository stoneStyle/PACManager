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
    	gridview.addItem(1, "ͬ���ֵ���Ϣ", R.drawable.icon);
    	gridview.addItem(2, "ͬ����ҵ��Ϣ", R.drawable.icon);
    	gridview.addItem(3, "ͬ������Ϣ", R.drawable.police);
    	gridview.addItem(4, "�豸��Ϣ����", R.drawable.icon);
    	gridview.addItem(5, "֪ͨͨ������", R.drawable.icon);
    	gridview.addItem(6, "����ͨѶ", R.drawable.icon);
    	gridview.addItem(7, "�������", R.drawable.icon);
    	
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