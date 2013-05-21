package com.share.music.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.share.music.R;

public class MenueDialog extends Dialog{
	
	ListView mlistView;
	
	String[] data = new String[] { "顺序播放", "本地浏览", "全部循环", "分享", "定时关", "个性化",
			"联网", "更多设置" };
	Context context;
	
	public MenueDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
		

      DisplayMetrics dm = new DisplayMetrics();
       ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
 
         int screenWidth = dm.widthPixels;
 
         int screenHeigh = dm.heightPixels;
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.main_menue,null);

		this.setContentView(v);
		Window win = getWindow();
		android.view.WindowManager.LayoutParams p = new android.view.WindowManager.LayoutParams();
		win.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
		p.x = 0;
		p.y = 2*screenHeigh/15+screenHeigh/4;
		p.width = screenWidth/3;
		p.height = screenHeigh/2;
		win.setAttributes(p);
		 this.setCanceledOnTouchOutside(true);
	}
	
}
