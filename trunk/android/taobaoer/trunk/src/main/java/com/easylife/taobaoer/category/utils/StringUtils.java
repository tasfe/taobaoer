package com.easylife.taobaoer.category.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.JSONObject;

import android.util.Log;

import com.easylife.taobaoer.category.model.CatParams;

public class StringUtils {

	public static CatParams analyzeInfoUrl(String infoUrl) {

		CatParams catParams = null;

		String meilishuo = "meilishuo://twitter_list.meilishuo?json_params";

		if (infoUrl != null && infoUrl.indexOf("=") > 0) {

			String[] str = infoUrl.split("=");
			if (str.length == 2) {

				if (meilishuo.equals(str[0])) {

					String jsonParams = getStrDecoder(str[1]);

					catParams = getJsonToObject(jsonParams);

				}

			}
		}

		return catParams;
	}

	public static String getStrDecoder(String str) {

		String dstr = null;

		try {
			dstr = URLDecoder.decode(str, "UTF-8");
		} catch (Exception e) {
			Log.e("StringUtils.getStrDecoder", e.toString());
		}
		return dstr;
	}
	
	public static String getStrEncoder(String str){
		
		String estr = null;
		
		try {
			estr = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			Log.e("StringUtils.getStrEncoder", e.toString());
		}
		return estr;
	}

	public static CatParams getJsonToObject(String strJson) {

		// {"title":"\u88d9\u88e4","method":"twitter\/attr","params":{"attr_id":"34304","type":"7d"}}
		CatParams catParams = null;

		try {
			JSONObject dataJson = new JSONObject(strJson);
			String title = dataJson.getString("title");
			
			JSONObject params = dataJson.getJSONObject("params");
			String attr_id = params.getString("attr_id");
			String type = params.getString("type");

			catParams = new CatParams();
			catParams.setTitle(getStrEncoder(title));
			catParams.setAttr_id(attr_id);
			catParams.setType(type);

		} catch (Exception e) {
			Log.e("StringUtils.getJsonToObject", e.toString());
		}

		return catParams;
	}

}
