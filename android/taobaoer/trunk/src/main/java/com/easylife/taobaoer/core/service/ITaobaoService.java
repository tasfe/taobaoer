package com.easylife.taobaoer.core.service;

import android.content.Context;

import com.easylife.taobaoer.core.model.Code;

public interface ITaobaoService {
	/**
	 * 获取美丽说code
	 * 
	 * @return
	 */
	Code getMeilishuoCode(Context context);
}
