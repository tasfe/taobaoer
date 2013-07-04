package com.easylife.taobaoer.product.task;

import android.content.Context;

import com.easylife.taobaoer.core.model.PageList;
import com.easylife.taobaoer.core.model.Pager;
import com.easylife.taobaoer.core.model.Result.ProductListResult;
import com.easylife.taobaoer.core.widget.list.GetDataTask;
import com.easylife.taobaoer.core.widget.list.JuzhaiRefreshGirdView;
import com.easylife.taobaoer.product.model.Product;
import com.easylife.taobaoer.product.model.ProductList;
import com.easylife.taobaoer.product.service.IProductService;
import com.easylife.taobaoer.product.service.impl.ProductService;

public class ProductListGetDataTask extends
		GetDataTask<ProductListResult, Product> {

	public ProductListGetDataTask(Context context,
			JuzhaiRefreshGirdView refreshListView) {
		super(context, refreshListView);
	}

	@Override
	protected ProductListResult doInBackground(Object... params) {
		ProductListResult result = new ProductListResult();
		IProductService productService = new ProductService();
		int page = (Integer) params[0];
		try {
			ProductList products = productService.getHotProductList(context,
					page);
			PageList<Product> pageList = new PageList<Product>();
			pageList.setList(products.getData());
			Pager pager = new Pager();
			pager.setCurrentPage(page);
			pager.setHasNext(true);
			pageList.setPager(pager);
			result.setSuccess(true);
			result.setResult(pageList);
		} catch (Exception e) {

		}
		return result;
	}
}