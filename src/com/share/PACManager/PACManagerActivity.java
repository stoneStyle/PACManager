package com.share.PACManager;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class PACManagerActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab_layout); 
        
        createTabs();    	
    }
    
    private View createTabView(Context c, String text) {

    	LinearLayout layout = new LinearLayout(this);
    	layout.setClickable(true);
    	layout.setGravity(Gravity.CENTER);
    	layout.setBackgroundResource(R.drawable.tab_btn_selector);
		
		
		TextView textview = new TextView(c);
		textview.setTextSize(18);
		textview.setText(text);
		textview.setTextColor(Color.WHITE);
		
		layout.addView(textview);	
		
		return layout;
	}
    
	private void createTabs()
	{		
		TabHost tabHost = getTabHost();
		//1
		View view = createTabView(this, "信息登记");
		Intent intent = new Intent(this, TestActivity.class);
		TabSpec spec = tabHost.newTabSpec("tab1");
		spec.setIndicator(view);
		spec.setContent(intent);
		tabHost.addTab(spec);

		//2
		view = createTabView(this, " 信息查询");
		intent = new Intent(this, TestActivity.class);
		spec = tabHost.newTabSpec("tab2");
		spec.setIndicator(view);
		spec.setContent(intent);
		tabHost.addTab(spec);

		//3
		view = createTabView(this, "系统维护");
		intent = new Intent(this, TestActivity.class);
		spec = tabHost.newTabSpec("tab3");
		spec.setIndicator(view);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}	
}