package com.share.music.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MediaInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int playMode;
	public ArrayList<Song> listRandom;
	public ArrayList<Song> list;
	
	public MediaInfo(int playMode,ArrayList<Song> list){
		
		this.playMode = playMode;
		this.list = list;
		listRandom = list;
		 Collections.shuffle(listRandom);
	}

}
