package com.share.music.interfac;

import org.json.JSONException;

import com.share.music.dto.Album;
import com.share.music.dto.Artist;
import com.share.music.dto.License;
import com.share.music.dto.Playlist;
import com.share.music.dto.PlaylistRemote;
import com.share.music.dto.Radio;
import com.share.music.dto.Review;
import com.share.music.dto.Track;
import com.share.music.utils.WSError;

public interface JamendoGet2Api {


	  public static final String ENCODING_MP3 = "mp31";
	  public static final String ENCODING_OGG = "ogg2";

	  public abstract Album getAlbumById(int paramInt)
	    throws JSONException, WSError;

	  public abstract License getAlbumLicense(Album paramAlbum)
	    throws WSError;

	  public abstract Review[] getAlbumReviews(Album paramAlbum)
	    throws JSONException, WSError;

	  public abstract Track[] getAlbumTracks(Album paramAlbum, String paramString)
	    throws JSONException, WSError;

	  public abstract Album[] getAlbumsByTracksId(int[] paramArrayOfInt)
	    throws JSONException, WSError;

	  public abstract Artist getArtist(String paramString)
	    throws JSONException, WSError;

	  public abstract Playlist getPlaylist(PlaylistRemote paramPlaylistRemote)
	    throws JSONException, WSError;

	  public abstract Album[] getPopularAlbumsWeek()
	    throws JSONException, WSError;

	  public abstract Playlist getRadioPlaylist(Radio paramRadio, int paramInt, String paramString)
	    throws JSONException, WSError;

	  public abstract Radio[] getRadiosByIds(int[] paramArrayOfInt)
	    throws JSONException, WSError;

	  public abstract Radio[] getRadiosByIdstr(String paramString)
	    throws JSONException, WSError;

	  public abstract int[] getTop100Listened()
	    throws WSError;

	  public abstract String getTrackLyrics(Track paramTrack)
	    throws WSError;

	  public abstract Track[] getTracksByTracksId(int[] paramArrayOfInt, String paramString)
	    throws JSONException, WSError;

	  public abstract PlaylistRemote[] getUserPlaylist(String paramString)
	    throws JSONException, WSError;

	  public abstract Album[] getUserStarredAlbums(String paramString)
	    throws JSONException, WSError;

	  public abstract Album[] searchForAlbumsByArtist(String paramString)
	    throws JSONException, WSError;

	  public abstract Album[] searchForAlbumsByArtistName(String paramString)
	    throws JSONException, WSError;

	  public abstract Album[] searchForAlbumsByTag(String paramString)
	    throws JSONException, WSError;
}
