package com.share.PACManager;

import com.share.PACManager.task.CheckPasswordTask.CheckPasswordListener;
import com.share.PACManager.task.RegDeviceTask.RegDeviceListener;
import com.share.PACManager.utils.Utils;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegDeviceActivity extends BindActivity {

	private Button reg_button;
	private boolean from_login;
	
	private EditText imei_text;
	private EditText server_text;
	private EditText pwd_text;
	private EditText pwd_text1;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_device_layout);         
        from_login = getIntent().getBooleanExtra("from_login", false);
        
        imei_text = (EditText) findViewById(R.id.id_imei_text);  
        imei_text.setText(Utils.getImei(this));
        
        server_text = (EditText) findViewById(R.id.id_server_text);  
        pwd_text = (EditText) findViewById(R.id.id_pwd_text);  
        pwd_text1 = (EditText) findViewById(R.id.id_pwd_text1);  
        
        reg_button = (Button) findViewById(R.id.id_reg_button);
        reg_button.setOnClickListener(onRegButton);
    }

    public OnClickListener onRegButton = new OnClickListener(){

		@Override
		public void onClick(View v) {
			hideIM(v);			
			String pwd = pwd_text.getText().toString();
			doRegDeivice();
		}    	
    };
    
    private void doRegDeivice()
    {    	
    	final ProgressDialog progressDialog = ProgressDialog.show(this, "", "正在注册设备...", true, false); 
		mBoundService.regDevice(new RegDeviceListener() {
			@Override
			public void onCheckResult(boolean result) {
				if (result) {
					setResult(RESULT_OK);
				} else {
					Toast.makeText(RegDeviceActivity.this, "注册失败",
							Toast.LENGTH_SHORT).show();
				}
				progressDialog.dismiss();				
				setResult(RESULT_OK);
				
				if(from_login)
				{
					Intent intent = new Intent();
					intent.putExtra("from_reg", true);
					intent.setClass(RegDeviceActivity.this, LoginActivity.class);
					startActivity(intent);
				}
				finish();
			}

			@Override
			public String requestImei() {
				return imei_text.getText().toString();
			}

			@Override
			public String requestServer() {
				return server_text.getText().toString();
			}

			@Override
			public String requestPassword() {
				return pwd_text.getText().toString();
			}

			@Override
			public String requestPasswordAgain() {
				return pwd_text1.getText().toString();
			}
		});
   }
    
	private void hideIM(View v) {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}
}