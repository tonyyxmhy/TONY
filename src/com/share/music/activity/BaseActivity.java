package com.share.music.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * 
 * @author yexiaoming
 *
 */

public class BaseActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		MusicApplication.app.addActivity(this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		MusicApplication.app.removeActivity(this);
	}

}
