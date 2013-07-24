package com.easylife.taobaoer.category.task;

import android.content.Context;
import android.util.Log;

import com.easylife.taobaoer.category.model.CatParams;
import com.easylife.taobaoer.category.model.CatProduct;
import com.easylife.taobaoer.category.model.CatProductList;
import com.easylife.taobaoer.category.service.ICategoryService;
import com.easylife.taobaoer.category.service.impl.CategoryServiceImpl;
import com.easylife.taobaoer.category.utils.StringUtils;
import com.easylife.taobaoer.core.model.PageList;
import com.easylife.taobaoer.core.model.Pager;
import com.easylife.taobaoer.core.model.Result.CatProductListResult;
import com.easylife.taobaoer.core.widget.list.MultiColumnGetDataTask;
import com.easylife.taobaoer.core.widget.list.PageAdapter;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView;

public class CatProductListGetDataTask extends
		MultiColumnGetDataTask<CatProductListResult, CatProduct> {

	private int childrenTypeId;

	private String childrenTypeMethod;

	public CatProductListGetDataTask(Context context,
			MultiColumnPullToRefreshListView refreshListView,
			PageAdapter<?> mAdapter, int childrenTypeId, String childrenTypeMethod) {
		super(context, refreshListView, mAdapter);
		this.childrenTypeId = childrenTypeId;
		this.childrenTypeMethod = childrenTypeMethod;
	}

	@Override
	protected CatProductListResult doInBackground(Object... params) {
		CatProductListResult result = new CatProductListResult();
		ICategoryService categoryService = new CategoryServiceImpl();
		int page = (Integer) params[0];
		try {
			CatProductList catProductList = null;

			CatParams catParams = StringUtils
					.analyzeInfoUrl(childrenTypeMethod);
			if (catParams == null) {
				catProductList = categoryService.getCatGrandChildrenList(
						context, page, childrenTypeId);
			} else {
				catProductList = categoryService.getCatGrandChildrenList(
						context, page, catParams);
			}

			PageList<CatProduct> pageList = new PageList<CatProduct>();
			pageList.setList(catProductList.getData());
			Pager pager = new Pager();
			pager.setCurrentPage(page);
			pager.setHasNext(true);
			pageList.setPager(pager);
			result.setSuccess(true);
			result.setResult(pageList);
		} catch (Exception e) {
			Log.e("CatProductListGetDataTask.CatProductListResult",
					e.toString());
		}
		return result;
	}

}
