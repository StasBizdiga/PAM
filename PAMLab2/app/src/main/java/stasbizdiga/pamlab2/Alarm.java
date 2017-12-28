package stasbizdiga.pamlab2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.Calendar;


public class Alarm extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        wl.acquire();
        v.vibrate(250);
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example

        wl.release();
    }

    public void setAlarm(Context context, long time, String note) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        //am.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.currentThreadTimeMillis()+5000, 60000, pi); // Millisec * Second * Minute
        am.set(AlarmManager.RTC_WAKEUP, time, pi);
    }

     static public void cancelAlarm(Context context) {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }


    public static long timeConvert(String myDate, int Hour, int Minute) {

        String parts[] = myDate.split("-");
        int day = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[0]);

        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(SystemClock.currentThreadTimeMillis());
        cal.clear();
        cal.set(year,month-1,day,Hour,Minute);

        return cal.getTimeInMillis();
    }


}