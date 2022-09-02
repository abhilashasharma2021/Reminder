package com.reminder.Reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.reminder.R;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    @RequiresApi(api = Build.VERSION_CODES.Q)

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            // Set the alarm here.
          //  mp = MediaPlayer.create(context, R.raw.sharp);
            mp = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
            mp.start();
            Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

        }

    }
}
