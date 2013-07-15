package com.easylife.taobaoer.home.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.category.activity.CategoryActivity;
import com.easylife.taobaoer.category.activity.CategoryChildrenActivity;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnListView.OnLoadMoreListener;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView.OnRefreshListener;
import com.easylife.taobaoer.core.widget.waterfall.internal.PLA_AdapterView;
import com.easylife.taobaoer.detail.activity.DetailActivity;
import com.easylife.taobaoer.product.adapter.ProductListAdapter;
import com.easylife.taobaoer.product.model.Product;
import com.easylife.taobaoer.product.task.ProductListMultiColumnGetDataTask;

public class NewIndexActivity extends Activity {
	private int page = 1;
	private MultiColumnPullToRefreshListView mPulltoRefreshListView = null;
	private ProductListAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		mPulltoRefreshListView.setOnItemClickListener(new PLA_AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(PLA_AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				List<Product> productList = mAdapter.getProductList();
				Product product =  productList.get(position);
				Intent intent = new Intent();
				intent.putExtra("twitter_goods_id", String.valueOf(product.getTwitter_goods_id()));
				intent.putExtra("twitter_id", String.valueOf(product.getTwitter_id()));
				intent.setClass(NewIndexActivity.this,DetailActivity.class);
				NewIndexActivity.this.startActivity(intent);
			}

		
		});
	}

	protected void init() {
		setContentView(R.layout.page_new_index);
		mAdapter = new ProductListAdapter(this);
		mPulltoRefreshListView = (MultiColumnPullToRefreshListView) findViewById(R.id.list);
		mPulltoRefreshListView.setAdapter(mAdapter);
		mPulltoRefreshListView.setOnLoadMoreListener(new OnLoadMoreListener() {
			@Override
			public void onLoadMore() {
				page += 1;
				// 到底部自动获取数据
				new ProductListMultiColumnGetDataTask(NewIndexActivity.this,
						mPulltoRefreshListView, mAdapter).execute(page);
			}
		});
		mPulltoRefreshListView.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				new ProductListMultiColumnGetDataTask(NewIndexActivity.this,
						mPulltoRefreshListView, mAdapter).execute(1);
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