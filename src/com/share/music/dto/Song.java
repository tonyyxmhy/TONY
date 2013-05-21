package com.share.music.dto;

import java.io.Serializable;

public class Song implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numalbum ;
	private int id;
	private String name;
	private String url;
	private String album;
	private String artist;
	private long duration;
	private String stream;
	private double rating;
	
	
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getNumalbum() {
		return numalbum;
	}
	public void setNumalbum(int numalbum) {
		this.numalbum = numalbum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Song(int numalbum, int id, String name, String url, int duration,
			String stream, double rating) {
		this.numalbum = numalbum;
		this.id = id;
		this.name = name;
		this.url = url;
		this.duration = duration;
		this.stream = stream;
		this.rating = rating;
	}
	
	
	public Song(){
		
		
	}
	
}
