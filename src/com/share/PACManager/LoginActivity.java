package com.share.PACManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText pwd_text;
	private Button login_button;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout); 
        
        pwd_text = (EditText) findViewById(R.id.id_pwd_text);
        pwd_text.setText("123456");
        
        login_button = (Button) findViewById(R.id.id_login_button);
        login_button.setOnClickListener(onLoginButton);
    }
    
    
    public OnClickListener onLoginButton = new OnClickListener(){

		@Override
		public void onClick(View v) {
			hideIM(v);
			
			String pwd = pwd_text.getText().toString();
			if(TextUtils.isEmpty(pwd))
			{
				Toast.makeText(LoginActivity.this, "«Î ‰»Î√‹¬Î", Toast.LENGTH_SHORT).show();
				return;
			}			
			doLogin(pwd);
		}
    	
    };
    
    private void doLogin(String pwd)
    {
    	//Toast.makeText(LoginActivity.this, "µ«¬º≥…π¶", Toast.LENGTH_SHORT).show();
    	
    	Intent intent = new Intent();
    	intent.setClass(this, PACManagerActivity.class);
    	startActivity(intent);
    	finish();
    }
    
	private void hideIM(View v) {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}
}