package com.easylife.taobaoer.category.task;

import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

	private SharedPreferences sharedPreferences;

	public SharedPreferencesManager(Context context) {
		sharedPreferences = context.getSharedPreferences("taobaoer-android",
				Context.MODE_PRIVATE);
	}

	public boolean isExist(String key) {
		return sharedPreferences.contains(key);
	}

	public void delete(String key) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.remove(key);
		editor.commit();
	}

	public void commit(String key, String value) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public void commitBool(String key, boolean value) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public void commit(Map<String, String> map) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		for (Entry<String, String> entry : map.entrySet()) {
			editor.putString(entry.getKey(), entry.getValue());
		}
		editor.commit();
	}

	public String getString(String key) {
		return sharedPreferences.getString(key, null);
	}

	public boolean getBoolean(String key) {
		return sharedPreferences.getBoolean(key, false);
	}
}
