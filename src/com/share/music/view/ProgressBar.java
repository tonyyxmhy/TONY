package com.share.music.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.share.music.R;
import com.share.music.interfac.OnProgressChanged;

public class ProgressBar extends RelativeLayout implements
		OnGlobalLayoutListener {

	int max = 10000;
	int progress = 0;
	Context context;
	int width;
	int height;
	View v;
	RelativeLayout r1;
	OnProgressChanged listener;
	public ProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ProgressBar);
		// width = (int) a.getDimension(0, -1);
		// height = a.getDimension(1, 0);
		r1 = new RelativeLayout(context);
		RelativeLayout r2 = new RelativeLayout(context);
		v = new View(context);
		v.setBackgroundResource(R.drawable.progress);
		r2.addView(v, new RelativeLayout.LayoutParams(0,
				LayoutParams.FILL_PARENT));
		r1.setBackgroundResource(R.drawable.propress);
		// r1.setBackgroundColor(Color.TRANSPARENT);
		r2.setBackgroundColor(Color.TRANSPARENT);

		ViewTreeObserver vto2 = r1.getViewTreeObserver();
		vto2.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				r1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				width = r1.getWidth();
				height = r1.getHeight();
			}
		});

		this.addView(r1, new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		this.addView(r2, new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		// this.setBackgroundResource(R.drawable.propress);
	}

	public void setOnProgressChanged(OnProgressChanged onProgressChanged){
		
		this.listener = onProgressChanged;
	}
	
	public void setMax(int max) {

		this.max = max;
	}

	public void setProgress(int progress) {

		if (this.progress != progress) {
			this.progress = progress;
			int width = (int) (this.width * progress / max);
			updataProgress(width);
			listener.OnProgressChange(width);
		}
	}

	public int getMarginLeft() {

		return (int) (this.width * progress / max);
	}

	public void updataProgress(int width) {

		LayoutParams paras = (LayoutParams) v.getLayoutParams();

		paras.setMargins(0, 0, (int) (this.width - width), 0);
		paras.width = width;
		v.setLayoutParams(paras);
		invalidate();
	}

	@Override
	public void onGlobalLayout() {

	}


}
