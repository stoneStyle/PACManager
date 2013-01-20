package com.share.PACManager;

import android.app.Activity;

import com.share.PACManager.params.*;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.content.Intent;
import android.gesture.Gesture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.view.Gravity;

import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class PACManagerActivity extends TabActivity 
{
	private GestureDetector m_gesDetector;
	private CurHandler m_curHandler;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
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
    	 // ˫���ĵڶ���Touch downʱ���� 
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTap");
            return super.onDoubleTap(e);
        }
        
        // ˫���ĵڶ���Touch down��up���ᴥ��������e.getAction()����
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.i("MyGesture", "onDoubleTapEvent");
            return super.onDoubleTapEvent(e);
        }
        
        // Touch downʱ���� 
        public boolean onDown(MotionEvent e) {
            Log.i("MyGesture", "onDown");
            return super.onDown(e);
        }
        
        // Touch�˻���һ������upʱ����
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
        
        // Touch�˲��ƶ�һֱTouch downʱ����
        public void onLongPress(MotionEvent e) {
            Log.i("MyGesture", "onLongPress");
            super.onLongPress(e);
        }
        
        // Touch�˻���ʱ����
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i("MyGesture", "onScroll");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
        
        /*
         * Touch�˻�û�л���ʱ����
         * (1)onDownֻҪTouch Downһ�����̴���
         * (2)Touch Down���һ��û�л����ȴ���onShowPress�ٴ���onLongPress
         * So: Touch Down��һֱ��������onDown -> onShowPress -> onLongPress���˳�򴥷���
         */
        public void onShowPress(MotionEvent e) {
            Log.i("MyGesture", "onShowPress");
            super.onShowPress(e);
        }

        /*
         * ��������������Touch Down����û�л���(onScroll)����û�г���(onLongPress)��Ȼ��Touch Upʱ����
         * ���һ�·ǳ����(������)Touch Up: onDown->onSingleTapUp->onSingleTapConfirmed
         * ���һ����΢�����(������)Touch Up: onDown->onShowPress->onSingleTapUp->onSingleTapConfirmed 
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
		View view = createTabView(this, "��Ϣ�Ǽ�");
		Intent intent = new Intent(this, RegActivity.class);
		TabSpec spec = tabHost.newTabSpec("tab1");
		spec.setIndicator(view);
		spec.setContent(intent);
		
		tabHost.addTab(spec);

		//2
		view = createTabView(this, " ��Ϣ��ѯ");
		intent = new Intent(this, TestActivity.class);
		spec = tabHost.newTabSpec("tab2");
		spec.setIndicator(view);
		spec.setContent(intent);
		tabHost.addTab(spec);

		//3
		view = createTabView(this, "ϵͳά��");
		intent = new Intent(this, TestActivity.class);
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