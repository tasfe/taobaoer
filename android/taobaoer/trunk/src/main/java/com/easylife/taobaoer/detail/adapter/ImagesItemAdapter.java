package com.easylife.taobaoer.detail.adapter;

import java.util.Vector;

import org.springframework.util.StringUtils;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.core.utils.ImageUtils;
import com.easylife.taobaoer.core.widget.image.ImageLoaderCallback;
import com.easylife.taobaoer.core.widget.image.ImageViewLoader;
import com.easylife.taobaoer.detail.model.GoodsImageInfo;
import com.easylife.taobaoer.detail.model.GoodsMoreImages;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class ImagesItemAdapter extends BaseAdapter {
	private Context mContext;
	private ListView mListView;
	private LayoutInflater mInflater;
	private GoodsMoreImages goodsMoreImages;
	public ImagesItemAdapter(Context context,ListView listView,GoodsMoreImages moreImages) {
		mInflater = LayoutInflater.from(context);
		mContext = context;
		mListView = listView;
		goodsMoreImages = moreImages; 
	}
	
	@Override
	public int getCount() {
		if(goodsMoreImages==null||goodsMoreImages.getData()==null){
			return 0;
		}
		return goodsMoreImages.getData().size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View imageView, ViewGroup parent) {
		GoodsImageInfo goodsImageInfo = goodsMoreImages.getData().get(position);
		String pic_url = goodsImageInfo.getPic_url();
		System.out.println("============="+pic_url); 
		ViewHolder holder = null;
		if (imageView == null) {
			imageView = mInflater.inflate(R.layout.item_image, null);
		    holder = new ViewHolder();
			holder.itemImage = (ImageView) imageView
					.findViewById(R.id.goods_pic);
			imageView.setTag(holder);
		} else {
			holder = (ViewHolder) imageView.getTag();
		}
		
		final ImageView itemImage = holder.itemImage;
		System.out.println("=========================="+itemImage.getId());
		if (StringUtils.hasText(pic_url)) {
			imageView.setVisibility(View.VISIBLE);
			ImageViewLoader nid = ImageViewLoader.getInstance(mContext);
			nid.fetchImage(pic_url,
					R.drawable.loading_big, itemImage,
					new ImageLoaderCallback() {
						@Override
						public void imageLoaderFinish(Bitmap bitmap) {
							if (bitmap != null) {
								itemImage.setImageBitmap( ImageUtils
										.zoomWidthBitmapNotCut(bitmap,0, 0,
												mContext));
							}
						}
					});
		} else {
			imageView.setVisibility(View.GONE);
		}
		return imageView;
	}
	

	private class ViewHolder {
		public ImageView itemImage;
	}
}
