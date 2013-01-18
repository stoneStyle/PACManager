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
	        //步骤1：获得TabHost的对象，并进行初始化setup()
	        TabHost tabs = (TabHost)findViewById(R.id.reg_tabhost);
	        tabs.setup();
	        //步骤2：通过TabHost.TabSpec增加tab的一页，通过setContent()增加内容，通过setIndicator增加页的标签
	        /*（1）增加第1页*/
	        TabHost.TabSpec spec = tabs.newTabSpec("Tag1");
	        spec.setContent(R.id.reg_police);
	        spec.setIndicator("Clock");
	        tabs.addTab(spec);
	        /*（2）增加第2页*/
	        spec = tabs.newTabSpec("Tag2");
	        spec.setContent(R.id.reg_company);
	        spec.setIndicator("Button");
	        tabs.addTab(spec);
	        //步骤3：可通过setCurrentTab(index)指定显示的页，从0开始计算。
	        tabs.setCurrentTab(1);
	}

}
