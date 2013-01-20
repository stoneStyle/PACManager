package com.share.PACManager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class TestActivity extends Activity {

	private static int times;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        int n = (times++) % 3;
        int color = n==0?Color.RED:n==1?Color.GREEN:Color.BLUE;
        View contentView = getWindow().getDecorView();
        contentView.setBackgroundColor(color);  
        contentView.setBackgroundColor(Color.WHITE);
    }
}