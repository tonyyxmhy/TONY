package com.share.music.dto;


public class Radio
{
  private int id;
  private String idstr;
  private String image;
  private String name;

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

  public String getName()
  {
    return this.name;
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

  public void setName(String paramString)
  {
    this.name = paramString;
  }
}