package com.share.music.dto;

import org.json.JSONException;
import org.json.JSONObject;

public class ArtistBuilder extends JSONBuilder<Artist>{

	 public Artist build(JSONObject paramJSONObject)
			    throws JSONException
			  {
			    Artist localArtist = new Artist();
			    localArtist.setId(paramJSONObject.getInt("id"));
			    localArtist.setIdstr(paramJSONObject.getString("idstr"));
			    localArtist.setImage(paramJSONObject.getString("image"));
			    localArtist.setMbgid(paramJSONObject.getString("mbgid"));
			    localArtist.setMbid(paramJSONObject.getInt("mbid"));
			    localArtist.setName(paramJSONObject.getString("name"));
			    localArtist.setUrl(paramJSONObject.getString("url"));
			    return localArtist;
			  }
	
}
