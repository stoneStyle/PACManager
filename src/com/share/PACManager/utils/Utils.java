package com.share.PACManager.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class Utils {

	/*
	 * ��ȡ�豸IMEI 15λ���ֱ���
	 */
	public static String getImei(Context context)
	{
		return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}
}
