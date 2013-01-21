package com.share.PACManager.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class Utils {

	/*
	 * 获取设备IMEI 15位数字编码
	 */
	public static String getImei(Context context)
	{
		return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}
}
