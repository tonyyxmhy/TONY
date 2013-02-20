package com.share.music.interfac;

import com.share.music.dto.MediaInfo;

import android.media.MediaPlayer;
import android.os.Binder;

/**
 * 
 * @author yexiaoming
 *
 */
public abstract class MyBinder extends Binder{
	
	public MediaPlayer player;
	
	public MyBinder(MediaPlayer player) {
		
		this.player = player;
	}
	
	public abstract void setMediaInfo(MediaInfo mediaInfo);
	public abstract void paly(String steam);
	public abstract void seekTo(int position);
	public abstract void nextMusic();
	public abstract int getCurrentTime();
	public abstract void stop();
	public abstract void pause();
}
