package com.share.PACManager.task;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import com.share.PACManager.utils.MD5;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CheckPasswordTask extends Thread{

	private CheckPasswordListener mListener;
	private Context mContext;
	private String mPassword;
	
	public interface CheckPasswordListener {
		public String requestPassword();
		public void onCheckResult(boolean result);
	}

	public CheckPasswordTask(Context context) {
		mContext = context;
	}
	
	public void checkPassword(CheckPasswordListener listener) {
		mListener = listener;	
		start();
	}

	@Override
	public void run() {		
		String imei = ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		if(imei != null && imei.length() == 15)
		{
			try {
				mPassword = mListener.requestPassword();
				encodePassword(mPassword, imei);
				Thread.sleep(500);
				if(mListener != null)
					mListener.onCheckResult(true);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(mListener != null)
			mListener.onCheckResult(true);
		
		super.run();
	}	
	
	public static String encodePassword(String password, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		if(password == null)
			return null;
		
		// ①、	判断用户输入密码	是否符合规范：用户密码长度大于0小于等于20，输入的密码只能包含数字和字母。
		if(password.length() == 0 || password.length() > 20)
			return null;
		
		//②、	格式化用户密码：将用户密码转换为大写，去除左右空格。
		password = password.toUpperCase().trim();
		
		/*③、	第一次密码加密：将用户密码与密盐相加，使用UTF8格式获取其字节数组，使用MD5对字节数组进行加密，
		        将加密后的字节数组使用UTF8格式转换为字符串，将转换后的字符串转换为大写并截取前20位。
		*/
		String md5_text = password;		
		int times = 3;
		while((times--) > 0)
		{
			String text = md5_text + key;		
			byte[] bytes = text.getBytes("utf-8");
			byte[] md5_bytes = new MD5().getMD5(bytes);		
			md5_text = new String(md5_bytes, "utf-8").toUpperCase();
			if(md5_text.length() >= 20)
				md5_text = md5_text.substring(0, 20);
			
			System.out.println(text + " -> " + md5_text);
		}
		return md5_text;
	}	
}
