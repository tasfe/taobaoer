package com.easylife.taobaoer.detail.service;

import org.json.JSONException;

import android.content.Context;
import android.widget.ImageView;

import com.easylife.taobaoer.detail.model.GoodsDetail;
import com.easylife.taobaoer.product.model.ProductList;

public interface IGoodsDetailService {
	/**
	 * 获取商品明细
	 * 
	 * @return
	 */
	public GoodsDetail getGoodsDetail(Context context,String twitter_goods_id,String goods_id);
	
	/**
	 * 显示商品大图
	 * @param context
	 * @param pic_url
	 * @param imageView
	 */
	public void showBigPostImage(final Context context, String pic_url,
			final ImageView imageView,final int screenWidth);
}
