package com.easylife.taobaoer.category.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.category.adapter.CategoryParentAdapter;
import com.easylife.taobaoer.category.task.CommonData;

public class CategoryActivity extends Activity {
	
	private GridView gridView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_category_parent);
		gridView = (GridView) findViewById(R.id.catGridView);
		CategoryParentAdapter  catAdapter = new CategoryParentAdapter(this,CommonData.getBaseData(this));
		gridView.setAdapter(catAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				Intent intent = new Intent();
				intent.putExtra("parent_type", position+"");
				intent.setClass(CategoryActivity.this,CategoryChildrenActivity.class);
				CategoryActivity.this.startActivity(intent);
				
			}
		});
	}
}