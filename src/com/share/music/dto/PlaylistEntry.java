package com.share.music.dto;

import java.io.Serializable;

public class PlaylistEntry
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Album album;
  private Track track;

  public Album getAlbum()
  {
    return this.album;
  }

  public Track getTrack()
  {
    return this.track;
  }

  public void setAlbum(Album paramAlbum)
  {
    this.album = paramAlbum;
  }

  public void setTrack(Track paramTrack)
  {
    this.track = paramTrack;
  }
}