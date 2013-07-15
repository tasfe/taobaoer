package com.easylife.taobaoer.detail.service.imp;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.ApplicationContext;
import com.easylife.taobaoer.core.utils.HttpUtils;
import com.easylife.taobaoer.core.utils.ImageUtils;
import com.easylife.taobaoer.core.utils.TaobaoUtils;
import com.easylife.taobaoer.core.utils.UIUtil;
import com.easylife.taobaoer.core.widget.image.ImageLoaderCallback;
import com.easylife.taobaoer.core.widget.image.ImageViewLoader;
import com.easylife.taobaoer.detail.model.GoodsDetail;
import com.easylife.taobaoer.detail.service.IGoodsDetailService;


public class GoodsDetailService implements IGoodsDetailService {
	private final String GOODS_DETAIL_URI = "1.1/twitter/single";
	private final String CLIENT_ID = "10009";
	@Override
	public GoodsDetail getGoodsDetail(Context context,String twitter_goods_id,String twitter_id) {
		ApplicationContext config = (ApplicationContext) context
				.getApplicationContext();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("twitter_id", twitter_id);
		values.put("goods_id", twitter_goods_id);
		values.put("access_token", config.getToken().getAccess_token());
		values.put("imei", TaobaoUtils.getDeviceToken(context));
		values.put("mac", TaobaoUtils.getMac(context));
		values.put("qudaoid", CLIENT_ID);
		ResponseEntity<GoodsDetail> responseEntity = null;
		try {
			responseEntity = HttpUtils.get(context, GOODS_DETAIL_URI, values,
					GoodsDetail.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return responseEntity.getBody();
	}
	
	@Override
	public void showBigPostImage(final Context context, String pic_url,
			final ImageView imageView,final int screenWidth ) {
		if (StringUtils.hasText(pic_url)) {
			imageView.setVisibility(View.VISIBLE);
			ImageViewLoader nid = ImageViewLoader.getInstance(context);
			nid.fetchImage(pic_url,
					R.drawable.loading_big, imageView,
					new ImageLoaderCallback() {
						@Override
						public void imageLoaderFinish(Bitmap bitmap) {
							if (bitmap != null) {
								imageView.setImageBitmap( ImageUtils
										.zoomWidthBitmapNotCut(bitmap,screenWidth, 0,
												context));
							}
						}
					});
		} else {
			imageView.setVisibility(View.GONE);
		}
	}
}
