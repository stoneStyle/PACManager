package com.share.PACManager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class LinearWithFrame extends LinearLayout {

	public LinearWithFrame(Context context) {
		super(context);
	}
	public LinearWithFrame(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		Paint pt = new Paint();
		pt.setColor(Color.GRAY);
		
		pt.setStrokeWidth(2.0f);
		pt.setStyle(Style.STROKE);
		
		canvas.drawRoundRect(new RectF(0,0,this.getWidth(),this.getHeight()), 10, 10, pt);
		
	} 

}
