package com.easylife.taobaoer.detail.adapter;

import java.util.Vector;

import com.easylife.taobaoer.R;
import com.easylife.taobaoer.detail.model.GoodsImageInfo;

import android.content.Context;
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
	private Vector<GoodsImageInfo> mModels = new Vector<GoodsImageInfo>();
	SyncImageLoader syncImageLoader;
	public ImagesItemAdapter(Context context,ListView listView) {
		syncImageLoader = new SyncImageLoader(context);
		syncImageLoader.clearCache();
		mInflater = LayoutInflater.from(context);
		mContext = context;
		mListView = listView;
	}
	
	public void addImageInfo(GoodsImageInfo imageInfo){
		mModels.add(imageInfo);
	}
	
	public SyncImageLoader getImageLoader(){
		return syncImageLoader;
	}
	
	public void clean(){
		mModels.clear();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mModels.size();
	}

	@Override
	public Object getItem(int position) {
		if(position >= getCount()){
			return null;
		}
		return mModels.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View view=convertView;  
        if(convertView==null) {
        	view = mInflater.inflate(R.layout.item_image, null);  
        }
		
		GoodsImageInfo goodsImageInfo =mModels.get(position);
		//view.setTag(position);
		ImageView imageView = (ImageView) view.findViewById(R.id.goods_pic);
		//vi.setBackgroundResource(R.drawable.loading_big);
		syncImageLoader.DisplayImage(goodsImageInfo.getPic_url(), imageView);  
		return  view;
	}
}
