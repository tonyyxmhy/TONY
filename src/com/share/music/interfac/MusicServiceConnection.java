package com.share.music.interfac;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * 
 * @author yexiaoming
 *
 */
public class MusicServiceConnection implements ServiceConnection{

	MyBinder mybinder;
	
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		
		this.mybinder = (MyBinder) service;
		
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		
	}
	
	public MyBinder getMyBinder(){
		
		
		return mybinder;
	}

}
