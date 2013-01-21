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
    	gridview.addItem(1, "ע������Ϣ", R.drawable.police);
    	gridview.addItem(2, "ע����ҵ��Ϣ", R.drawable.icon);
    	gridview.addItem(3, "ע���豸��Ϣ", R.drawable.icon);
    	gridview.addItem(4, "ͬ���ֵ���Ϣ", R.drawable.icon);
    	gridview.addItem(5, "ͬ����ҵ��Ϣ", R.drawable.icon);
    	gridview.addItem(6, "ͬ������Ϣ", R.drawable.police);
    	gridview.addItem(7, "�豸��Ϣ����", R.drawable.icon);
    	gridview.addItem(8, "֪ͨͨ������", R.drawable.icon);
    	gridview.addItem(9, "����ͨѶ", R.drawable.icon);
    	gridview.addItem(10, "�������", R.drawable.icon);
    	
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