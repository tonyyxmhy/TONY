package com.share.music.activity;

import java.io.File;
import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;

import com.share.music.R;
import com.share.music.dto.MediaInfo;
import com.share.music.dto.Song;
import com.share.music.utils.Constants;

public class WelcomeActivity extends BaseActivity {

	Handler myHandler = new Handler() {

		public void handleMessage(android.os.Message msg) {

		};

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		initData();
//		myHandler.postDelayed(new Runnable() {
//
//			@Override
//			public void run() {
//
//				Intent intent = new Intent();
//				intent.setClass(WelcomeActivity.this, MainActivity.class);
//				WelcomeActivity.this.startActivity(intent);
//				WelcomeActivity.this.finish();
//				overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
//
//			}
//		}, Constants.WELCOME_DELAY_TIME);
	}

	public void initData() {

		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			new GetData().execute();
		}

	}

	class GetData extends AsyncTask<Void, Void, MediaInfo> {

		@Override
		protected MediaInfo doInBackground(Void... params) {

			File f = Environment.getExternalStorageDirectory();

			
			ContentResolver resolver = WelcomeActivity.this.getContentResolver();
			Cursor cursor =resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
			ArrayList<Song> arr = new ArrayList<Song>();
			
			while (cursor.moveToNext()) {
				
				Song song = new Song();
//				int id = cursor.getInt(cursor.getColumnIndex("id"));
				int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
//				String name = cursor.getString(cursor.getColumnIndex("name"));
				String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
//				String path = cursor.getString(cursor.getColumnIndex("path"));
				String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
//				String album = cursor.getString(cursor.getColumnIndex("album"));
				String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
//				long during = cursor.getLong(cursor.getColumnIndex("during"));
				int during = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
//				String artist = cursor.getString(cursor.getColumnIndex("artist"));
				String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
				song.setId(id);
				song.setName(name);
				song.setUrl(path);
				song.setAlbum(album);
				song.setArtist(artist);
				song.setDuration(during);
				arr.add(song);
				
				
			}
			MediaInfo mediaInfo = new MediaInfo(Constants.PLAY_MODE_RANDOM_AND_DEFAULT ,arr);
			
//			SQLiteDatabase db = DBUtils.getSQLiteDataBase();
//			Cursor cursor = db
//					.rawQuery(
//							"select id,name,path,during,artist,album from songs where list_id='1588943871937'",
//							null);
//
//			Cursor c = db.rawQuery("select playmode"
//					+ " from list where id='1588943871937'", null);
//			int playmode = 0;
//			if (c.moveToNext()) {
//
//				playmode = c.getInt(c.getColumnIndex("playmode"));
//			}
//			
//			ArrayList<Song> arr = new ArrayList<Song>();
//			while (cursor.moveToNext()) {
//				
//				Song song = new Song();
//				int id = cursor.getInt(cursor.getColumnIndex("id"));
//				String name = cursor.getString(cursor.getColumnIndex("name"));
//				String path = cursor.getString(cursor.getColumnIndex("path"));
//				String album = cursor.getString(cursor.getColumnIndex("album"));
//				long during = cursor.getLong(cursor.getColumnIndex("during"));
//				String artist = cursor.getString(cursor.getColumnIndex("artist"));
//				song.setId(id);
//				song.setName(name);
//				song.setUrl(path);
//				song.setAlbum(album);
//				song.setArtist(artist);
//				song.setDuration(during);
//				arr.add(song);
//
//			}
//			MediaInfo mediaInfo = new MediaInfo(playmode ,arr);
//			
//			DBUtils.close();
			return mediaInfo;
		}

		@Override
		protected void onPostExecute(MediaInfo result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Intent intent = new Intent();
			intent.setClass(WelcomeActivity.this, MainActivity.class);
			intent.putExtra("mediainfo", result);
			WelcomeActivity.this.startActivity(intent);
			WelcomeActivity.this.finish();
			overridePendingTransition(R.anim.welcome_in, R.anim.welcome_out);
		}

	}

}
