package com.easylife.taobaoer.category.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.category.adapter.CategoryChildrenAdapter;
import com.easylife.taobaoer.category.model.CatDataList;
import com.easylife.taobaoer.category.model.CategoryEnum;
import com.easylife.taobaoer.category.service.impl.CategoryServiceImpl;
import com.easylife.taobaoer.core.task.ProgressTask;
import com.easylife.taobaoer.core.task.TaskCallback;

public class CategoryChildrenActivity extends Activity {

	private GridView gridView;
	private Button backButton;
	private TextView parentText;
	private CatDataList catDataList;
	CategoryServiceImpl categoryService = new CategoryServiceImpl();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_category_children);
		gridView = (GridView) findViewById(R.id.catChildrenGridView);
		backButton = (Button) findViewById(R.id.catBackButton);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		parentText = (TextView) findViewById(R.id.parentType);

		Intent intent = this.getIntent();
		int parent_type = Integer
				.parseInt(intent.getStringExtra("parent_type"));

		final CategoryEnum catEnum = CategoryEnum.getCategoryEnum(parent_type);

		parentText.setText(catEnum.getName());

		new ProgressTask(CategoryChildrenActivity.this, new TaskCallback() {

			@Override
			public void successCallback() {
				if (catDataList == null) {
					return;
				}

				CategoryChildrenAdapter catAdapter = new CategoryChildrenAdapter(
						CategoryChildrenActivity.this, catDataList, catEnum);
				gridView.setAdapter(catAdapter);
				gridView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// TODO Auto-generated method stub

					}
				});

			}

			@Override
			public String doInBackground() {

				catDataList = categoryService.getCatProductList(
						CategoryChildrenActivity.this, 1, catEnum.getTypeId());
				return null;
			}
		}, false).execute();

	}

}