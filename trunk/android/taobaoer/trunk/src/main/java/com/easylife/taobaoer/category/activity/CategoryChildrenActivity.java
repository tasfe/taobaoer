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
import com.easylife.taobaoer.category.model.CatInfo;
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

						CatInfo catInfo = null;
						if (CategoryEnum.PEISHI.getId() == catEnum.getId()) {
							catInfo = catDataList.getData().get(1)
									.getChildren().get(position).getInfo();
						} else {
							catInfo = catDataList.getData().get(0)
									.getChildren().get(position).getInfo();
						}
						if (catInfo != null) {

							Intent intent = new Intent();
							intent.putExtra("children_tpye_id", catInfo.getNid());
							intent.putExtra("children_tpye_name",
									catInfo.getWord_name());
							intent.putExtra("children_tpye_method", catInfo.getUrl());
							
							intent.setClass(CategoryChildrenActivity.this,
									CategoryGrandchildrenActivity.class);
							CategoryChildrenActivity.this.startActivity(intent);

						}

					}
				});

			}

			@Override
			public String doInBackground() {

				catDataList = categoryService.getCatChildrenList(
						CategoryChildrenActivity.this, 1, catEnum.getTypeId());
				return null;
			}
		}, false).execute();

	}

}