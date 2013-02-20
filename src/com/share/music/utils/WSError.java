package com.share.music.utils;



public class WSError extends Throwable
{
  private static final long serialVersionUID = 1L;
  private String message;

  public String getMessage()
  {
    return this.message;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
}