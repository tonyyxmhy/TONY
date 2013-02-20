package com.share.music.dto;


public class License
{
  private int id;
  private String image;
  private String url;

  public int getId()
  {
    return this.id;
  }

  public String getImage()
  {
    return this.image;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setImage(String paramString)
  {
    this.image = paramString;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
}