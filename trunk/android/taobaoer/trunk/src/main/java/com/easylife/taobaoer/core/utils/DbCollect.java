package com.easylife.taobaoer.core.utils;

import java.util.ArrayList;
import java.util.List;

import com.easylife.taobaoer.product.model.Product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class DbCollect {
	private SQLiteDatabase db=null;
	private Cursor cursor=null;
	public static final String TB_NAME= "product";
	SqliteHelper sh;
	/**
	 * 构造方法设置连接对象
	 * @param context
	 */
	public DbCollect(Context context){
		sh=new SqliteHelper(context, TB_NAME, null, 2);
	}
	
	public void open(){
		db=sh.getWritableDatabase();
	}
	/**
	 * 关闭连接对象
	 */
	public void close(){
		db.close();
		sh.close();
		cursor.close();
	}
	/**
	 * 将要收藏的商品保存到数据库
	 * @param product
	 * @return
	 */
	public Long saveproductInfo(Product product){
		 ContentValues values = new ContentValues();
         values.put("twitter_id", product.getTwitter_id());
         values.put("title", product.getTitle());
         values.put("twitter_goods_id", product.getTwitter_goods_id());
         values.put("pic_url", product.getPic_url());
         values.put("j_pic_url", product.getJ_pic_url());
         values.put("q_pic_url", product.getQ_pic_url());
         values.put("pic_width", product.getPic_width());
         values.put("pic_height", product.getPic_height());
         open();
         Long uid=db.insert(TB_NAME,null, values);
         this.close();
         return uid;
	}
	/**
	 * 根据ID查询出该商品是否为已收藏商品
	 * @param twitter_id
	 * @return 得到ID的相关值
	 */
	public List<Product> getCollectProduct(Long twitter_id){
		List<Product> productList=new ArrayList<Product>();
		open();
		cursor=db.query(TB_NAME, null, "twitter_id=?", new String[]{twitter_id+""}, null, null, null);
		while(cursor.moveToNext()){
			Product product=new Product();
			product.setTwitter_id(cursor.getInt(0));
			product.setTitle(cursor.getString(1));
			product.setTwitter_goods_id(cursor.getInt(2));
			product.setPic_url(cursor.getString(3));
			product.setJ_pic_url(cursor.getString(4));
			product.setQ_pic_url(cursor.getString(5));
			product.setPic_width(cursor.getInt(6));
			product.setPic_height(cursor.getInt(7));
			productList.add(product);
		}
		 this.close();
		return productList;
	}
	/**
	 * 获得收藏的所有商品
	 * @return
	 */
	public int getPageNumProduct(){
		open();
		cursor=db.query(TB_NAME, null, null, null, null, null, null);
		int number=cursor.getCount();
		number=number%20==0?number/20:number/20+1;
		this.close();
		return number;
	}
	
	
	public List<Product> pageSelectproduct(int page){
		List<Product> pageProductList=new ArrayList<Product>();
		open();
		cursor=db.rawQuery("select * from "+TB_NAME+" order by twitter_id desc limit 20*("+page+"-1),"+page+"*20", null);
		
		
		while(cursor.moveToNext()){
			Product product=new Product();
			product.setTwitter_id(cursor.getInt(0));
			product.setTitle(cursor.getString(1));
			product.setTwitter_goods_id(cursor.getInt(2));
			product.setPic_url(cursor.getString(3));
			product.setJ_pic_url(cursor.getString(4));
			product.setQ_pic_url(cursor.getString(5));
			product.setPic_width(cursor.getInt(6));
			product.setPic_height(cursor.getInt(7));
			pageProductList.add(product);
		}
		this.close();
		return pageProductList;
	}
	
	public int deleteCollectProduct(Product product) {
		open();
		int flg=db.delete(TB_NAME, "twitter_id=?", new String[]{product.getTwitter_id()+""});
		this.close();
		return flg;
	}
}