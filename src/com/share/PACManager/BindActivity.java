package com.share.PACManager;

import com.share.PACManager.data.LocalService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public class BindActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		doBindService();
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		doUnbindService();
		super.onDestroy();
	}

	protected boolean mIsBound;
	protected LocalService mBoundService;    
	protected ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mBoundService = ((LocalService.LocalBinder)service).getService();
        }

        public void onServiceDisconnected(ComponentName className) {
            mBoundService = null;
        }
    };
    
    protected void doBindService() {
        bindService(new Intent(this, LocalService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }
    
    protected void doUnbindService() {
        if (mIsBound) {
            unbindService(mConnection);
            mIsBound = false;
        }
    }
}
