package com.easylife.market.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;
import com.x.y.MyKAActivity;

public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startActivity(new Intent(this, MyKAActivity.class));
		finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

}
