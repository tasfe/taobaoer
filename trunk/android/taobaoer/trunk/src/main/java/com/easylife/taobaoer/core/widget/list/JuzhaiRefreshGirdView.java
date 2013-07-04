package com.easylife.taobaoer.core.widget.list;

import android.content.Context;
import android.util.AttributeSet;

import com.easylife.taobaoer.core.model.Entity;
import com.easylife.taobaoer.core.widget.list.pullrefresh.PullToRefreshGridView;

public class JuzhaiRefreshGirdView extends PullToRefreshGridView {

	private PageAdapter<? extends Entity> adapter;

	public JuzhaiRefreshGirdView(Context context) {
		super(context);
		init();
	}

	public JuzhaiRefreshGirdView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public JuzhaiRefreshGirdView(Context context, Mode mode) {
		super(context, mode);
		init();
	}

	private void init() {
		setShowIndicator(false);
	}

	public void manualRefresh() {
		this.setRefreshing();
	}

	public <T extends Entity> void setAdapter(PageAdapter<T> adapter) {
		this.adapter = adapter;
		super.setAdapter(adapter);
	}

	@SuppressWarnings("unchecked")
	public <T extends Entity> PageAdapter<T> getPageAdapter() {
		return (PageAdapter<T>) adapter;
	}
}
