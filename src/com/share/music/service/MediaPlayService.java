package com.share.music.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import com.share.music.dto.MediaInfo;
import com.share.music.dto.Song;
import com.share.music.interfac.OnDownloadLRCComplete;
import com.share.music.utils.Constants;
import com.share.music.utils.NetUtils;
import com.share.music.utils.Utils;

public class MediaPlayService extends Service implements OnCompletionListener {

	MyBinder myBinder;
	MediaPlayer player = new MediaPlayer();


	public  class MyBinder extends Binder {

		OnDownloadLRCComplete listener;
		public final static String urlLRC = "http://geci.me/api/lyric/";
		public MediaPlayer player = MediaPlayService.this.player;
		MediaInfo mediaInfo;
		public int currentposition = 0;
		public List<Song> curPlayList = new ArrayList<Song>();

		// public MyMap<Integer, Object>
		public void setMediaInfo(MediaInfo mediaInfo) {

			this.mediaInfo = mediaInfo;
			curPlayList = getCurPlayList();
		}

		public void setLisnter(OnDownloadLRCComplete listener){
			
			this.listener = listener;
		}
		
		private List<Song> getCurPlayList() {

			switch (mediaInfo.playMode) {
			case Constants.PLAY_MODE_RANDOM_AND_DEFAULT:
			case Constants.PLAY_MODE_RANDOM_AND_LOOP:
				return mediaInfo.listRandom;
			case Constants.PLAY_MODE_SEQUENCE_AND_DEFAULT:
			case Constants.PLAY_MODE_SEQUENCE_AND_LOOP:
				return mediaInfo.list;

			}
			return null;

		}

		Handler myHandler = new Handler(){
			
			public void handleMessage(android.os.Message msg) {
				
			};
			
		};
		
		
		
		public void play(final Song song) {

			Log.d("flag", "正在播放");

			try {
				player.reset();
				player.setDataSource(song.getUrl());
				player.prepare();
				player.start();
				new DownLoadLRCTask().execute(song);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void seekTo(int position) {
			player.seekTo(position * 1000);
		}

		public void preMusic() {

			switch (mediaInfo.playMode) {
			case Constants.PLAY_MODE_RANDOM_AND_DEFAULT:
				if ((--currentposition) < 0) {
					currentposition = 0;
					return;
				}
				myBinder.play(mediaInfo.listRandom.get(currentposition));
				break;
			case Constants.PLAY_MODE_RANDOM_AND_LOOP:
				if (--currentposition < 0) {
					currentposition = mediaInfo.listRandom.size() - 1;
				}
				myBinder.play(mediaInfo.listRandom.get(currentposition));
				break;
			case Constants.PLAY_MODE_SEQUENCE_AND_DEFAULT:
				if (--currentposition < 0) {
					currentposition = 0;
					return;
				}
				myBinder.play(mediaInfo.list.get(currentposition));
				break;
			case Constants.PLAY_MODE_SEQUENCE_AND_LOOP:
				if (--currentposition < 0) {
					currentposition = mediaInfo.list.size() - 1;
				}
				myBinder.play(mediaInfo.list.get(currentposition));
				break;
			}
			updata();

		}

		public void nextMusic() {

			switch (mediaInfo.playMode) {
			case Constants.PLAY_MODE_RANDOM_AND_DEFAULT:
				if ((++currentposition) >= mediaInfo.list.size()) {
					currentposition = 0;
					return;
				}
				myBinder.play(mediaInfo.listRandom.get(currentposition));
				break;
			case Constants.PLAY_MODE_RANDOM_AND_LOOP:
				if ((++currentposition) >= mediaInfo.list.size()) {
					currentposition = 0;
				}
				myBinder.play(mediaInfo.listRandom.get(currentposition));
				break;
			case Constants.PLAY_MODE_SEQUENCE_AND_DEFAULT:
				if ((++currentposition) >= mediaInfo.list.size()) {
					currentposition = 0;
					return;
				}
				myBinder.play(mediaInfo.list.get(currentposition));
				break;
			case Constants.PLAY_MODE_SEQUENCE_AND_LOOP:
				if ((++currentposition) >= mediaInfo.list.size()) {
					currentposition = 0;
				}
				myBinder.play(mediaInfo.list.get(currentposition));
				break;
			}
			updata();

		}

		public int getCurrentTime() {

			return (int) player.getCurrentPosition();
		}

		public void stop() {

			player.stop();
			cancelnotify();
		}

	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		myBinder = new MyBinder();
		player.setOnCompletionListener(this);

	}

	@Override
	public IBinder onBind(Intent intent) {

		return myBinder;
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		
		myBinder.nextMusic();
		myBinder.listener.onPlayNextSong(myBinder.getCurPlayList().get(myBinder.currentposition));
	}

	Notification notify;
	NotificationManager noMag;
	RemoteViews view;

	public void showNotify() {

	}

	public void updata() {

	}

	public void cancelnotify() {

	}
	
	class DownLoadLRCTask extends AsyncTask<Song, Void, File>{
		public final static String urlLRC = "http://geci.me/api/lyric/";
		@Override
		protected File doInBackground(Song... params) {
			
			Song song = params[0];
			HttpResponse response = null;
			String url = "";
			if("".equals(song.getArtist())||null==song.getArtist()||"<unknown>".equals(song.getArtist())){
				
				url = urlLRC + song.getName() ;
			}else{
				
				url = urlLRC + song.getName() + "/"
						+ song.getArtist();
			}
			HttpClient client = new DefaultHttpClient();
			url = Utils.toTrim(url);
			HttpGet get = new HttpGet(url);
			InputStream in = null;
			File lrc = null;
			try {
				response = client.execute(get);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String urlL = "";
			int id = 0;
			if (response != null) {

				StringBuilder sbResult = new StringBuilder();
				try {
					in = response.getEntity().getContent();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(in));

					String data = "";
					while ((data = br.readLine()) != null) {

						sbResult.append(data);
					}

				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				try {
					JSONObject j = new JSONObject(sbResult.toString());
					JSONArray result = j.getJSONArray("result");
					if(result.length()>0){
						
						JSONObject obj = result.getJSONObject(0);
						urlL = obj.getString("lrc");
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				lrc = NetUtils.getLRC(urlL, song.getId());
			}

		
			
			return lrc;
		}
		
		@Override
		protected void onPostExecute(File result) {
			super.onPostExecute(result);
			myBinder.listener.onDownloadlrcComplete(result);
		}
		
	}

}
