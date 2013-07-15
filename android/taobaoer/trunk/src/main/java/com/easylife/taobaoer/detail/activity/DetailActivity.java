package com.easylife.taobaoer.detail.activity;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.ApplicationContext;
import com.easylife.taobaoer.core.model.Code;
import com.easylife.taobaoer.core.model.Token;
import com.easylife.taobaoer.core.task.ProgressTask;
import com.easylife.taobaoer.core.task.TaskCallback;
import com.easylife.taobaoer.core.widget.tab.TabBar;
import com.easylife.taobaoer.core.widget.tab.TabBarItem;
import com.easylife.taobaoer.detail.model.GoodsDetail;
import com.easylife.taobaoer.detail.service.IGoodsDetailService;
import com.easylife.taobaoer.detail.service.imp.GoodsDetailService;
import com.easylife.taobaoer.home.activity.CategoryActivity;
import com.easylife.taobaoer.home.activity.FavActivity;
import com.easylife.taobaoer.home.activity.IndexActivity;
import com.easylife.taobaoer.main.activity.LaunchActivity;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends ActivityGroup {
	IGoodsDetailService goodsDetailService = new GoodsDetailService();
	private TextView priceView;
	private ImageView picView;
	private TextView remarkView;
	private Button backButton;
	GoodsDetail goodsDetail;
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_detail);
		backButton = (Button) findViewById(R.id.back);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		new ProgressTask(DetailActivity.this, new TaskCallback(){
			@Override
			public void successCallback() {
				// TODO (review) 用table的clear方法
				priceView = (TextView) findViewById(R.id.goods_price);
				picView = (ImageView) findViewById(R.id.goods_pic);
				remarkView = (TextView) findViewById(R.id.goods_info);
				String pic_url = goodsDetail.getData().getPic_url();
				int screenWidth = getResources().getDisplayMetrics().widthPixels;
				goodsDetailService.showBigPostImage(DetailActivity.this, pic_url, picView,screenWidth);
				remarkView.setText(goodsDetail.getData().getRemark());
				priceView.setText("￥"+goodsDetail.getData().getGoods().getGoods_price());
			}

			@Override 
			public String doInBackground() {
				Intent intent = DetailActivity.this.getIntent();
				String twitter_goods_id = intent.getStringExtra("twitter_goods_id");
				String twitter_id = intent.getStringExtra("twitter_id");
				System.out.println(twitter_goods_id+"======="+twitter_id);
				goodsDetail = goodsDetailService.getGoodsDetail(DetailActivity.this,twitter_goods_id,twitter_id);
				return null;
			}
		}, false).execute();
		
	}
}
