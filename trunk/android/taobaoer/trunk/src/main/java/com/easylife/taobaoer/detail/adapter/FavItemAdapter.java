package com.easylife.taobaoer.detail.adapter;

import org.springframework.util.StringUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.utils.ImageUtils;
import com.easylife.taobaoer.core.widget.image.ImageLoaderCallback;
import com.easylife.taobaoer.core.widget.image.ImageViewLoader;
import com.easylife.taobaoer.core.widget.list.PageAdapter;
import com.easylife.taobaoer.product.model.Product;


public class FavItemAdapter extends PageAdapter<Product>{

	private boolean mBusy = false;

	public void setFlagBusy(boolean mBusy) {
		this.mBusy = mBusy;
	}
	
	public FavItemAdapter(Context mContext) {
		super(mContext);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Product product = data.getDatas().get(position);
		ViewHolder holder = null;
		//判断convertView是否已设置布局，如果为空则设置布局
		//第一次取值时需要新建布局，后面只需要取第一次创建的布局
		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.fav_night_item, null);
			holder = new ViewHolder();
			holder.itemImage = (ImageView) convertView
					.findViewById(R.id.item_fav_image);
			holder.itemText = (TextView) convertView.findViewById(R.id.item_fav_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//获得布局
		final RelativeLayout layout = (RelativeLayout) convertView;
		final ImageView itemImage = holder.itemImage;
		//清空原布局中图片
		itemImage.setImageBitmap(null);
		TextView itemContent = holder.itemText;
		
		//判断其是否为文本
		if (StringUtils.hasText(product.getPic_url())) {
			ImageViewLoader nid = ImageViewLoader.getInstance(mContext);
			if (!mBusy) {
				nid.fetchImage(product.getPic_url(),
						R.drawable.loading_small, itemImage,
						new ImageLoaderCallback() {
							@Override
							public void imageLoaderFinish(Bitmap bitmap) {
								if (bitmap != null) {
									if (layout.getWidth() > 0) {
										Log.e("product.getPic_height()",product.getPic_height()+"");
										bitmap = ImageUtils.zoomWidthCutMiddlePx(bitmap,layout.getWidth(),product.getPic_height(),mContext);
									}
									itemImage.setImageBitmap(bitmap);
								}
							}
						});
			}
		}
		itemContent.setText(product.getTitle());
		return convertView;
	}
	
	
	private class ViewHolder {
		public ImageView itemImage;
		public TextView itemText;
	}
}
