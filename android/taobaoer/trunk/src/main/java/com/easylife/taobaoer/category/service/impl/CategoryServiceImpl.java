package com.easylife.taobaoer.category.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import android.content.Context;
import android.util.Log;

import com.easylife.taobaoer.category.model.CatDataList;
import com.easylife.taobaoer.category.model.CatParams;
import com.easylife.taobaoer.category.model.CatProductList;
import com.easylife.taobaoer.category.service.ICategoryService;
import com.easylife.taobaoer.core.ApplicationContext;
import com.easylife.taobaoer.core.utils.HttpUtils;
import com.easylife.taobaoer.core.utils.TaobaoUtils;

public class CategoryServiceImpl implements ICategoryService {

	private final String CAT_CHILDREND_URI = "2.0/navigation/tree?";
	private final String CAT_GRAND_CHILDREND_URI_NEW = "2.0/navigation/poster?";
	private final String CAT_GRAND_CHILDREND_URI_OLD = "1.1/twitter/attr?";
	private final String r1 = "navigation-root";
	private final String r2 = "navigation-top_word";
	private final String r3 = "navigation-root.navigation-tree";
	private final String qudaoid = "10009";
	private final int maxResult = 40;
	
	@Override
	public CatDataList getCatChildrenList(Context context, int page,
			int parentTypeId) {
		ApplicationContext config = (ApplicationContext) context
				.getApplicationContext();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("nid", parentTypeId);
		values.put("r", r1);
		values.put("imei", TaobaoUtils.getDeviceToken(context));
		values.put("mac", TaobaoUtils.getMac(context));
		values.put("qudaoid", qudaoid);
		values.put("access_token", config.getToken().getAccess_token());
		ResponseEntity<CatDataList> responseEntity = null;
		try {
			responseEntity = HttpUtils.get(context, CAT_CHILDREND_URI, values,
					CatDataList.class);
		} catch (Exception e) {
			Log.e("CategoryServiceImpl.getCatChildrenList",e.toString());
			return null;
		}
		return responseEntity.getBody();
	}

	@Override
	public CatProductList getCatGrandChildrenList(Context context, int page,
			int childrenTypeId) {
		
		ApplicationContext config = (ApplicationContext) context
				.getApplicationContext();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("offset", (page - 1) * maxResult);
		values.put("limit", page * maxResult);
		values.put("eid","");
		values.put("nid", childrenTypeId);
		values.put("r", r2);
		values.put("imei", TaobaoUtils.getDeviceToken(context));
		values.put("mac", TaobaoUtils.getMac(context));
		values.put("qudaoid", qudaoid);
		values.put("access_token", config.getToken().getAccess_token());
		ResponseEntity<CatProductList> responseEntity = null;
		try {
			responseEntity = HttpUtils.get(context, CAT_GRAND_CHILDREND_URI_NEW, values,
					CatProductList.class);
		} catch (Exception e) {
			Log.e("CategoryServiceImpl.getCatGrandChildrenList",e.toString());
			return null;
		}
		return responseEntity.getBody();
	}

	@Override
	public CatProductList getCatGrandChildrenList(Context context, int page,
			CatParams catParams) {
		
		ApplicationContext config = (ApplicationContext) context
				.getApplicationContext();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("offset", (page - 1) * maxResult);
		values.put("page", page - 1);
		values.put("limit", page * maxResult);
		values.put("topic_title", catParams.getTitle());
		values.put("r", r3);
		values.put("attr_id", catParams.getAttr_id());
		values.put("type", catParams.getType());
		values.put("imei", TaobaoUtils.getDeviceToken(context));
		values.put("mac", TaobaoUtils.getMac(context));
		values.put("qudaoid", qudaoid);
		values.put("access_token", config.getToken().getAccess_token());
		ResponseEntity<CatProductList> responseEntity = null;
		try {
			responseEntity = HttpUtils.get(context, CAT_GRAND_CHILDREND_URI_OLD, values,
					CatProductList.class);
		} catch (Exception e) {
			Log.e("CategoryServiceImpl.getCatGrandChildrenList2",e.toString());
			return null;
		}
		return responseEntity.getBody();
	}

}
