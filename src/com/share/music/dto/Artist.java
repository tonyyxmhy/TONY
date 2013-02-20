package com.share.music.dto;

import java.io.Serializable;


public class Artist
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String[] genre;
  private int id;
  private String idstr;
  private String image;
  private String mbgid;
  private int mbid;
  private String name;
  private String url;

  public String[] getGenre()
  {
    return this.genre;
  }

  public int getId()
  {
    return this.id;
  }

  public String getIdstr()
  {
    return this.idstr;
  }

  public String getImage()
  {
    return this.image;
  }

  public String getMbgid()
  {
    return this.mbgid;
  }

  public int getMbid()
  {
    return this.mbid;
  }

  public String getName()
  {
    return this.name;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setGenre(String[] paramArrayOfString)
  {
    this.genre = paramArrayOfString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setIdstr(String paramString)
  {
    this.idstr = paramString;
  }

  public void setImage(String paramString)
  {
    this.image = paramString;
  }

  public void setMbgid(String paramString)
  {
    this.mbgid = paramString;
  }

  public void setMbid(int paramInt)
  {
    this.mbid = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
}