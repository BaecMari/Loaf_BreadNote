package com.example.banananote;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class RestartService extends Service { //implements View.OnTouchListener
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /*private View onTopView;
    private WindowManager manager;

    private String TAG = "FloatingViewService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

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
    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Test();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
        builder.setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("default", "undead", NotificationManager.IMPORTANCE_NONE));
        }

        Notification notification = builder.build();
        startForeground(9, notification);

        Intent in = new Intent(this, FloatingViewService.class);
        startService(in);

        stopForeground(true);
        stopSelf();

        return START_NOT_STICKY;
    }

    /*
    public void Test() {
        if(Build.VERSION.SDK_INT >= 26) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            });

        } else {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            onTopView = inflater.inflate(R.layout.always_on_top_layout, null);
            onTopView.setOnTouchListener(this);

            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE, //sdk 26 이하
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
        }
    }*/
}
