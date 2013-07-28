package com.easylife.taobaoer.detail.activity;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.category.activity.CategoryChildrenActivity;
import com.easylife.taobaoer.category.adapter.CategoryChildrenAdapter;
import com.easylife.taobaoer.core.task.ProgressTask;
import com.easylife.taobaoer.core.task.TaskCallback;
import com.easylife.taobaoer.detail.adapter.ImagesItemAdapter;
import com.easylife.taobaoer.detail.model.GoodsMoreImages;
import com.easylife.taobaoer.detail.service.IGoodsDetailService;
import com.easylife.taobaoer.detail.service.imp.GoodsDetailService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;

public class MultigraphActivity extends Activity {
	IGoodsDetailService goodsDetailService = new GoodsDetailService();
	ListView imagesListView;
	GoodsMoreImages goodsMoreImages;
	private String twitter_goods_id;
	private String twitter_id;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_more_images);
		//new Thread(runnable).start();
		new ProgressTask(MultigraphActivity.this, new TaskCallback(){

			@Override
			public String doInBackground() {
				ImagesItemAdapter adapter = new ImagesItemAdapter(MultigraphActivity.this, null, goodsMoreImages);
				imagesListView = (ListView) findViewById(R.id.MoreImages);
		        imagesListView.setAdapter(adapter);
				return null;
			}

			@Override
			public void successCallback() {
				Intent intent = MultigraphActivity.this.getIntent();
				twitter_goods_id = intent.getStringExtra("twitter_goods_id");
				twitter_id = intent.getStringExtra("twitter_id");
				goodsMoreImages = goodsDetailService
						.getGoodsMorePictures(MultigraphActivity.this,
								twitter_goods_id, twitter_id);
			}
			
		}, false).execute();
		
	}
	
//	Runnable runnable = new Runnable(){
//	    @Override
//	    public void run() {
//	    	Intent intent = MultigraphActivity.this.getIntent();
//			twitter_goods_id = intent.getStringExtra("twitter_goods_id");
//			twitter_id = intent.getStringExtra("twitter_id");
//			goodsMoreImages = goodsDetailService
//					.getGoodsMorePictures(MultigraphActivity.this,
//							twitter_goods_id, twitter_id);
//			ImagesItemAdapter adapter = new ImagesItemAdapter(MultigraphActivity.this, null, goodsMoreImages);
//			imagesListView = (ListView) findViewById(R.id.MoreImages);
//	        imagesListView.setAdapter(adapter);
//	    }
//	};
}
