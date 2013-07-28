package com.easylife.taobaoer.category.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.category.adapter.CategoryGrandChildrenAdapter;
import com.easylife.taobaoer.category.model.CatProduct;
import com.easylife.taobaoer.category.task.CatProductListGetDataTask;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnListView.OnLoadMoreListener;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView.OnRefreshListener;
import com.easylife.taobaoer.core.widget.waterfall.internal.PLA_AdapterView;
import com.easylife.taobaoer.core.widget.waterfall.internal.PLA_AdapterView.OnItemClickListener;
import com.easylife.taobaoer.detail.activity.DetailActivity;

public class CategoryGrandchildrenActivity extends Activity{

	private int page = 1;
	private MultiColumnPullToRefreshListView mPulltoRefreshListView = null;
	private CategoryGrandChildrenAdapter mAdapter;
	private Button backButton;
	private TextView childrenText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}
	
	protected void init() {
		setContentView(R.layout.page_category_grandchildren);
		Intent intent = this.getIntent();
		final int childrenTypeId = Integer.parseInt(intent.getStringExtra("children_tpye_id"));
		String childrenTypeName = intent.getStringExtra("children_tpye_name");
		final String childrenTypeMethod = intent.getStringExtra("children_tpye_method");
		
		backButton = (Button) findViewById(R.id.catChildrenBackButton);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		childrenText = (TextView) findViewById(R.id.childrenType);
		childrenText.setText(childrenTypeName);
		
		mAdapter = new CategoryGrandChildrenAdapter(this);
		mPulltoRefreshListView = (MultiColumnPullToRefreshListView) findViewById(R.id.grandChildrenList);
		mPulltoRefreshListView.setAdapter(mAdapter);
		mPulltoRefreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(PLA_AdapterView<?> parent, View view,
					int position, long id) {
				CatProduct catProduct = (CatProduct) mAdapter.getItem(position);
				
				Intent intent = new Intent();
				intent.putExtra("twitter_goods_id", catProduct.getTwitter_goods_id());
				intent.putExtra("twitter_id", catProduct.getTwitter_id());
				intent.setClass(CategoryGrandchildrenActivity.this, DetailActivity.class);
				CategoryGrandchildrenActivity.this.startActivity(intent);
				
			}
		});
		
		mPulltoRefreshListView.setOnLoadMoreListener(new OnLoadMoreListener() {
			@Override
			public void onLoadMore() {
				page += 1;
				// 到底部自动获取数据
				new CatProductListGetDataTask(CategoryGrandchildrenActivity.this,
						mPulltoRefreshListView, mAdapter,childrenTypeId,childrenTypeMethod).execute(page);
			}
		});
		mPulltoRefreshListView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				new CatProductListGetDataTask(CategoryGrandchildrenActivity.this,
						mPulltoRefreshListView, mAdapter,childrenTypeId,childrenTypeMethod).execute(1);
			}
		});
		mPulltoRefreshListView.setRefreshing();
	}
	
	OnScrollListener mScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_FLING:
				mAdapter.setFlagBusy(true);
				break;
			case OnScrollListener.SCROLL_STATE_IDLE:
				mAdapter.setFlagBusy(false);
				break;
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				mAdapter.setFlagBusy(false);
				break;
			default:
				break;
			}
			mAdapter.notifyDataSetChanged();
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

		}
	};
	
	
}
