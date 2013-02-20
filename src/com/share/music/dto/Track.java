package com.share.music.dto;

import java.io.Serializable;


public class Track
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int duration;
  private int id;
  private String name;
  private int numalbum;
  private double rating;
  private String stream;
  private String url;

  public int getDuration()
  {
    return this.duration;
  }

  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public int getNumAlbum()
  {
    return this.numalbum;
  }

  public double getRating()
  {
    return this.rating;
  }

  public String getStream()
  {
    return this.stream;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setDuration(int paramInt)
  {
    this.duration = paramInt;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setNumAlbum(int paramInt)
  {
    this.numalbum = paramInt;
  }

  public void setRating(double paramDouble)
  {
    this.rating = paramDouble;
  }

  public void setStream(String paramString)
  {
    this.stream = paramString;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
}