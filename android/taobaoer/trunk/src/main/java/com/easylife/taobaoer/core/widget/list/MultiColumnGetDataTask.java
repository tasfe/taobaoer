package com.easylife.taobaoer.core.widget.list;

import java.util.List;

import org.springframework.util.StringUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.model.Entity;
import com.easylife.taobaoer.core.model.PageList;
import com.easylife.taobaoer.core.model.Pager;
import com.easylife.taobaoer.core.model.Result;
import com.easylife.taobaoer.core.utils.DialogUtils;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView;

public abstract class MultiColumnGetDataTask<T extends Result<? extends PageList<E>>, E extends Entity>
		extends AsyncTask<Object, Integer, T> {

	protected MultiColumnPullToRefreshListView refreshListView;

	protected Context context;

	protected PageAdapter mAdapter;

	public MultiColumnGetDataTask(Context context,
			MultiColumnPullToRefreshListView refreshListView,
			PageAdapter mAdapter) {
		this.context = context;
		this.refreshListView = refreshListView;
		this.mAdapter = mAdapter;
	}

	protected void onPostExecute(T result) {
		if (null == result
				|| (!result.getSuccess() && !StringUtils.hasText(result
						.getErrorInfo()))) {
			DialogUtils.showErrorDialog(refreshListView.getContext(),
					R.string.no_network);
		} else if (!result.getSuccess()) {
			DialogUtils.showErrorDialog(refreshListView.getContext(),
					result.getErrorInfo());
		} else {
			// add or override
			Pager pager = result.getResult().getPager();
			mAdapter.setPager(pager);
			List<E> list = result.getResult().getList();
			if (pager.getCurrentPage() == 1) {
				mAdapter.setDatas(list);
				refreshListView.onRefreshComplete();
			} else {
				mAdapter.pushDatas(list);
				refreshListView.onLoadMoreComplete();
			}
		}
		super.onPostExecute(result);
	}

	protected void loadComplete() {
	}

}
