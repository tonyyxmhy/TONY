package com.share.music.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Application;

import com.share.music.R;
import com.share.music.utils.Constants;
import com.share.music.utils.Utils;
/**
 * 
 * @author yexiaoming
 *
 */
public class MusicApplication extends Application{
	
	ArrayList<BaseActivity> activities = new ArrayList<BaseActivity>();
	public static MusicApplication app;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		if(null==app){
			
			app = this;
		}
		
		createDirOrFile();
	}
	
	public void addActivity(BaseActivity acitivity){
		
		activities.add(acitivity);
		
	}
	
	
	public void removeActivity(BaseActivity acitivity){
		
		for(int i=0;i<activities.size();i++){
			
			if(activities.get(i)==acitivity){
				
				activities.remove(i);
				return;
			}
			
		}
		
	}
	
	public void exit(){
		
		for(int i=0;i<activities.size();i++){
			
			activities.get(i).finish();
		}
		
		activities.clear();
		System.exit(0);
	}
	
	
	private void createDirOrFile() {
		
		final File dir = new File(Constants.FILE_PATH);
		if(!dir.exists()){
			
			new Thread(){
				
				public void run() {
					
					dir.mkdirs();
					File f = new File(dir, Constants.FILE_NAME_DB);
					if(!f.exists()){
						
						try {
							f.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						InputStream in = MusicApplication.this.getResources().openRawResource(R.raw.list);
						Utils.createFileDB(f, in);
						
					}
				};
				
			}.start();
		}else{
			
			final File f = new File(dir, Constants.FILE_NAME_DB);
			
			
			if(!f.exists()){
				new Thread(){
					
					public void run() {
						
						try {
							f.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						InputStream in = MusicApplication.this.getResources().openRawResource(R.raw.list);
						Utils.createFileDB(f, in);
						
					};
					
				}.start();
				
				
			}
			
		}
		

		
	}

}
