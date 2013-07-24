package com.easylife.taobaoer.category.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.category.model.CatChildren;
import com.easylife.taobaoer.category.model.CategoryEnum;
import com.easylife.taobaoer.core.widget.list.PageAdapter;

public class CategoryParentAdapter extends PageAdapter<CatChildren> {
	
	//private List<CatParentInfo> catParentList;

	public CategoryParentAdapter(Context mContext) {
		super(mContext);
		//this.catParentList = catParentList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		CategoryEnum  categoryEnum = CategoryEnum.getCategoryEnum(position);
		
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
		
		 ImageView itemImage = holder.itemImage;
		 TextView  itemContent = holder.itemContent;
		 
		 itemImage.setBackgroundResource(categoryEnum.getUrl());
		 itemContent.setText(categoryEnum.getName());
		 
		
		/*CatParentInfo parentInfo = catParentList.get(position);
		
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
		itemContent.setText(parentInfo.getTypeName());*/
		return convertView;
	}

	private class ViewHolder {
		public ImageView itemImage;
		public TextView itemContent;
	}
	
}
