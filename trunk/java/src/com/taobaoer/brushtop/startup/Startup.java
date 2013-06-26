package com.taobaoer.brushtop.startup;

import com.taobaoer.brushtop.core.BrushtopCore;

public class Startup {
	

	public static void main(String[] args) {
		 BrushtopCore brushtop = new BrushtopCore("http://www.apk.anzhi.com/apk/201306/07/com.easylife.weather_06894700_0.apk");
		 for(int i =0;i< 2;i++){
			 brushtop.excute();
		 }
	}
}
