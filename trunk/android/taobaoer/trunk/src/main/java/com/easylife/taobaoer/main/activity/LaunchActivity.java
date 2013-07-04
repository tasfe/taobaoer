package com.easylife.taobaoer.main.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.ApplicationContext;
import com.easylife.taobaoer.core.model.Code;
import com.easylife.taobaoer.core.model.Token;
import com.easylife.taobaoer.core.service.ITaobaoService;
import com.easylife.taobaoer.core.service.impl.TaobaoService;

public class LaunchActivity extends Activity {
	ITaobaoService taobaoService = new TaobaoService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_launch);
		// 获取token
		Code code = taobaoService.getMeilishuoCode(LaunchActivity.this);
		Token token = taobaoService
				.getMeilishuoToken(LaunchActivity.this, code);
		ApplicationContext app = (ApplicationContext) LaunchActivity.this
				.getApplicationContext();
		app.setToken(token);
		startActivity(new Intent(LaunchActivity.this, MainTabActivity.class));
		finish();
	}
}
