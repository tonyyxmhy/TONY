package com.share.music.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 
 * @author yexiaoming
 *
 */

public final class Utils {

	/*
	 * 生成唯一id
	 */
	
	public static String generateID(){
		StringBuilder sb = new StringBuilder();
		Date date = new Date();
		int time = (int) date.getTime();
		int random = (int) (Math.random()*1000+Math.random()*100+Math.random()*10);
		
		sb.append(time);
		sb.append(random);
		
		return sb.toString();
		
	}

	
	/**
	 * 将文件流 写入文件
	 * 
	 * @param file
	 * @param in
	 */
	public static void createFileDB(File file, InputStream in) {

		FileOutputStream out = null;
		try {

			out = new FileOutputStream(file);
			byte[] b = new byte[10 * 1024];
			int len = 0;
			while (len != -1) {
				out.write(b, 0, len);
				len = in.read(b);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				out.close();
				in.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}
	
	
	public static String toDate(int a ){
		int minute = a/60;
		int second = a%60;
		String s = "";
		if(second<10){
			
			s = minute+":0"+second;
		}else{
			
			s = minute+":"+second;
			
		}
		return s;
		
	}

	public static Bitmap readBitMap(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}
	
	public static String toTrim(String s){
		s.trim();
		if(null!=s&&!"".equals(s)){
			
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<s.length();i++){
				if(s.charAt(i)!=' '){
					sb.append(s.charAt(i));
					
				}
			}
			
			return sb.toString();
		}
		
		
		return s;
	}
	
}
