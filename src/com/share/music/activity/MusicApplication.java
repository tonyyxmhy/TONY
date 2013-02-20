package com.share.music.activity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;
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
	

}
