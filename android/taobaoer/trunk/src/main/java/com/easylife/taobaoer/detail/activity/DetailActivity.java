package com.easylife.taobaoer.detail.activity;

import java.util.ArrayList;
import java.util.List;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.task.ProgressTask;
import com.easylife.taobaoer.core.task.TaskCallback;
import com.easylife.taobaoer.core.utils.DbCollect;
import com.easylife.taobaoer.detail.model.GoodsDetail;
import com.easylife.taobaoer.detail.service.IGoodsDetailService;
import com.easylife.taobaoer.detail.service.imp.GoodsDetailService;
import com.easylife.taobaoer.product.model.Product;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends ActivityGroup {
	IGoodsDetailService goodsDetailService = new GoodsDetailService();
	private TextView priceView;
	private ImageView picView;
	private TextView remarkView;
	private Button backButton;
	private DbCollect dbCollect;
	private GoodsDetail goodsDetail;
	private String twitter_goods_id;
	private String twitter_id;
	private List<Product> productList=new ArrayList<Product>();
	
	//author reash26 20130717
	private Button favouriteButton; 
	private Product product=new Product();
	
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_detail);
		
		//author reash26 20130717
		favouriteButton=(Button) findViewById(R.id.favourite);
		favouriteButton.setOnClickListener(new FavouriteButtonOnclick());
		dbCollect=new DbCollect(this);
	
		//从doInBackground移至这里
		Intent intent = DetailActivity.this.getIntent();
		twitter_goods_id = intent.getStringExtra("twitter_goods_id");
		twitter_id = intent.getStringExtra("twitter_id");
		
		//判断其商品是否为已收藏商品
		productList=dbCollect.getCollectProduct(Long.parseLong(twitter_id));
		if(productList.size()!=0)
			favouriteButton.setBackgroundResource(R.drawable.fav_hover);
		
		
		backButton = (Button) findViewById(R.id.back);
		ImageView imagesView = (ImageView) findViewById(R.id.goods_pic);
		imagesView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("twitter_goods_id", twitter_goods_id);
				intent.putExtra("twitter_id",twitter_id);
				intent.setClass(DetailActivity.this,MultigraphActivity.class);
				DetailActivity.this.startActivity(intent);
			}
		});
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
				product.setTitle("￥"+goodsDetail.getData().getGoods().getGoods_price());
				product.setPic_height(Integer.parseInt(goodsDetail.getData().getPic_height()));
				product.setPic_width(Integer.parseInt(goodsDetail.getData().getPic_width()));
				
				
			}

			@Override 
			public String doInBackground() {
				//author reash26 20130717
				product.setTwitter_goods_id(Integer.parseInt(twitter_goods_id));
				product.setTwitter_id(Integer.parseInt(twitter_id));
				
				goodsDetail = goodsDetailService.getGoodsDetail(DetailActivity.this,twitter_goods_id,twitter_id);
				product.setPic_url(goodsDetail.getData().getPic_url());
				return null;
			}
		}, false).execute();
		
	}
	
	/**
	 * 收藏按钮的点击事件
	 * @author reash26
	 *
	 */
	class FavouriteButtonOnclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			productList=dbCollect.getCollectProduct(product.getTwitter_id());
			if(productList.size()==0){
				Long successflg=dbCollect.saveproductInfo(product);
				if(successflg!=-1){
					Toast.makeText(DetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
					favouriteButton.setBackgroundResource(R.drawable.fav_hover);
				}
			}else{
				AlertDialog alert = new AlertDialog.Builder(DetailActivity.this).setTitle("提示")
						.setMessage("确定要删除该收藏吗？").setPositiveButton("确定",new DialogInterface.OnClickListener() {//设置确定按钮
								@Override//处理确定按钮点击事件
								public void onClick(DialogInterface dialog, int which) {
									dbCollect.deleteCollectProduct(product);
									favouriteButton.setBackgroundResource(R.drawable.favourite_selector_button);
									}
								})
					                 .setNegativeButton("取消",new DialogInterface.OnClickListener() {//设置取消按钮
					                                                   @Override//取消按钮点击事件
								public void onClick(DialogInterface dialog, int which) {
									dialog.cancel();//对话框关闭。
									}
								}).create();
								alert.show();
			}
		}
		
	}
}
