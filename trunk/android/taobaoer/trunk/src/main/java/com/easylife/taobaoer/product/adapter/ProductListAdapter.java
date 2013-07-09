package com.easylife.taobaoer.product.adapter;

import org.springframework.util.StringUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.utils.ImageUtils;
import com.easylife.taobaoer.core.widget.image.ImageLoaderCallback;
import com.easylife.taobaoer.core.widget.image.ImageViewLoader;
import com.easylife.taobaoer.core.widget.list.PageAdapter;
import com.easylife.taobaoer.product.model.Product;

public class ProductListAdapter extends PageAdapter<Product> {
	private boolean mBusy = false;

	public void setFlagBusy(boolean busy) {
		this.mBusy = busy;
	}

	public ProductListAdapter(Context mContext) {
		super(mContext);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Product product = data.getDatas().get(position);
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_product, null);
			holder = new ViewHolder();
			holder.itemImage = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.itemContent = (TextView) convertView
					.findViewById(R.id.item_content);
			holder.itemPrice = (TextView) convertView
					.findViewById(R.id.item_price);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final LinearLayout layout = (LinearLayout) convertView;
		final ImageView itemImage = holder.itemImage;
		itemImage.setImageBitmap(null);
		TextView itemContent = holder.itemContent;
		TextView itemPrice = holder.itemPrice;
		if (StringUtils.hasText(product.getPic_url())) {
			ImageViewLoader nid = ImageViewLoader.getInstance(mContext);
			if (!mBusy) {
				nid.fetchImage(product.getPic_url(), R.drawable.loading_small,
						itemImage, new ImageLoaderCallback() {
							@Override
							public void imageLoaderFinish(Bitmap bitmap) {
								if (bitmap != null) {
									if (layout.getWidth() > 0) {
										bitmap = ImageUtils
												.zoomWidthCutMiddlePx(
														bitmap,
														layout.getWidth(),
														product.getPic_height(),
														mContext);
									}
									itemImage.setImageBitmap(bitmap);
								}
							}
						});
			}
		}
		itemContent.setText(product.getRemark());
		itemPrice.setText(product.getTitle());
		return convertView;
	}

	private class ViewHolder {
		public ImageView itemImage;
		public TextView itemContent;
		public TextView itemPrice;
	}

}
