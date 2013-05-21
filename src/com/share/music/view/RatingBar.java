package com.share.music.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.share.music.R;

public class RatingBar extends LinearLayout {

	ImageView[] x;

	public RatingBar(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater inflate = LayoutInflater.from(context);
		View v = inflate.inflate(R.layout.layout_ratingbar, null);
		int[] id = new int[] { R.id.x1, R.id.x2, R.id.x3, R.id.x4, R.id.x5 };
		x = new ImageView[id.length];

		for (int i = 0; i < id.length; i++) {

			x[i] = (ImageView) v.findViewById(id[i]);
		}
		this.addView(v, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
	}

	public void initRating(){
		
		for(int i=0;i<x.length;i++){
			
			x[i].setImageResource(R.drawable.xing01);
		}
		
	}
	
	public void setRatingNum(int num){
		
		if(num>=5){
			
			for(int i=0;i<x.length;i++){
				
				x[i].setImageResource(R.drawable.xing02);
			}
			
		}else if(num>0&&num<5){
			
			for(int i=0;i<num;i++){
				
				x[i].setImageResource(R.drawable.xing02);
			}
		}
		
	}
}
