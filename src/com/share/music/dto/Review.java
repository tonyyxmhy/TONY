package com.share.music.dto;

import java.io.Serializable;
import java.util.Date;


public class Review
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Date[] dates;
  private int id;
  private String lang;
  private String name;
  private int rating;
  private String text;
  private String userImage;
  private String userName;

  public Date[] getDates()
  {
    return this.dates;
  }

  public int getId()
  {
    return this.id;
  }

  public String getLang()
  {
    return this.lang;
  }

  public String getName()
  {
    return this.name;
  }

  public int getRating()
  {
    return this.rating;
  }

  public String getText()
  {
    return this.text;
  }

  public String getUserImage()
  {
    return this.userImage;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public void setDates(Date[] paramArrayOfDate)
  {
    this.dates = paramArrayOfDate;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setLang(String paramString)
  {
    this.lang = paramString;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setRating(int paramInt)
  {
    this.rating = paramInt;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }

  public void setUserImage(String paramString)
  {
    this.userImage = paramString;
  }

  public void setUserName(String paramString)
  {
    this.userName = paramString;
  }
}