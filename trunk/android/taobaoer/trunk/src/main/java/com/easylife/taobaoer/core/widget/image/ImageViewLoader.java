package com.easylife.taobaoer.core.widget.image;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

/**
 * 只能在主线程使用
 * 
 * @author kooks
 * 
 */
public class ImageViewLoader {

	private static final String CACHE_PATH = "images";

	private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB

	private static ImageViewLoader instance;

	private LruCache<String, Bitmap> mCache;

	private DiskLruImageCache diskCache;

	private ImageViewLoader(Context context) {
		if (mCache == null) {
			final int memClass = ((ActivityManager) context
					.getSystemService(Context.ACTIVITY_SERVICE))
					.getMemoryClass();
			final int cacheSize = 1024 * 1024 * memClass / 8;
			mCache = new LruCache<String, Bitmap>(cacheSize) {
				@Override
				protected int sizeOf(String key, Bitmap value) {
					return value.getRowBytes() * value.getHeight();
				}
			};
			diskCache = new DiskLruImageCache(context, CACHE_PATH,
					DISK_CACHE_SIZE, null, -1);
		}
	}

	public static ImageViewLoader getInstance(Context context) {
		synchronized (ImageViewLoader.class) {
			if (null == instance) {
				instance = new ImageViewLoader(context);
			}
		}
		return instance;
	}

	public void clearCache() {
		diskCache.clearCache();
		mCache.evictAll();
		instance = null;
	}

	public void fetchImage(final String url, int placeholderImage,
			ImageView imageView, final ImageLoaderCallback imageLoaderCallback) {
		fetchImage(url, placeholderImage, false, imageView, imageLoaderCallback);
	}

	/**
	 * 兼容png图片
	 * 
	 * @param url
	 * @param placeholderImage
	 * @param imageView
	 * @param imageLoaderCallback
	 */
	public void fetchImage(final String url, int placeholderImage,
			boolean hasPng, ImageView imageView,
			final ImageLoaderCallback imageLoaderCallback) {
		if (placeholderImage > 0) {
			imageView.setImageResource(placeholderImage);
		}
		if (imageView.getTag() != null
				&& imageView.getTag() instanceof WebImageTask) {
			((WebImageTask) imageView.getTag()).cancel(true);
		}
		if (StringUtils.hasText(url)) {
			// 查询内存缓存
			Bitmap bitmap = mCache.get(getKey(url));
			if (null == bitmap) {
				WebImageTask imageLoader = new WebImageTask(hasPng,
						imageLoaderCallback);
				imageView.setTag(imageLoader);
				imageLoader.execute(url);
			} else {
				imageLoaderCallback.imageLoaderFinish(bitmap);
			}
		}
	}

	private String getKey(String url) {
		return new String(Hex.encodeHex(DigestUtils.md5(url)));
	}

	private class WebImageTask extends AsyncTask<String, Integer, Bitmap> {

		// private DiskLruImageCache diskCache;
		private boolean hasPng = false;
		private ImageLoaderCallback imageLoaderCallback;

		public WebImageTask(boolean hasPng,
				ImageLoaderCallback imageLoaderCallback) {
			// this.diskCache = diskCache;
			this.hasPng = hasPng;
			this.imageLoaderCallback = imageLoaderCallback;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			String myFileUrl = params[0];

			Bitmap bitmap = diskCache.getBitmap(getKey(myFileUrl));
			if (null != bitmap) {
				mCache.put(getKey(myFileUrl), bitmap);
				return bitmap;
			}
			URL imageUrl = null;
			try {
				imageUrl = new URL(myFileUrl);
			} catch (MalformedURLException e) {
				Log.e(getClass().getSimpleName(), "Image Url[" + myFileUrl
						+ "] is not url format", e);
				return null;
			}
			InputStream is = null;
			HttpURLConnection conn = null;
			try {
				conn = (HttpURLConnection) imageUrl.openConnection();
				conn.setDoInput(true);
				conn.connect();
				is = conn.getInputStream();
				bitmap = BitmapFactory.decodeStream(is);
				is.close();
			} catch (IOException e) {
				Log.e(getClass().getSimpleName(), "download image error", e);
			} finally {
				try {
					if (is != null) {
						is.close();
					}
					if (conn != null) {
						conn.disconnect();
					}
				} catch (IOException e) {
					Log.e(getClass().getSimpleName(), "download image error", e);
				}
			}
			if (null != bitmap) {
				mCache.put(getKey(myFileUrl), bitmap);
				diskCache.put(hasPng, getKey(myFileUrl), bitmap);
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			super.onPostExecute(bitmap);
			imageLoaderCallback.imageLoaderFinish(bitmap);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}
	}
}
