package com.easylife.taobaoer.home.activity;

import java.util.List;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.category.activity.CategoryGrandchildrenActivity;
import com.easylife.taobaoer.category.model.CatProduct;
import com.easylife.taobaoer.category.task.CatProductListGetDataTask;
import com.easylife.taobaoer.core.utils.DbCollect;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnListView.OnLoadMoreListener;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView.OnRefreshListener;
import com.easylife.taobaoer.core.widget.waterfall.internal.PLA_AdapterView;
import com.easylife.taobaoer.core.widget.waterfall.internal.PLA_AdapterView.OnItemClickListener;
import com.easylife.taobaoer.detail.activity.DetailActivity;
import com.easylife.taobaoer.detail.adapter.FavItemAdapter;
import com.easylife.taobaoer.product.model.Product;
import com.easylife.taobaoer.product.task.FavProductListGetDataTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class FavActivity extends Activity {

	private FavItemAdapter mAdapter;
	private MultiColumnPullToRefreshListView favListView=null;
	private Button backButton=null;
	private TextView favText=null;
	private int page=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_fav);
	}

	@Override
	protected void onStart() {
		super.onStart();
		favListView = (MultiColumnPullToRefreshListView) findViewById(R.id.favList);
		favText = (TextView) findViewById(R.id.favType);
		favText.setText(R.string.favText);
		mAdapter = new FavItemAdapter(this);
		favListView.setAdapter(mAdapter);
		
		
		favListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(PLA_AdapterView<?> parent, View view, int position,
					long id) {
               Product product = (Product) mAdapter.getItem(position);
				
				Intent intent = new Intent();
				intent.putExtra("twitter_goods_id", product.getTwitter_goods_id());
				intent.putExtra("twitter_id", product.getTwitter_id());
				intent.setClass(FavActivity.this, DetailActivity.class);
				FavActivity.this.startActivity(intent);
				
			}
		});
	
		favListView.setOnLoadMoreListener(new OnLoadMoreListener() {
		@Override
		public void onLoadMore() {
			page += 1;
			// 到底部自动获取数据
			new FavProductListGetDataTask(FavActivity.this,favListView,mAdapter).execute(page);
		}
	});
		favListView.setOnRefreshListener(new OnRefreshListener() {
		@Override
		public void onRefresh() {
			new FavProductListGetDataTask(FavActivity.this,favListView, mAdapter).execute(1);
		}
	});
		favListView.setRefreshing();
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
