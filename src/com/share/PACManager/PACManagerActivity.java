package com.share.PACManager;

import com.share.PACManager.params.*;
import android.app.TabActivity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.inputmethod.InputMethodManager;
import android.view.Gravity;

import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class PACManagerActivity extends TabActivity 
{
	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyUp(int, android.view.KeyEvent)
	 */


	private GestureDetector m_gesDetector;
	private CurHandler m_curHandler;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tab_layout); 
        
        createTabs(); 
       // m_gesDetector = new GestureDetector(new GestureListner());
        Looper curLooper = Looper.myLooper();
        m_curHandler = new CurHandler(curLooper);
    }

    
    public boolean onTouchEvent(View v, MotionEvent event) {
        //m_gesDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    
    class CurHandler extends Handler
    {
     public CurHandler(Looper looper)
     {
            super(looper);
     }
     public void handleMessage(Message msg) 
     {
    	 switch(msg.what)
    	 {
    	 case StaticParams.MSG_UI_SET_TAB_HEIGHT:
    		 Bundle msgData = msg.getData();
    		 int nHeight = msgData.getInt("TabHeight",0);
    		 setTabTagHeight(nHeight);
    		 break;
    	 default:
    		 super.handleMessage(msg);
    		 break;
    	 }            
      }    
    }
    
    class GestureListner extends SimpleOnGestureListener
    {
    	 // 双击的第二下Touch down时触发 
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTap");
            return super.onDoubleTap(e);
        }
        
        // 双击的第二下Touch down和up都会触发，可用e.getAction()区分
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTapEvent");
            return super.onDoubleTapEvent(e);
        }
        
        // Touch down时触发 
        public boolean onDown(MotionEvent e) {
            Log.i("MyGesture", "onDown");
            return super.onDown(e);
        }
        
        // Touch了滑动一点距离后，up时触发
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("MyGesture", "onFling");
            
            if(e2.getX() - e1.getX() > 50)
            {
            	Intent switchIntent = new Intent();
            	switchIntent.setClass(PACManagerActivity.this, RegActivity.class);
            	startActivity(switchIntent);
            }
            
            return true;//super.onFling(e1, e2, velocityX, velocityY);
        }
        
        // Touch了不移动一直Touch down时触发
        public void onLongPress(MotionEvent e) {
            Log.i("MyGesture", "onLongPress");
            super.onLongPress(e);
        }
        
        // Touch了滑动时触发
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("MyGesture", "onScroll");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
        
        /*
         * Touch了还没有滑动时触发
         * (1)onDown只要Touch Down一定立刻触发
         * (2)Touch Down后过一会没有滑动先触发onShowPress再触发onLongPress
         * So: Touch Down后一直不滑动，onDown -> onShowPress -> onLongPress这个顺序触发。
         */
        public void onShowPress(MotionEvent e) {
            Log.i("MyGesture", "onShowPress");
            super.onShowPress(e);
        }

        /*
         * 两个函数都是在Touch Down后又没有滑动(onScroll)，又没有长按(onLongPress)，然后Touch Up时触发
         * 点击一下非常快的(不滑动)Touch Up: onDown->onSingleTapUp->onSingleTapConfirmed
         * 点击一下稍微慢点的(不滑动)Touch Up: onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed 
         */ 
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("MyGesture", "onSingleTapConfirmed");
            return super.onSingleTapConfirmed(e);
        }
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("MyGesture", "onSingleTapUp");
            return super.onSingleTapUp(e);
        }    	
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuItem mi1 = menu.add(1, 1, 1, "刷新");
		mi1.setIcon(R.drawable.menu_refresh);
		MenuItem mi2 = menu.add(1, 2, 1, "退出");
		mi2.setIcon(R.drawable.menu_exit);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getGroupId() == 1)
		{
			switch(item.getItemId())
			{
			case 1:
				
				break;
				
			case 2:
				
				break;
			}
		}
		return super.onOptionsItemSelected(item);
	}
	
    private View createTabView(Context c, String lable, int resId) {
    	
    	LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View convertView = inflater.inflate(R.layout.tab_item, null, false);
		convertView.setBackgroundResource(R.drawable.tab_btn_selector);
		convertView.setClickable(true);
		
		ImageView icon = (ImageView) convertView.findViewById(R.id.id_icon);
		TextView text = (TextView) convertView.findViewById(R.id.id_text);		
		icon.setImageResource(resId);
		text.setText(lable);
		
		
		return convertView;
    	/*
    	LinearLayout layout = new LinearLayout(this);
    	layout.setClickable(true);
    	layout.setGravity(Gravity.CENTER);
    	layout.setBackgroundResource(R.drawable.tab_btn_selector);
				
		TextView textview = new TextView(c);
		textview.setTextSize(16);
		textview.setText(lable);
		textview.setTextColor(Color.WHITE);		
		layout.addView(textview);	
		
		return layout;
		
		*/
	}
    
	private void createTabs()
	{		
		TabHost tabHost = getTabHost();
		//1
		View view = createTabView(this, "信息登记", R.drawable.tab_icon_person);
		Intent intent = new Intent(this, RegActivity.class);
		TabSpec spec = tabHost.newTabSpec("tab1");
		spec.setIndicator(view);
		spec.setContent(intent);
		
		tabHost.addTab(spec);

		//2
		view = createTabView(this, "信息查询", R.drawable.tab_icon_query);
		intent = new Intent(this, QueryActivity.class);
		spec = tabHost.newTabSpec("tab2");
		spec.setIndicator(view);
		spec.setContent(intent);
		tabHost.addTab(spec);

		//3
		view = createTabView(this, "系统维护", R.drawable.tab_icon_setting);
		intent = new Intent(this, SettingActivity.class);
		spec = tabHost.newTabSpec("tab3");
		spec.setIndicator(view);
		spec.setContent(intent);
		tabHost.addTab(spec);
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener()
		{
			@Override
			public void onTabChanged(String tabId) 
			{
				InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}			

		});		
	}
		
	private void setTabTagHeight(int nHeight)
    {
		TabHost tabHost = getTabHost();
    	TabWidget tabWidget = tabHost.getTabWidget();
    	for (int i = 0; i < tabWidget.getChildCount(); i++) 
    	{
    	   tabWidget.getChildAt(i).getLayoutParams().height = Math.min(nHeight,0);
    	   tabWidget.getChildAt(i).getLayoutParams().width = 65;    	       	   
    	}
    }

	
}