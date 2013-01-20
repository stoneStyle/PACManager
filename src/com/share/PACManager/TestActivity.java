package com.share.PACManager;

import com.share.PACManager.R;
import com.share.PACManager.R.layout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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