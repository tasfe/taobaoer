package com.easylife.taobaoer.category.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import android.content.Context;
import android.util.Log;

import com.easylife.taobaoer.category.model.CatDataList;
import com.easylife.taobaoer.category.service.ICategoryService;
import com.easylife.taobaoer.core.ApplicationContext;
import com.easylife.taobaoer.core.utils.HttpUtils;
import com.easylife.taobaoer.core.utils.TaobaoUtils;

public class CategoryServiceImpl implements ICategoryService {

	private final String CAT_PRODUCT_URI = "2.0/navigation/tree?";
	private final String r = "navigation-root";
	private final String qudaoid = "10009";
	
	@Override
	public CatDataList getCatProductList(Context context, int page,
			int parentTypeId) {
		ApplicationContext config = (ApplicationContext) context
				.getApplicationContext();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("nid", parentTypeId);
		values.put("r", r);
		values.put("imei", TaobaoUtils.getDeviceToken(context));
		values.put("mac", TaobaoUtils.getMac(context));
		values.put("qudaoid", qudaoid);
		values.put("access_token", config.getToken().getAccess_token());
		ResponseEntity<CatDataList> responseEntity = null;
		try {
			responseEntity = HttpUtils.get(context, CAT_PRODUCT_URI, values,
					CatDataList.class);
		} catch (Exception e) {
			Log.e("CategoryServiceImpl.getCatProductList",e.toString());
			return null;
		}
		return responseEntity.getBody();
	}

}
