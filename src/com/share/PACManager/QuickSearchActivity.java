package com.share.PACManager;

import com.share.PACManager.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class QuickSearchActivity extends Activity {

	private EditText search_edit;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.quick_search_layout);
        
        search_edit = (EditText) findViewById(R.id.id_search_edit);
        
    }
    
    public void showInputMethod(View view) {
    	 InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    	 im.showSoftInput(view, 0);
    }
}