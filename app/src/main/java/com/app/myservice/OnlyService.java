package com.app.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by acer on 11-02-2018.
 */

public class OnlyService extends Service {
    Handler mhandler=new Handler();
    private static final long interval = 40000;
    private Timer timer=null;
    @Override
    public void onCreate() {
        if(timer!=null)
        {
            timer.cancel();
        }
        else
        {
         timer=new Timer();
            timer.scheduleAtFixedRate(new ToastTimerTask(),0,interval);
        }
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class ToastTimerTask extends TimerTask{

        @Override
        public void run() {
            mhandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), " 2nd App OnlyService running", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
