package com.easylife.taobaoer.product.service;

import android.content.Context;

import com.easylife.taobaoer.product.model.ProductList;

public interface IProductService {
	/**
	 * 获取热门列表
	 * 
	 * @return
	 */
	ProductList getHotProductList(Context context, int page);
}
