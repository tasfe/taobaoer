package com.easylife.taobaoer.category.adapter;

import java.util.List;

import org.springframework.util.StringUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.category.model.CatChildren;
import com.easylife.taobaoer.category.model.CatParentInfo;
import com.easylife.taobaoer.core.utils.ImageUtils;
import com.easylife.taobaoer.core.utils.UIUtil;
import com.easylife.taobaoer.core.widget.image.ImageLoaderCallback;
import com.easylife.taobaoer.core.widget.image.ImageViewLoader;
import com.easylife.taobaoer.core.widget.list.PageAdapter;

public class CategoryParentAdapter extends PageAdapter<CatChildren> {
	
	private List<CatParentInfo> catParentList;

	public CategoryParentAdapter(Context mContext, List<CatParentInfo> catParentList) {
		super(mContext);
		this.catParentList = catParentList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		CatParentInfo parentInfo = catParentList.get(position);
		
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_category_parent, null);
			holder = new ViewHolder();
			holder.itemImage = (ImageView) convertView
					.findViewById(R.id.catItemImage);
			holder.itemContent = (TextView) convertView
					.findViewById(R.id.catItemText);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final RelativeLayout layout = (RelativeLayout) convertView;
		final ImageView itemImage = holder.itemImage;
		itemImage.setImageBitmap(null);
		TextView itemContent = holder.itemContent;
		if (StringUtils.hasText(parentInfo.getImgulr())) {
			ImageViewLoader nid = ImageViewLoader.getInstance(mContext);
			nid.fetchImage(parentInfo.getImgulr(), 0, itemImage,
					new ImageLoaderCallback() {
						@Override
						public void imageLoaderFinish(Bitmap bitmap) {
							if (bitmap != null) {
								int width=layout.getWidth() ;
								if (layout.getWidth() <= 0) {
									width=UIUtil.dip2px(mContext, 160);
								}bitmap = ImageUtils
										.zoomWidthCutMiddlePx(
												bitmap,
												width,
												UIUtil.dip2px(mContext, 150),
												mContext);
								itemImage.setImageBitmap(bitmap);
							}
						}
					});
		}
		itemContent.setText(parentInfo.getTypeName());
		return convertView;
	}

	private class ViewHolder {
		public ImageView itemImage;
		public TextView itemContent;
	}
	
}
