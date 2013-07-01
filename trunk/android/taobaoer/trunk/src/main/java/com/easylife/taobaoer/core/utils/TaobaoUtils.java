package com.easylife.taobaoer.core.utils;

import org.springframework.util.StringUtils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

public class TaobaoUtils {
	public static String getDeviceToken(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		if (StringUtils.hasText(deviceId)) {
			return deviceId;
		}
		return "";
	}

	public static String getMac(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}
}
