package com.easylife.taobaoer.detail.activity;

import java.util.ArrayList;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.task.ProgressTask;
import com.easylife.taobaoer.core.task.TaskCallback;
import com.easylife.taobaoer.detail.adapter.ImagesItemAdapter;
import com.easylife.taobaoer.detail.adapter.SyncImageLoader;
import com.easylife.taobaoer.detail.model.GoodsImageInfo;
import com.easylife.taobaoer.detail.model.GoodsMoreImages;
import com.easylife.taobaoer.detail.service.IGoodsDetailService;
import com.easylife.taobaoer.detail.service.imp.GoodsDetailService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class MultigraphActivity extends Activity {
	IGoodsDetailService goodsDetailService = new GoodsDetailService();
	ListView imagesListView;
	GoodsMoreImages goodsMoreImages;
	private String twitter_goods_id;
	private String twitter_id;
	private ImagesItemAdapter adapter;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_more_images);
		//new Thread(runnable).start();
		new ProgressTask(MultigraphActivity.this, new TaskCallback(){

			@Override
			public String doInBackground() {
				Intent intent = MultigraphActivity.this.getIntent();
				twitter_goods_id = intent.getStringExtra("twitter_goods_id");
				twitter_id = intent.getStringExtra("twitter_id");
				goodsMoreImages = goodsDetailService
						.getGoodsMorePictures(MultigraphActivity.this,
								twitter_goods_id, twitter_id);
				return null;
			}

			@Override
			public void successCallback() {
				imagesListView = (ListView) findViewById(R.id.MoreImages);
				adapter = new ImagesItemAdapter(MultigraphActivity.this, imagesListView);
		        imagesListView.setAdapter(adapter);
		        adapter.clean();
		        ArrayList<GoodsImageInfo> list = goodsMoreImages.getData(); 
		        for(GoodsImageInfo imageInfo:list){
		        	 adapter.addImageInfo(imageInfo);
		        }
			}
			
		}, false).execute();
	}
	
	@Override
	protected void onDestroy() {
		SyncImageLoader imageLoader = adapter.getImageLoader();
		if (imageLoader != null){
			imageLoader.clearCache();
		}
		super.onDestroy();
	}

}
