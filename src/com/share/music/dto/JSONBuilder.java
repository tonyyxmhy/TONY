package com.share.music.dto;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class JSONBuilder<T>
{
  protected String root = "";

  public abstract T build(JSONObject paramJSONObject)
    throws JSONException;

  public void setRoot(String paramString)
  {
    this.root = paramString;
  }
}
