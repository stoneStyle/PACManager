package com.share.PACManager;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabWidget;

public class RegActivity extends Activity 
{
	private TabWidget tabWidget;
	private TabHost tabHost;
	private EditText m_editPolice;
    @Override
	public void onCreate(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.reg_main);
	        m_editPolice = (EditText)findViewById(R.id.edit_police);
	        m_editPolice.setOnFocusChangeListener(new FocusOnEdit());
	}
    
    class FocusOnEdit implements EditText.OnFocusChangeListener
    {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			Bundle msgData = new Bundle();
			if(hasFocus)
			{
				
			}
		}
    }
    
    
	
}
