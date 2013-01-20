package com.share.PACManager;

import com.share.PACManager.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class QueryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.query_layout);
    }
}