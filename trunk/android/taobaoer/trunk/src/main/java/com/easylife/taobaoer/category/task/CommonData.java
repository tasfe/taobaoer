package com.easylife.taobaoer.category.task;

import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import com.easylife.taobaoer.category.model.CatParentInfo;
import com.easylife.taobaoer.core.utils.JacksonSerializer;
import android.content.Context;
import android.util.Log;

public class CommonData {

	public static final String SHARED_PREFERNCES_BASEDATA = "cat_type_data";
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static List<CatParentInfo> result = null;

	public static List<CatParentInfo> getBaseData(Context context) {
		if (result == null) {
			SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(
					context);
			String str = sharedPreferencesManager
					.getString(SHARED_PREFERNCES_BASEDATA);
			// 没有获取到数据使用离线数据
			if (str == null || "".equals(str)) {
				try {
					result = objectMapper.readValue(
							context.getAssets().open("category.config"),
							new TypeReference<List<CatParentInfo>>() {
							});
					String jsonStr = JacksonSerializer.toString(result);
					sharedPreferencesManager.commit(SHARED_PREFERNCES_BASEDATA,
							jsonStr);
					return result;
				} catch (Exception e) {
				}
			}
			try {
				result = new ObjectMapper().readValue(str,
						new TypeReference<List<CatParentInfo>>() {
						});
			} catch (Exception e) {
				Log.d("baseData", "json to basedata is error", e);
			}
		}
		return result;
	}

	public static void writeBaseData(Context context, List<CatParentInfo> result) {
		if (result != null && result.size() > 0) {
			try {
				SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(
						context);
				String jsonStr = JacksonSerializer.toString(result);
				sharedPreferencesManager.commit(SHARED_PREFERNCES_BASEDATA,
						jsonStr);
			} catch (Exception e) {
			}
		}

	}

}
