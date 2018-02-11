package com.app.myservice;

import android.app.Service;
import android.content.ComponentName;
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
    private static final long interval = 10000;
    private Timer timer=null;
    private int count=0;
    Intent i = new Intent();
    ComponentName compo = new ComponentName("com.example.nouiapplication", "com.example.nouiapplication.service.MyService");
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

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onCreate();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        timer.cancel();
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
                    //Calculate Signal strength and pass it to App 1 (OnstartCommand).
                    //Do Calculations - Like get Telephony data and send it back to first service
        i.putExtra("data","RETURNING BACK DATA"+ ++count);
        i.setComponent(compo);
        getApplicationContext().startService(i);
                 //   Toast.makeText(getApplicationContext(), " Second App  : "+ ++count, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
