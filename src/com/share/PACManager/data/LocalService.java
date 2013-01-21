package com.share.PACManager.data;

import com.share.PACManager.task.CheckPasswordTask;
import com.share.PACManager.task.CheckPasswordTask.CheckPasswordListener;
import com.share.PACManager.task.RegDeviceTask;
import com.share.PACManager.task.RegDeviceTask.RegDeviceListener;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {
    public class LocalBinder extends Binder {
        public LocalService getService() {
            return LocalService.this;
        }
    }
    
    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
    	
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder(); 
    
    ////////////////////////////////////////////////////
    // ºÏ≤‚√‹¬Î≤ø∑÷    
    public void checkPassword(CheckPasswordListener listener)
    {
    	CheckPasswordTask task = new CheckPasswordTask(this);
    	task.checkPassword(listener);
    }
    
    public void regDevice(RegDeviceListener listener)
    {
    	RegDeviceTask task = new RegDeviceTask(this);
    	task.regDevice(listener);
    }
}

