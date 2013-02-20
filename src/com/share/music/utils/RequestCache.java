package com.share.music.utils;

import java.util.Hashtable;
import java.util.LinkedList;

public class RequestCache {

	private static int CACHE_LIMIT = 10;
	private Hashtable<String, String> cache = new Hashtable();
	private LinkedList history = new LinkedList();

	public String get(String paramString) {
		return (String) this.cache.get(paramString);
	}

	public void put(String paramString1, String paramString2) {
		this.history.add(paramString1);
		if (this.history.size() > CACHE_LIMIT) {
			String str = (String) this.history.poll();
			this.cache.remove(str);
		}
		this.cache.put(paramString1, paramString2);
	}

}
