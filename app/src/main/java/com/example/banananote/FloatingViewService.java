package com.example.banananote;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class FloatingViewService extends Service implements View.OnTouchListener{

    //새로운 시도
    public static Intent serviceIntent = null;


    private String TAG = "FloatingViewService";

    private View onTopView;
    private WindowManager manager;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /*LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onTopView = inflater.inflate(R.layout.always_on_top_layout, null);
        onTopView.setOnTouchListener(this);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                //WindowManager.LayoutParams.TYPE_PHONE, //안씀 아래꺼 씀
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.LEFT | Gravity.TOP;

        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        manager.addView(onTopView, params);

        Button closeBtn = onTopView.findViewById(R.id.close_this_window);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.removeView(onTopView);
                onTopView = null;
                stopSelf();
            }
        });*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (onTopView != null) {
            manager.removeView(onTopView);

            onTopView = null;
        }*/

        //serviceIntent = null;

        //새로운 시도

        //25이하는 알람이 실행되서 무한 루프 걸린다.
        if(Build.VERSION.SDK_INT >= 26) {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 1);//3
            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        }

        //Thread.currentThread().interrupt();
    }

    //새로운 시도
    //위에 onDestroy() 때문에 아래까지하면 2개가 떠버린다.
    /*@Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 3);
        Intent intent = new Intent(this, BootCompleteReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }*/


    float xpos = 0;
    float ypos = 0;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int action = motionEvent.getAction();
        int pointerCount = motionEvent.getPointerCount();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (pointerCount == 1) {
                    xpos = motionEvent.getRawX();
                    ypos = motionEvent.getRawY();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 1) {
                    WindowManager.LayoutParams lp = (WindowManager.LayoutParams) view.getLayoutParams();
                    float dx = xpos - motionEvent.getRawX();
                    float dy = ypos - motionEvent.getRawY();
                    xpos = motionEvent.getRawX();
                    ypos = motionEvent.getRawY();

                    Log.d(TAG, "lp.x : " + lp.x + ", dx : " + dx + "lp.y : " + lp.y + ", dy : " + dy);

                    lp.x = (int) (lp.x - dx);
                    lp.y = (int) (lp.y - dy);

                    manager.updateViewLayout(view,lp);
                    return true;
                }
                break;

        }
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        serviceIntent = intent;
        Test();
        //initializeNotification();

        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    public void Test() {
        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= 26) { //Build.VERSION_CODES.O
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
            //LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onTopView = inflater.inflate(R.layout.always_on_top_layout, null);
        onTopView.setOnTouchListener(this);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                //WindowManager.LayoutParams.TYPE_PHONE, //안씀 아래꺼 씀
                //WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                //WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                //WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                LAYOUT_FLAG,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|
                        WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        //params.gravity = Gravity.LEFT | Gravity.TOP;

        manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        manager.addView(onTopView, params);

        Button closeBtn = onTopView.findViewById(R.id.close_this_window);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.removeView(onTopView);
                onTopView = null;
                stopSelf();
            }
        });

        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


        } else {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            onTopView = inflater.inflate(R.layout.always_on_top_layout, null);
            onTopView.setOnTouchListener(this);

            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    //WindowManager.LayoutParams.TYPE_PHONE, //sdk 26 이하
                    WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                    //WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|
                            WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                    PixelFormat.TRANSLUCENT);

            params.gravity = Gravity.LEFT | Gravity.TOP;

            manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            manager.addView(onTopView, params);

            Button closeBtn = onTopView.findViewById(R.id.close_this_window);
            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    manager.removeView(onTopView);
                    onTopView = null;
                    stopSelf();
                }
            });
        }*/
    }

    public void initializeNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1");

        /*builder.setSmallIcon(R.mipmap.ic_launcher);

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.bigText("설정을 보려면 누르세요.");
        style.setBigContentTitle(null);
        style.setSummaryText("서비스 동작중");

        builder.setContentText(null);
        builder.setContentTitle(null);
        builder.setOngoing(true);

        builder.setStyle(style);
        builder.setWhen(0);
        builder.setShowWhen(false);*/

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= 26) {
            manager.createNotificationChannel(new NotificationChannel("1", "undead", NotificationManager.IMPORTANCE_NONE));
        }

        Notification notification = builder.build();
        startForeground(1, notification);
    }
}
