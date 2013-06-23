package com.easylife.taobaoer.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.easylife.taobaoer.R;

public class LaunchActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_launch);
		startActivity(new Intent(LaunchActivity.this, MainTabActivity.class));
		finish();
	}
}
