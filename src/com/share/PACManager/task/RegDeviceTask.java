package com.share.PACManager.task;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import com.share.PACManager.utils.MD5;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class RegDeviceTask extends Thread{

	private RegDeviceListener mListener;
	private Context mContext;
	private String mPassword;
	
	public interface RegDeviceListener {
		public String requestImei();
		public String requestServer();
		public String requestPassword();
		public String requestPasswordAgain();
		public void onCheckResult(boolean result);
	}

	public RegDeviceTask(Context context) {
		mContext = context;
	}
	
	public void regDevice(RegDeviceListener listener) {
		mListener = listener;		
	
		start();
	}

	@Override
	public void run() {		
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(mListener != null)
			mListener.onCheckResult(true);		
		super.run();
	}	
	
	
}
