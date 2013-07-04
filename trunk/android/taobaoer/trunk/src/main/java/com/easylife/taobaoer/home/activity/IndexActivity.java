package com.easylife.taobaoer.home.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.widget.list.JuzhaiRefreshGirdView;
import com.easylife.taobaoer.core.widget.list.pullrefresh.PullToRefreshBase;
import com.easylife.taobaoer.core.widget.list.pullrefresh.PullToRefreshBase.OnRefreshListener2;
import com.easylife.taobaoer.product.adapter.ProductListAdapter;
import com.easylife.taobaoer.product.task.ProductListGetDataTask;

public class IndexActivity extends Activity {
	JuzhaiRefreshGirdView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_index);
		listView = (JuzhaiRefreshGirdView) findViewById(R.id.grid_view);
		listView.setOnRefreshListener(new OnRefreshListener2<GridView>() {
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<GridView> refreshView) {
				super.onPullDownToRefresh(refreshView);
				new ProductListGetDataTask(IndexActivity.this, listView)
						.execute(1);
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<GridView> refreshView) {
				super.onPullUpToRefresh(refreshView);
				new ProductListGetDataTask(IndexActivity.this, listView)
						.execute(listView.getPageAdapter().getPager()
								.getCurrentPage() + 1);
			}
		});
		listView.setAdapter(new ProductListAdapter(IndexActivity.this));
		listView.manualRefresh();
	}
}