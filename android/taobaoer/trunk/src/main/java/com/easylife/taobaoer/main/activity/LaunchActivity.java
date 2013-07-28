package com.easylife.taobaoer.main.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.category.model.CatDataList;
import com.easylife.taobaoer.category.model.CatInfo;
import com.easylife.taobaoer.category.model.CatParentInfo;
import com.easylife.taobaoer.category.model.CategoryEnum;
import com.easylife.taobaoer.category.service.impl.CategoryServiceImpl;
import com.easylife.taobaoer.category.task.CommonData;
import com.easylife.taobaoer.category.task.SharedPreferencesManager;
import com.easylife.taobaoer.core.ApplicationContext;
import com.easylife.taobaoer.core.model.Code;
import com.easylife.taobaoer.core.model.Token;
import com.easylife.taobaoer.core.service.ITaobaoService;
import com.easylife.taobaoer.core.service.impl.TaobaoService;
import com.easylife.taobaoer.core.widget.image.ImageViewLoader;

public class LaunchActivity extends Activity {
	ITaobaoService taobaoService = new TaobaoService();
	CategoryServiceImpl categoryService = new CategoryServiceImpl();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_launch);
		// 获取token
		new AsyncTask<Void, Void, List<CatParentInfo>>() {

			@Override
			protected List<CatParentInfo> doInBackground(Void... params) {
				Code code = taobaoService.getMeilishuoCode(LaunchActivity.this);
				Token token = taobaoService.getMeilishuoToken(
						LaunchActivity.this, code);
				ApplicationContext app = (ApplicationContext) LaunchActivity.this
						.getApplicationContext();
				app.setToken(token);
				
				List<CatParentInfo> list = null;
				/*try {
					CatParentInfo shangyi = getCatType(CategoryEnum.SHANGYI.getTypeId(), 0, CategoryEnum.SHANGYI.getName());
					CatParentInfo qunzi = getCatType(CategoryEnum.QUNZI.getTypeId(), 1, CategoryEnum.QUNZI.getName());
					CatParentInfo kuzi = getCatType(CategoryEnum.KUZI.getTypeId(), 2, CategoryEnum.KUZI.getName());
					CatParentInfo xiezi = getCatType(CategoryEnum.XIEZI.getTypeId(), 3, CategoryEnum.XIEZI.getName());
					CatParentInfo baobao = getCatType(CategoryEnum.BAOBAO.getTypeId(), 4, CategoryEnum.BAOBAO.getName());
					CatParentInfo peishi = getCatType(CategoryEnum.PEISHI.getTypeId(), 5, CategoryEnum.PEISHI.getName());
					if(shangyi != null && qunzi != null && kuzi != null && xiezi != null){
						list = new ArrayList<CatParentInfo>();
						list.add(shangyi);
						list.add(qunzi);
						list.add(kuzi);
						list.add(xiezi);
						list.add(baobao);
						list.add(peishi);
					}
					
				} catch (Exception e) {
					Log.e("LaunchActivity.onPostExecute", e.toString());
				}*/
				
				ImageViewLoader.getInstance(LaunchActivity.this).clearCache();
				
				return list;
			}

			protected void onPostExecute(List<CatParentInfo> list) {
				/*if (list != null && list.size() > 0) {
					CommonData.writeBaseData(LaunchActivity.this, list);
				}else{
					SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(LaunchActivity.this);
					sharedPreferencesManager.delete(CommonData.SHARED_PREFERNCES_BASEDATA);
				}*/
				
				startActivity(new Intent(LaunchActivity.this,
						MainTabActivity.class));
				finish();
			};

		}.execute();
		
	}
	
	private CatParentInfo getCatType(int parentTypeId,int typeId,String typeName){
		
		CatParentInfo catParentInfo = null;
		try{
			CatDataList catList = categoryService.getCatChildrenList(LaunchActivity.this, 1, parentTypeId);
			
			CatInfo info = null;
			if(CategoryEnum.PEISHI.getName().equals(typeName)){
				info = catList.getData().get(1).getChildren().get(0).getInfo();
			}else{
				info = catList.getData().get(0).getChildren().get(0).getInfo();
			}
			if(info != null){
				catParentInfo = new CatParentInfo();
				catParentInfo.setTypeId(typeId);
				catParentInfo.setImgulr(info.getPic());
				catParentInfo.setTypeName(typeName);
			}
			
		}catch(Exception e){
			return null;
		}
		return catParentInfo;
	}
}
