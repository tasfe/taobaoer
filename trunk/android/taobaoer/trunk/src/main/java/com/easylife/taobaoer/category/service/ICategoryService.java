package com.easylife.taobaoer.category.service;

import android.content.Context;

import com.easylife.taobaoer.category.model.CatDataList;
import com.easylife.taobaoer.category.model.CatParams;
import com.easylife.taobaoer.category.model.CatProductList;

public interface ICategoryService {

	CatDataList getCatChildrenList(Context context,int page,int parentTypeId);
	
	CatProductList getCatGrandChildrenList(Context context,int page,int childrenTypeId);
	
	CatProductList getCatGrandChildrenList(Context context,int page,CatParams catParams);
}
