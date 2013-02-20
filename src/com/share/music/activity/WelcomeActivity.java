package com.share.music.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.share.music.R;
import com.share.music.utils.Constants;

public class WelcomeActivity extends BaseActivity{
	
	Handler myHandler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			
			
		};
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		myHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				Intent intent = new Intent();
				intent.setClass(WelcomeActivity.this, MainActivity.class);
				WelcomeActivity.this.startActivity(intent);
				WelcomeActivity.this.finish();
				overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
				
			}
		}, Constants.WELCOME_DELAY_TIME);
	}

	
	
}
