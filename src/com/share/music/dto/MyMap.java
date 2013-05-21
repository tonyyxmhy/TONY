package com.share.music.dto;

import java.util.ArrayList;

import android.util.SparseArray;

@SuppressWarnings("hiding")
public class MyMap<Integer, Object> {

	SparseArray<Object> sparse;
	ArrayList<java.lang.Integer> arr;

	public MyMap() {

		sparse = new SparseArray<Object>();
		arr = new ArrayList<java.lang.Integer>();
	}

	public Object get(int key) {

		return sparse.get(key);

	}

	public void put(int key, Object obj) {

		sparse.put(key, obj);

		arr.add(key);

	}

	public void remove(int index) {

		java.lang.Integer key = arr.get(index);
		sparse.remove(key);
		arr.remove(index);

	}

	public void removeKey(int key) {

		sparse.remove(key);
		arr.remove(key);
	}
	
	
	public int size(){
		
		return sparse.size();
	}
	
	public boolean contain(int key){
		
		return arr.contains(key);
	}
	
	
	public void clear(){
		
		sparse.clear();
		arr.clear();
	}

}
