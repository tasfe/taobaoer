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
import com.easylife.taobaoer.category.model.CatChildren;
import com.easylife.taobaoer.category.model.CatDataList;
import com.easylife.taobaoer.category.model.CatInfo;
import com.easylife.taobaoer.category.model.CategoryEnum;
import com.easylife.taobaoer.core.utils.ImageUtils;
import com.easylife.taobaoer.core.utils.UIUtil;
import com.easylife.taobaoer.core.widget.image.ImageLoaderCallback;
import com.easylife.taobaoer.core.widget.image.ImageViewLoader;
import com.easylife.taobaoer.core.widget.list.PageAdapter;

public class CategoryChildrenAdapter extends PageAdapter<CatChildren> {

	private CatDataList catDataList;
	
	private CategoryEnum catEnum;

	public CategoryChildrenAdapter(Context mContext, CatDataList catDataList, CategoryEnum catEnum) {
		super(mContext);
		this.catDataList = catDataList;
		this.catEnum = catEnum;
	}

	@Override
	public int getCount() {

		if (catDataList == null || catDataList.getData() == null
				|| catDataList.getData().get(0).getChildren() == null) {
			return 0;
		} else {
			if(CategoryEnum.PEISHI.getId() == catEnum.getId()){
				return catDataList.getData().get(1).getChildren().size();
			}else{
				return catDataList.getData().get(0).getChildren().size();
			}
			
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		CatInfo catInfo = null;
		if(CategoryEnum.PEISHI.getId() == catEnum.getId()){
			catInfo = catDataList.getData().get(1).getChildren().get(position).getInfo();
		}else{
			catInfo = catDataList.getData().get(0).getChildren().get(position).getInfo();
		}

		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_category_children, null);
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
		if (StringUtils.hasText(catInfo.getPic())) {
			ImageViewLoader nid = ImageViewLoader.getInstance(mContext);
			nid.fetchImage(catInfo.getPic(), R.drawable.loading_small, itemImage,
					new ImageLoaderCallback() {
						@Override
						public void imageLoaderFinish(Bitmap bitmap) {
							if (bitmap != null) {
								int width=layout.getWidth() ;
								if (layout.getWidth() <= 0) {
									width=UIUtil.dip2px(mContext, 110);
								}bitmap = ImageUtils
										.zoomWidthCutMiddlePx(
												bitmap,
												width,
												UIUtil.dip2px(mContext, 100),
												mContext);
								itemImage.setImageBitmap(bitmap);
							}
						}
					});
		}
		itemContent.setText(catInfo.getWord_name());
		return convertView;
	}

	private class ViewHolder {
		public ImageView itemImage;
		public TextView itemContent;
	}

}
