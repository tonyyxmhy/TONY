package com.share.music.dto;

import java.io.Serializable;

public class PlaylistRemote implements Serializable {
	private static final long serialVersionUID = 1L;
	private int duration;
	private int id;
	private String name;

	public int getDuration() {
		return this.duration;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setDuration(int paramInt) {
		this.duration = paramInt;
	}

	public void setId(int paramInt) {
		this.id = paramInt;
	}

	public void setName(String paramString) {
		this.name = paramString;
	}
}