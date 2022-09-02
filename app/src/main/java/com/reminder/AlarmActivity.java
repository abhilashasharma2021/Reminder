package com.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSetAlarm;
    EditText etHour, etMinute;
    int minute, hour, day;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        btnSetAlarm = (Button) findViewById(R.id.btn_set_alarm);
        etHour = (EditText) findViewById(R.id.etHour);
        etMinute = (EditText) findViewById(R.id.etMinute);
        btnSetAlarm.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set_alarm:
                setAlarm();
                break;
        }
    }

    private void setAlarm() {
        cal = new GregorianCalendar();
        cal.setTimeInMillis(System.currentTimeMillis());
        day = cal.get(Calendar.DAY_OF_WEEK);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);

        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_HOUR, hour + Integer.parseInt(etHour.getText().toString()));
        i.putExtra(AlarmClock.EXTRA_MINUTES, minute + Integer.parseInt(etMinute.getText().toString()));
        i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        startActivity(i);
    }
}