package stasbizdiga.pamlab2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

/*

public class AlarmService extends Service {

    private static String LOG_TAG = "debug_log";

    NotificationManager manager;
    Notification myNotication;
    IBinder mBinder;
    Alarm alarm = new Alarm();
    public AlarmManager alarmMgr;

public void showNotification(int id){
    manager.notify(id, myNotication);
}

    @Override
    public void onCreate() {
        Log.v(LOG_TAG, "in onCreate");
        super.onCreate();

        mBinder = new MyBinder();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotification();
        showNotification(11);
    }




    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "in onBind");
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(LOG_TAG, "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LOG_TAG, "in onUnbind");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "in onDestroy");
    }

    void createNotification() {

        Intent intent = new Intent(".AlarmService");

        PendingIntent pendingIntent = PendingIntent.getActivity(AlarmService.this, 1, intent, 0);

        Notification.Builder builder = new Notification.Builder(AlarmService.this);

        builder.setAutoCancel(false);
        builder.setContentTitle("PAM Lab-2 Notification");
        builder.setContentText("You've set a reminder for this moment!");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.setSubText("This is your note...");   // Note goes here
        //builder.setWhen(10);
        //builder.setSound(audio);
        builder.build();

        myNotication = builder.getNotification();
    }
            //onCancelClick - > manager.cancel(11);

    public class MyBinder extends Binder {
        AlarmService getService() {
            return AlarmService.this;
        }
    }
}

*/