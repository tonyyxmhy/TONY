package com.share.music.activity;

import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.share.music.R;
import com.share.music.dto.Album;
import com.share.music.function.Getalbum;
import com.share.music.utils.WSError;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		initData();

	}

	public void initData() {

		new NewsTask().execute(new Void[] { null });

	}

	private class NewsTask extends AsyncTask<Void, WSError, Album[]> {
		private NewsTask() {
		}

		public Album[] doInBackground(Void[] paramArrayOfVoid) {
			Getalbum a = new Getalbum();
			Album[] albums = null;
			try {
				albums = a.getPopularAlbumsWeek();
				return albums;
			} catch (JSONException localJSONException) {
			} catch (WSError localWSError) {
			}
			
			return albums;
		}

		public void onPostExecute(Album[] albums) {
			
		}

		public void onPreExecute() {
			super.onPreExecute();
		}

		protected void onProgressUpdate(WSError[] paramArrayOfWSError) {
			super.onProgressUpdate(paramArrayOfWSError);
		}
	}

}
