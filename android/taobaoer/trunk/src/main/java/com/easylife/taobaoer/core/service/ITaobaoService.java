package com.easylife.taobaoer.core.service;

import android.content.Context;

import com.easylife.taobaoer.core.model.Code;
import com.easylife.taobaoer.core.model.Token;

public interface ITaobaoService {
	/**
	 * 获取美丽说code
	 * 
	 * @return
	 */
	Code getMeilishuoCode(Context context);

	/**
	 * 获取美丽说token
	 * 
	 * @param context
	 * @param code
	 * @return
	 */
	Token getMeilishuoToken(Context context, Code code);
}
