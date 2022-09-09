package com.reminder.utils;

import static android.content.ContentValues.TAG;
import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.reminder.MainActivity;
import com.reminder.R;
import com.reminder.activity.LockscreenNotificationActivity;

import java.util.concurrent.TimeUnit;

public class NotificaitonHelper extends ContextWrapper {

    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private NotificationManager notificationManager;
    PendingIntent fullScreenPendingIntent;
    private static final int NOTIFICATION_ID = 101;
    Intent fullScreenIntent;
    private static final String TAG = "BigTextService";

    public static final String ACTION_DISMISS =
            "com.example.android.wearable.wear.wearnotifications.handlers.action.DISMISS";
    public static final String ACTION_SNOOZE =
            "com.example.android.wearable.wear.wearnotifications.handlers.action.SNOOZE";
    private static final long SNOOZE_TIME = TimeUnit.SECONDS.toMillis(5);
    PendingIntent snoozePendingIntent,dismissPendingIntent;
    NotificationCompat.Action dismissAction,snoozeAction;
    public NotificaitonHelper(Context base) {
        super((base));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName,
                NotificationManager.IMPORTANCE_HIGH);
     /*   Log.e("Create Channel", String.format("Set lockscreen %d", channel.getLockscreenVisibility()));
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);*/
        getManager().createNotificationChannel(channel);



        fullScreenIntent = new Intent(getApplicationContext(), LockscreenNotificationActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fullScreenPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, fullScreenIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else {
            fullScreenPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }





        Intent snoozeIntent = new Intent(this, NotificationPublisher.class);
        snoozeIntent.setAction(ACTION_SNOOZE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           snoozePendingIntent  = PendingIntent.getService(getApplicationContext(), 0, snoozeIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else {
            snoozePendingIntent  = PendingIntent.getService(getApplicationContext(), 0, snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        snoozeAction =
                new NotificationCompat.Action.Builder(
                        R.drawable.ic_launcher_background,
                        "Snooze",
                        snoozePendingIntent)
                        .build();

        Intent dismissIntent = new Intent(this, NotificationPublisher.class);
        dismissIntent.setAction(ACTION_DISMISS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dismissPendingIntent  = PendingIntent.getService(getApplicationContext(), 0, dismissIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else {
            dismissPendingIntent  = PendingIntent.getService(getApplicationContext(), 0, dismissIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
       dismissAction =
                new NotificationCompat.Action.Builder(
                        R.drawable.ic_launcher_background,
                        "Dismiss",
                        dismissPendingIntent)
                        .build();



    }
    public NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }
    public NotificationCompat.Builder getChannelNotification() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setAutoCancel(true)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setFullScreenIntent(fullScreenPendingIntent, true)
                .setContentIntent(fullScreenPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(snoozeAction)
                .addAction(dismissAction)
                .setSmallIcon(R.drawable.ic_launcher_background);


    }

    /*@Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent(): " + intent);

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DISMISS.equals(action)) {
                handleActionDismiss();
            } else if (ACTION_SNOOZE.equals(action)) {
                handleActionSnooze();
            }
        }
    }
*/
    private void handleActionDismiss() {
        Log.d(TAG, "handleActionDismiss()");
        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.cancel(this.NOTIFICATION_ID);

    }

    private void handleActionSnooze() {
        Log.d(TAG, "handleActionSnooze()");

        NotificationCompat.Builder notificationCompatBuilder =
              new NotificationCompat.Builder(getApplicationContext());

        // Recreate builder from persistent state if app process is killed
        if (notificationCompatBuilder == null) {
            // Note: New builder set globally in the method

        }

        Notification notification;
        notification = notificationCompatBuilder.build();


        if (notification != null) {
            NotificationManagerCompat notificationManagerCompat =
                    NotificationManagerCompat.from(getApplicationContext());

            notificationManagerCompat.cancel(this.NOTIFICATION_ID);

            try {
                Thread.sleep(SNOOZE_TIME);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            notificationManagerCompat.notify(this.NOTIFICATION_ID, notification);
        }

    }

}
