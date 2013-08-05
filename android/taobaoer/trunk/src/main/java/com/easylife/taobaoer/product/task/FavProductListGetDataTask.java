package com.easylife.taobaoer.product.task;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.easylife.taobaoer.core.model.PageList;
import com.easylife.taobaoer.core.model.Pager;
import com.easylife.taobaoer.core.model.Result.ProductListResult;
import com.easylife.taobaoer.core.utils.DbCollect;
import com.easylife.taobaoer.core.widget.list.MultiColumnGetDataTask;
import com.easylife.taobaoer.core.widget.list.PageAdapter;
import com.easylife.taobaoer.core.widget.waterfall.MultiColumnPullToRefreshListView;
import com.easylife.taobaoer.product.model.Product;

public class FavProductListGetDataTask extends MultiColumnGetDataTask<ProductListResult, Product> {

	
	protected MultiColumnPullToRefreshListView refreshListView;

	protected Context context;

	protected PageAdapter mAdapter;

	
	public FavProductListGetDataTask(Context context,
			MultiColumnPullToRefreshListView refreshListView,
			PageAdapter mAdapter) {
		super(context, refreshListView, mAdapter);
		this.refreshListView=refreshListView;
		this.context=context;
		this.mAdapter=mAdapter;
	}

	@Override
	protected ProductListResult doInBackground(Object... params) {
		ProductListResult productListResult=new ProductListResult();
		DbCollect dbCollect=new DbCollect(context);
		int page = (Integer) params[0];
		int pageNumber=dbCollect.getPageNumProduct();
		List<Product> productList=new ArrayList<Product>();
		PageList<Product> pageList=new PageList<Product>();
		Pager pager = new Pager();
		Log.e("pageNumber>=page",(pageNumber>=page)+"");
		Log.e("pageNumber", pageNumber+"");
		Log.e("page", page+"");
		if(pageNumber>=page){
			productList=dbCollect.pageSelectproduct(page);
			pageList=new PageList<Product>();
			pageList.setList(productList);
			pager.setHasNext(true);//是否还能翻页
		}else{
			pager.setHasNext(false);//是否还能翻页
		}
		pager.setCurrentPage(page);
		pageList.setPager(pager);
		productListResult.setSuccess(true);
		Log.e("pageList.size()", pageList.getList().size()+"");
		productListResult.setResult(pageList);
		return productListResult;
	}

}
