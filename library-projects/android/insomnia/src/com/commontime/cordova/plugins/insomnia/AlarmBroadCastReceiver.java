package com.commontime.cordova.plugins.insomnia;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.content.WakefulBroadcastReceiver;

public class AlarmBroadCastReceiver extends WakefulBroadcastReceiver {
    private PowerManager.WakeLock screenWakeLock;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (screenWakeLock == null)
        {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            screenWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP,
                    "ct:Screenlock:");
            screenWakeLock.acquire();
        }
        Intent service = new Intent(context, BackgroundOperationsManagerService.class);
        startWakefulService(context, service);
        if (screenWakeLock != null)
            screenWakeLock.release();
    }
}