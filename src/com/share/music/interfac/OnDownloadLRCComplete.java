package com.share.music.interfac;

import java.io.File;

import com.share.music.dto.Song;

public interface OnDownloadLRCComplete {

	public abstract void onDownloadlrcComplete(File f);
	public abstract void onPlayNextSong(Song song);
}
