package com.easylife.taobaoer.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import android.content.Context;

import com.easylife.taobaoer.core.model.Code;
import com.easylife.taobaoer.core.model.Token;
import com.easylife.taobaoer.core.service.ITaobaoService;
import com.easylife.taobaoer.core.utils.HttpUtils;
import com.easylife.taobaoer.core.utils.TaobaoUtils;

public class TaobaoService implements ITaobaoService {
	private final String OAUTH_CODE_URI = "1.0/oauth/authorize";
	private final String OAUTH_TOKEN_URI = "1.0/oauth/access_token";
	private final String CLIENT_ID = "10009";

	@Override
	public Code getMeilishuoCode(Context context) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("response_type", "code");
		values.put("client_id", CLIENT_ID);
		values.put("redirect_uri", "");
		values.put("imei", TaobaoUtils.getDeviceToken(context));
		values.put("mac", TaobaoUtils.getMac(context));
		values.put("qudaoid", CLIENT_ID);
		ResponseEntity<Code> responseEntity = null;
		try {
			responseEntity = HttpUtils.get(context, OAUTH_CODE_URI, values,
					Code.class);
		} catch (Exception e) {
			return null;
		}
		return responseEntity.getBody();
	}

	@Override
	public Token getMeilishuoToken(Context context, Code code) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("grant_type", "authorization_code");
		values.put("code", code.getCode());
		values.put("client_id", CLIENT_ID);
		values.put("redirect_uri", "");
		values.put("imei", TaobaoUtils.getDeviceToken(context));
		values.put("mac", TaobaoUtils.getMac(context));
		values.put("qudaoid", CLIENT_ID);
		ResponseEntity<Token> responseEntity = null;
		try {
			responseEntity = HttpUtils.get(context, OAUTH_TOKEN_URI, values,
					Token.class);
		} catch (Exception e) {
			return null;
		}
		return responseEntity.getBody();
	}

}
