package com.easylife.taobaoer.product.adapter;

import org.springframework.util.StringUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.utils.ImageUtils;
import com.easylife.taobaoer.core.widget.image.ImageLoaderCallback;
import com.easylife.taobaoer.core.widget.image.ImageViewLoader;
import com.easylife.taobaoer.core.widget.list.PageAdapter;
import com.easylife.taobaoer.product.model.Product;

public class ProductListAdapter extends PageAdapter<Product> {

	public ProductListAdapter(Context mContext) {
		super(mContext);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Product product = data.getDatas().get(position);
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_product, null);
			holder = new ViewHolder();
			holder.itemImage = (ImageView) convertView
					.findViewById(R.id.item_image);
			holder.itemContent = (TextView) convertView
					.findViewById(R.id.item_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final ImageView itemImage = holder.itemImage;
		TextView itemContent = holder.itemContent;
		if (StringUtils.hasText(product.getJ_pic_url())) {
			ImageViewLoader nid = ImageViewLoader.getInstance(mContext);
			nid.fetchImage(product.getJ_pic_url(), 0, itemImage,
					new ImageLoaderCallback() {
						@Override
						public void imageLoaderFinish(Bitmap bitmap) {
							if (bitmap != null) {
								Bitmap zoomBitmap = ImageUtils.zoomCutMiddle(
										bitmap, 100, 100, mContext);
								itemImage.setImageBitmap(zoomBitmap);
							}
						}
					});
		}
		itemContent.setText(product.getTitle());
		return convertView;
	}

	private class ViewHolder {
		public ImageView itemImage;
		public TextView itemContent;
	}
}
