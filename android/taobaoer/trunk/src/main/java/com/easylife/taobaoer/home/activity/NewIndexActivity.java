package com.easylife.taobaoer.home.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnListView.OnLoadMoreListener;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView.OnRefreshListener;
import com.easylife.taobaoer.product.adapter.ProductListAdapter;
import com.easylife.taobaoer.product.task.ProductListMultiColumnGetDataTask;

public class NewIndexActivity extends Activity {
	private int page = 1;
	private MultiColumnPullToRefreshListView mPulltoRefreshListView = null;
	private ProductListAdapter mAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
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