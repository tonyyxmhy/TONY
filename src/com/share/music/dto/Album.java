package com.share.music.dto;

import java.io.Serializable;


public class Album
  implements Serializable
{
  private static final long serialVersionUID = 8517633545835124349L;
  private String artistName;
  private int id;
  private String image;
  private String name;
  private double rating;

  public String getArtistName()
  {
    return this.artistName;
  }

  public int getId()
  {
    return this.id;
  }

  public String getImage()
  {
    return this.image;
  }

  public String getName()
  {
    return this.name;
  }

  public double getRating()
  {
    return this.rating;
  }

  public void setArtistName(String paramString)
  {
    this.artistName = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setImage(String paramString)
  {
    this.image = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setRating(double paramDouble)
  {
    this.rating = paramDouble;
  }
}