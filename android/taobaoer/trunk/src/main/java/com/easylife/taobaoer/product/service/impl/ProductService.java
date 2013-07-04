package com.easylife.taobaoer.product.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import android.content.Context;

import com.easylife.taobaoer.core.ApplicationContext;
import com.easylife.taobaoer.core.utils.HttpUtils;
import com.easylife.taobaoer.core.utils.TaobaoUtils;
import com.easylife.taobaoer.product.model.ProductList;
import com.easylife.taobaoer.product.service.IProductService;

public class ProductService implements IProductService {
	private final String HOT_PRODUCT_URI = "1.1/twitter/popular?";
	private final String CLIENT_ID = "10009";
	private final int maxResult = 40;

	@Override
	public ProductList getHotProductList(Context context, int page) {
		ApplicationContext config = (ApplicationContext) context
				.getApplicationContext();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("imei", TaobaoUtils.getDeviceToken(context));
		values.put("mac", TaobaoUtils.getMac(context));
		values.put("qudaoid", CLIENT_ID);
		values.put("offset", (page - 1) * maxResult);
		values.put("limit", 1 * maxResult);
		values.put("access_token", config.getToken().getAccess_token());
		ResponseEntity<ProductList> responseEntity = null;
		try {
			responseEntity = HttpUtils.get(context, HOT_PRODUCT_URI, values,
					ProductList.class);
		} catch (Exception e) {
			return null;
		}
		return responseEntity.getBody();
	}
}
