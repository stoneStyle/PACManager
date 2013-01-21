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
		
		// �١�	�ж��û���������	�Ƿ���Ϲ淶���û����볤�ȴ���0С�ڵ���20�����������ֻ�ܰ������ֺ���ĸ��
		if(password.length() == 0 || password.length() > 20)
			return null;
		
		//�ڡ�	��ʽ���û����룺���û�����ת��Ϊ��д��ȥ�����ҿո�
		password = password.toUpperCase().trim();
		
		/*�ۡ�	��һ��������ܣ����û�������������ӣ�ʹ��UTF8��ʽ��ȡ���ֽ����飬ʹ��MD5���ֽ�������м��ܣ�
		        �����ܺ���ֽ�����ʹ��UTF8��ʽת��Ϊ�ַ�������ת������ַ���ת��Ϊ��д����ȡǰ20λ��
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
