package com.share.PACManager;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class RegActivity extends TabActivity 
{
    @Override
	protected void onCreate(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.reg_main);
	        //����1�����TabHost�Ķ��󣬲����г�ʼ��setup()
	        TabHost tabs = (TabHost)findViewById(R.id.reg_tabhost);
	        tabs.setup();
	        //����2��ͨ��TabHost.TabSpec����tab��һҳ��ͨ��setContent()�������ݣ�ͨ��setIndicator����ҳ�ı�ǩ
	        /*��1�����ӵ�1ҳ*/
	        TabHost.TabSpec spec = tabs.newTabSpec("Tag1");
	        spec.setContent(R.id.reg_police);
	        spec.setIndicator("Clock");
	        tabs.addTab(spec);
	        /*��2�����ӵ�2ҳ*/
	        spec = tabs.newTabSpec("Tag2");
	        spec.setContent(R.id.reg_company);
	        spec.setIndicator("Button");
	        tabs.addTab(spec);
	        //����3����ͨ��setCurrentTab(index)ָ����ʾ��ҳ����0��ʼ���㡣
	        tabs.setCurrentTab(1);
	}

}
