package com.easylife.taobaoer.category.adapter;

import org.springframework.util.StringUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.category.model.CatProduct;
import com.easylife.taobaoer.core.utils.ImageUtils;
import com.easylife.taobaoer.core.widget.image.ImageLoaderCallback;
import com.easylife.taobaoer.core.widget.image.ImageViewLoader;
import com.easylife.taobaoer.core.widget.list.PageAdapter;

public class CategoryGrandChildrenAdapter extends PageAdapter<CatProduct> {

	private boolean mBusy = false;

	public void setFlagBusy(boolean mBusy) {
		this.mBusy = mBusy;
	}

	public CategoryGrandChildrenAdapter(Context mContext) {
		super(mContext);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final CatProduct catProduct = data.getDatas().get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.item_category_grandchildren, null);
			holder = new ViewHolder();
			holder.itemImage = (ImageView) convertView
					.findViewById(R.id.item_grandchildren_image);
			holder.itemText = (TextView) convertView.findViewById(R.id.item_grandchildren_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final RelativeLayout layout = (RelativeLayout) convertView;
		final ImageView itemImage = holder.itemImage;
		itemImage.setImageBitmap(null);
		TextView itemContent = holder.itemText;
		if (StringUtils.hasText(catProduct.getPic_url())) {
			ImageViewLoader nid = ImageViewLoader.getInstance(mContext);
			if (!mBusy) {
				nid.fetchImage(catProduct.getPic_url(),
						R.drawable.loading_small, itemImage,
						new ImageLoaderCallback() {
							@Override
							public void imageLoaderFinish(Bitmap bitmap) {
								if (bitmap != null) {
									if (layout.getWidth() > 0) {
										bitmap = ImageUtils
												.zoomWidthCutMiddlePx(
														bitmap,
														layout.getWidth(),
														catProduct
																.getPic_height(),
														mContext);
									}
									itemImage.setImageBitmap(bitmap);
								}
							}
						});
			}
		}
		itemContent.setText(catProduct.getTitle());
		return convertView;
	}

	private class ViewHolder {
		public ImageView itemImage;
		public TextView itemText;
	}

}
