package com.example.banananote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            //새로운 시도
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent in = new Intent(context, AlarmReceiver.class);
                context.startForegroundService(in);
            } else {
                Intent in = new Intent(context, FloatingViewService.class);
                context.startService(in);
            }
        }
        /*if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            //Intent i = new Intent(context, FloatingActivity.class);
            //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //context.startForegroundService(i);
            //context.startActivity(i);
            //context.startService(i);


            //context.startForegroundService(i);
            //context.startActivity(i);
            //context.startService(new Intent(context, FloatingViewService.class));


           // if(Build.VERSION.SDK_INT >= 26) {
                //안드로이드 8 이상에서는 foreground 서비스로 시작한다.
                //Intent i = new Intent(context,FloatingViewService.class);
                //context.startForegroundService(i);
                //context.startService(i);
                //context.startActivity(i);
            //} else {
                //Intent i = new Intent(context, FloatingViewService.class);
                //context.startService(i);
            //}

        }*/
    }
}
