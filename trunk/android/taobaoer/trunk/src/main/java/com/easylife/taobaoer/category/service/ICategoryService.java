package com.easylife.taobaoer.category.service;

import android.content.Context;

import com.easylife.taobaoer.category.model.CatDataList;

public interface ICategoryService {

	CatDataList getCatProductList(Context context,int page,int parentTypeId);
}
