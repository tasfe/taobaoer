package com.taobaoer.brushtop.core;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class BrushtopCore {
  
	private String url = null;
	
	public BrushtopCore(String url){
		this.url = url;
	}
	
	public void excute(){
		HttpClient  httpClient = new DefaultHttpClient();
	//text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
		HttpGet get = new HttpGet(url);
		//get.setHeader(name, value);
		try {
			HttpResponse   response= httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			saveFile(inputStream);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveFile(InputStream inputStream) throws IOException{
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String filePath = "F:/dowloadfile/";
		String fileName = System.currentTimeMillis()+url.substring(url.lastIndexOf("."));
		try {
			bis = new BufferedInputStream(inputStream);
			File path = new File(filePath);
			if(!path.exists()){
				path.mkdirs();
			}
			bos = new BufferedOutputStream(new FileOutputStream(new File(filePath+"/"+fileName)));
			byte[] b = new byte[1024];
			int length = 0;
			while((length=bis.read(b))!=-1){
				bos.write(b, 0, length);
			}
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bos!=null){
				bos.close();
			}
			if(bis!=null){
				bis.close();
			}
		}
	}
	
}
