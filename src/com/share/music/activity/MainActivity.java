package com.share.music.activity;

import java.io.File;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.share.music.R;
import com.share.music.dialog.MenueDialog;
import com.share.music.dto.MediaInfo;
import com.share.music.dto.Song;
import com.share.music.dto.SongLyric;
import com.share.music.interfac.OnDownloadLRCComplete;
import com.share.music.interfac.OnProgressChanged;
import com.share.music.service.MediaPlayService;
import com.share.music.service.MediaPlayService.MyBinder;
import com.share.music.utils.Constants;
import com.share.music.utils.Utils;
import com.share.music.view.LyricView;
import com.share.music.view.MyTextView;
import com.share.music.view.ProgressBar;

public class MainActivity extends Activity implements OnClickListener,
		OnTouchListener, OnProgressChanged {

	ImageButton mbtn_return;
	ImageButton mbtn_pre;
	ImageButton mbtn_play;
	ImageButton mbtn_next;
	ImageButton mbtn_menu;

	MyTextView songName;
	MyTextView albumName;
	MyTextView name;
	TextView process;
	TextView total;
	RelativeLayout relation_thumb;
	Intent intentser;
	// 引用Service中的MyBinder
	MyBinder myBinder;
	// 播放列表信息
	MediaInfo mediaInfo;
	// 当前播放歌曲在list中的位置
	int currentPosition = 0;

	// SeekBar seekBar;
	ProgressBar progress;
	View thume;
	LyricView lrcView;
	OnDownloadLRCComplete listener = new OnDownloadLRCComplete() {

		@Override
		public void onDownloadlrcComplete(File f) {
			SongLyric song = new SongLyric(f);
			lrcView.setLyric(song);
			lrcView.setTime(myBinder.getCurrentTime());
			lrcView.invalidate();
		}

		@Override
		public void onPlayNextSong(Song song) {

			refreshUI(song);
		}
	};

	ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {

			// 得到service 中的Binder
			myBinder = (MyBinder) service;
			// 将播放列表信息传入service
			myBinder.setMediaInfo(mediaInfo);
			myBinder.setLisnter(listener);
			// 播放音乐
			myBinder.play(myBinder.curPlayList.get(currentPosition));

			mbtn_play.setImageResource(R.drawable.btn_pause01);
			// 创建一个线程 用于 刷新播放界面中的播放进程
			new Thread() {

				public void run() {

					while (true) {

						try {
							// 800毫秒刷新一次播放进度
							Thread.sleep(800);

							if (null == myBinder) {
								return;
							}
							// 得到播放的当前时间的毫秒值
							long l = myBinder.getCurrentTime();
							// 播放下一曲时刷仙播放进度UI
							if (l / 1000 == 0) {
								// refreshMusicInfoUI();
							}
							// 创建一个Message
							Message msg = myHandler.obtainMessage();
							msg.what = 0;
							// 设置message 中的成员变量arg1 为播放当前时间 秒值
							msg.arg1 = (int) (l / 1000);
							// 设置message 中的成员变量obj 为播放当前时间 时间格式：00:00
							// m.obj = CommonUtils.toDate((int) (l / 1000));
							// 将message 发送至UI 线程 刷新页面
							myHandler.sendMessage(msg);

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				};

			}.start();

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();

	}

	private void initView() {

		mbtn_return = (ImageButton) findViewById(R.id.im_return);
		mbtn_pre = (ImageButton) findViewById(R.id.im_pre);
		mbtn_play = (ImageButton) findViewById(R.id.im_paly);
		mbtn_next = (ImageButton) findViewById(R.id.im_next);
		mbtn_menu = (ImageButton) findViewById(R.id.im_menu);
		songName = (MyTextView) findViewById(R.id.songname);
		albumName = (MyTextView) findViewById(R.id.album);
		name = (MyTextView) findViewById(R.id.name);
		process = (TextView) findViewById(R.id.progress);
		total = (TextView) findViewById(R.id.total);
		thume = findViewById(R.id.thume);

		mbtn_return.setOnClickListener(this);
		mbtn_pre.setOnClickListener(this);
		mbtn_play.setOnClickListener(this);
		mbtn_next.setOnClickListener(this);
		mbtn_menu.setOnClickListener(this);
		relation_thumb = (RelativeLayout) findViewById(R.id.relation_thumb);
		// seekBar = (SeekBar) findViewById(R.id.progressBar1);
		progress = (ProgressBar) findViewById(R.id.p);
		progress.setOnTouchListener(this);
		progress.setOnProgressChanged(this);
		progress.setOnClickListener(this);
		relation_thumb.setOnClickListener(this);
		LinearLayout lrcv = (LinearLayout) findViewById(R.id.lrc);
		lrcView = new LyricView(this);
		lrcView.setBackgroundColor(Color.TRANSPARENT);
		lrcv.addView(lrcView, new LinearLayout.LayoutParams(
				android.widget.LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT));

	}

	public void initData() {

		Intent intent = getIntent();
		mediaInfo = (MediaInfo) intent.getSerializableExtra("mediainfo");
		if (null != mediaInfo) {
			Song song = null;
			switch (mediaInfo.playMode) {
			case Constants.PLAY_MODE_RANDOM_AND_LOOP:
			case Constants.PLAY_MODE_RANDOM_AND_DEFAULT:
				song = mediaInfo.listRandom.get(0);
				break;
			case Constants.PLAY_MODE_SEQUENCE_AND_DEFAULT:
			case Constants.PLAY_MODE_SEQUENCE_AND_LOOP:
				song = mediaInfo.list.get(0);
				break;
			default:
				break;
			}

			if (null != song) {
				refreshUI(song);
				intentser = new Intent();
				intentser.putExtra("stream", song);
				intentser.setClass(this, MediaPlayService.class);
				startService(intentser);
				bindService(intentser, connection, BIND_AUTO_CREATE);
			}
		}

	}

	public void refreshUI(Song song) {

		String album = song.getAlbum();
		String artist = song.getArtist();
		String name = song.getName();
		long duration = song.getDuration();

		int m = (int) (duration / 1000);

		String s = m / 60 + ":" + m % 60;

		songName.setText(name);
		albumName.setText(album);
		this.name.setText(artist);
		total.setText(s);

		// seekBar.setMax(m);
		progress.setMax(m);

	}

	@Override
	public void onClick(View v) {
		Log.d("onTouch", "33333333333");
		switch (v.getId()) {
		case R.id.im_return:
			MusicApplication.app.exit();
			break;
		case R.id.im_pre:
			myBinder.preMusic();
			refreshUI(myBinder.curPlayList.get(myBinder.currentposition));
			break;
		case R.id.im_paly:
			if (myBinder.player.isPlaying()) {

				myBinder.player.pause();
				mbtn_play.setImageResource(R.drawable.btn_paly01);
			} else {

				myBinder.player.start();
				mbtn_play.setImageResource(R.drawable.btn_pause01);
			}
			break;
		case R.id.im_next:
			myBinder.nextMusic();
			refreshUI(myBinder.curPlayList.get(myBinder.currentposition));
			break;
		case R.id.im_menu:
			showDialog();
			break;
		case R.id.progress:
		case R.id.thume:
			Log.d("onTouch", "44444444444");
			break;
		default:
			break;
		}

	}

	private void showDialog() {

		MenueDialog dailog = new MenueDialog(this, R.style.myDialogTheme);
		dailog.show();

	}

	Handler myHandler = new Handler() {

		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 0:
				int l = msg.arg1;
				String date = Utils.toDate(l);
				process.setText(date);
				progress.setProgress(l);
				break;

			default:
				break;
			}

		};

	};

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.d("onTouch", "111111111111");
		boolean isTouch = false;
		int c = progress.getChildCount();
		for (int i = 0; i < c; i++) {
			if (v == progress.getChildAt(i)) {

				isTouch = true;
				break;
			}
		}
		int count = relation_thumb.getChildCount();

		for (int i = 0; i < count; i++) {
			if (v == relation_thumb.getChildAt(i)) {

				isTouch = true;
				break;
			}
		}

		if (v == progress || v == relation_thumb || isTouch) {
			Log.d("onTouch", "2222222222222");
			float x = event.getX();
			int[] loc = new int[2];
			v.getLocationOnScreen(loc); // 获取在当前窗口内的绝对坐标
			// process.getLocationOnScreen(0);//获取在整个屏幕内的绝对坐标

			int width = (int) (x - loc[0]);
			progress.setProgress(width);

			myBinder.seekTo(width);
		}

		return false;
	}

	@Override
	public void OnProgressChange(int width) {
		RelativeLayout.LayoutParams layout = (android.widget.RelativeLayout.LayoutParams) thume
				.getLayoutParams();
		layout.setMargins(width, 0,
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,
				android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
		thume.setLayoutParams(layout);
		thume.invalidate();
	}
}
