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
import com.easylife.taobaoer.core.widget.list.pullrefresh.PullToRefreshBase.Mode;

public abstract class GetDataTask<T extends Result<? extends PageList<E>>, E extends Entity>
		extends AsyncTask<Object, Integer, T> {

	protected JuzhaiRefreshGirdView refreshListView;

	protected Context context;

	public GetDataTask(Context context, JuzhaiRefreshGirdView refreshListView) {
		this.context = context;
		this.refreshListView = refreshListView;
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
			PageAdapter<E> adapter = refreshListView.getPageAdapter();
			adapter.setPager(pager);
			List<E> list = result.getResult().getList();
			if (pager.getCurrentPage() == 1) {
				adapter.setDatas(list);
			} else {
				adapter.pushDatas(list);
			}
			loadComplete();
		}
		if (null != refreshListView) {
			refreshListView.onRefreshComplete();
			if (null != result && result.getSuccess()) {
				refreshListView.setMode(result.getResult().getPager()
						.getHasNext() ? Mode.BOTH : Mode.PULL_DOWN_TO_REFRESH);
			}
		}
		super.onPostExecute(result);
	}

	protected void loadComplete() {
	}
}
