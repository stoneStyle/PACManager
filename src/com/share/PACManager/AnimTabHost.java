package com.share.PACManager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TabHost;

public class AnimTabHost extends TabHost {

	public AnimTabHost(Context context) {
		super(context);
		initTabHost();
	}
	
	public AnimTabHost(Context context, AttributeSet attrs) {
		super(context, attrs);
		initTabHost();		
	}
	
	private Animation ani_left_in, ani_right_out;
	private Animation ani_right_in, ani_left_out;	
	private void initTabHost()
	{
		ani_left_in = AnimationUtils.loadAnimation(getContext(), R.anim.ani_left_in);
		ani_right_out = AnimationUtils.loadAnimation(getContext(), R.anim.ani_right_out);
		ani_right_in = AnimationUtils.loadAnimation(getContext(), R.anim.ani_right_in);
		ani_left_out = AnimationUtils.loadAnimation(getContext(), R.anim.ani_left_out);
		
	}
	
	@Override
	public void setCurrentTab(int index)
	{
		int last_index = getCurrentTab();
		View last_view = getCurrentView();
		super.setCurrentTab(index);		
		View this_view = getCurrentView();
		
		if(last_index == index || last_view == null || this_view == null)
			return;
		
		if(index > last_index)
		{
			last_view.startAnimation(ani_left_out);
			this_view.startAnimation(ani_right_in);
		}
		else
		{
			last_view.startAnimation(ani_right_out);
			this_view.startAnimation(ani_left_in);
		}
	}	
}
