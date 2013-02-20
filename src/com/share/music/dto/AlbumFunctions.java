package com.share.music.dto;

import org.json.JSONArray;
import org.json.JSONException;

public class AlbumFunctions {

	public static Album[] getAlbums(JSONArray paramJSONArray)
			throws JSONException {
		int i = paramJSONArray.length();
		Album[] arrayOfAlbum = new Album[i];
		AlbumBuilder localAlbumBuilder = new AlbumBuilder();
		for (int j = 0;; j++) {
			if (j >= i)
				return arrayOfAlbum;
			// arrayOfAlbum[j] =
			// localAlbumBuilder.build(paramJSONArray.getJSONObject(j));
		}
	}

}
