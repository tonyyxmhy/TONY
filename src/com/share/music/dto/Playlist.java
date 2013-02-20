package com.share.music.dto;

import java.io.Serializable;
import java.util.ArrayList;


public class Playlist
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private ArrayList<PlaylistEntry> playlist = new ArrayList();
  private int selected = -1;

  public void addPlaylistEntry(PlaylistEntry paramPlaylistEntry)
  {
    this.playlist.add(paramPlaylistEntry);
  }

  public void addTrack(Track paramTrack, Album paramAlbum)
  {
    PlaylistEntry localPlaylistEntry = new PlaylistEntry();
    localPlaylistEntry.setAlbum(paramAlbum);
    localPlaylistEntry.setTrack(paramTrack);
    this.playlist.add(localPlaylistEntry);
  }

  public void addTracks(Track[] paramArrayOfTrack, Album paramAlbum)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfTrack.length)
        return;
      addTrack(paramArrayOfTrack[i], paramAlbum);
    }
  }

  public int getSelectedIndex()
  {
    if (isEmpty())
      this.selected = -1;
    if ((this.selected == -1) && (!isEmpty()))
      this.selected = 0;
    return this.selected;
  }

  public PlaylistEntry getSelectedTrack()
  {
    boolean bool = isEmpty();
    PlaylistEntry localPlaylistEntry = null;
    if (!bool)
      localPlaylistEntry = (PlaylistEntry)this.playlist.get(getSelectedIndex());
    return localPlaylistEntry;
  }

  public PlaylistEntry getTrack(int paramInt)
  {
    return (PlaylistEntry)this.playlist.get(paramInt);
  }

  public boolean isEmpty()
  {
	  if (this.playlist.size() == 0);
    for (int i = 1; ; i = 0)
//      return i;
    
    return false;
  }

  public void remove(int paramInt)
  {
    if ((this.playlist != null) && (paramInt < this.playlist.size()) && (paramInt >= 0))
    {
      if (this.selected >= paramInt)
        this.selected -= 1;
      this.playlist.remove(paramInt);
    }
  }

  public void select(int paramInt)
  {
    if ((!isEmpty()) && (paramInt >= 0) && (paramInt < this.playlist.size()))
      this.selected = paramInt;
  }

  public void selectNext()
  {
    if (!isEmpty())
    {
      this.selected = (1 + this.selected);
      this.selected %= this.playlist.size();
    }
  }

  public void selectOrAdd(Track paramTrack, Album paramAlbum)
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.playlist.size())
      {
        addTrack(paramTrack, paramAlbum);
        select(this.playlist.size() - 1);
      }
      while (true)
      {
        if (((PlaylistEntry)this.playlist.get(i)).getTrack().getId() != paramTrack.getId())
          break;
        select(i);
        return;
      }
    }
  }

  public void selectPrev()
  {
    if (!isEmpty())
    {
      this.selected -= 1;
      if (this.selected < 0)
        this.selected = (this.playlist.size() - 1);
    }
  }

  public int size()
  {
    if (this.playlist == null);
    for (int i = 0; ; i = this.playlist.size())
      return i;
  }
}