package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.reminder.Database.DatabaseClient;
import com.reminder.Entity.Task;
import com.reminder.MainActivity;
import com.reminder.R;
import com.reminder.Reminder.MyBroadcastReceiver;
import com.reminder.databinding.ActivityAddingTaskBinding;
import com.reminder.utils.AppConstats;
import com.reminder.utils.SharedHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;


public class AddingTaskActivity extends AppCompatActivity {
    ActivityAddingTaskBinding binding;
    String[] convention;
    String selectedconvention;
    int sp_convention;
    String StrFinalStatus = "", strPriorty = "";
    private HorizontalCalendar horizontalCalendar;
    CalendarView simpleCalendarView;
    Integer in_Date, Seconds;
    private String selectedDate = "";
    int RQS_1=1;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddingTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, BroadcastReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        60 * 1000, alarmIntent);


        binding.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
                //startAlert();
            }
        });


        binding.cbHigh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.cbHigh.isChecked()) {

                    binding.cbLow.setChecked(false);
                    binding.cbMedium.setChecked(false);
                    binding.cbHigh.setChecked(true);
                    strPriorty = "High";

                    Log.e("cldfdsvf", "High: " + strPriorty);

                }
            }
        });

        binding.cbMedium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.cbMedium.isChecked()) {

                    binding.cbLow.setChecked(false);
                    binding.cbHigh.setChecked(false);
                    binding.cbMedium.setChecked(true);

                    strPriorty = "Medium";
                    Log.e("cldfdsvf", "Medium: " + strPriorty);
                }
            }
        });


        binding.cbLow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.cbLow.isChecked()) {

                    binding.cbMedium.setChecked(false);
                    binding.cbHigh.setChecked(false);
                    binding.cbLow.setChecked(true);

                    strPriorty = "Low";

                    Log.e("cldfdsvf", "Low: " + strPriorty);
                }
            }
        });


        String myString = "Convention";

        convention = getResources().getStringArray(R.array.spConvention);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(AddingTaskActivity.this, android.R.layout.simple_spinner_dropdown_item, convention);
        sp_convention = ad.getPosition(myString);
        binding.spConvention.setAdapter(ad);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.spConvention.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                ((TextView) arg0.getChildAt(0)).setTextColor(getResources().getColor(R.color.grey_text));
                ((TextView) arg0.getChildAt(0)).setTextSize(12);

                selectedconvention = binding.spConvention.getSelectedItem().toString();

                Log.e("etferwtfre", "onItemSelected: " + selectedconvention);
                System.out.println(selectedconvention);
                setid();

            }

            private void setid() {


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = binding.radioGroup.findViewById(checkedId);
                int index = binding.radioGroup.indexOfChild(radioButton);

                Log.e("wedgdsgdf", index + "");
                switch (index) {
                    case 0:
                        StrFinalStatus = "0";

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR_OF_DAY, 12);
                        calendar.set(Calendar.MINUTE, 30);


                        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                AlarmManager.INTERVAL_DAY, alarmIntent);


                        break;
                    case 1:
                        StrFinalStatus = "1";
                        break;
                    case 2:
                        StrFinalStatus = "2";
                        break;
                    case 3:
                        StrFinalStatus = "3";

                       /* AlarmManager objAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        Calendar objCalendar = Calendar.getInstance();
                        objCalendar.set(Calendar.YEAR, objCalendar.get(Calendar.YEAR));
                        objCalendar.set(Calendar.MONTH,  objCalendar.get(Calendar.MONTH));
                        objCalendar.set(Calendar.DAY_OF_MONTH,  objCalendar.get(Calendar.DAY_OF_MONTH));
                        objCalendar.set(Calendar.HOUR_OF_DAY, objCalendar.get(Calendar.HOUR_OF_DAY));
                        objCalendar.set(Calendar.DAY_OF_WEEK, objCalendar.get(Calendar.DAY_OF_WEEK));
                        objCalendar.set(Calendar.AM_PM, Calendar.AM);

                        Intent alamShowIntent = new Intent(AddingTaskActivity.this, MyBroadcastReceiver.class);
                        PendingIntent alarmPendingIntent = PendingIntent.getActivity(AddingTaskActivity.this, 0,alamShowIntent,0 );

                        objAlarmManager.set(AlarmManager.RTC_WAKEUP,objCalendar.getTimeInMillis(), alarmPendingIntent);

*/
                        break;
                    case 4:
                        StrFinalStatus = "4";
                        break;
                    case 5:
                        StrFinalStatus = "5";
                        break;


                }
            }
        });

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
        selectedDate = df.format(c);



        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_WEEK, 5);
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_WEEK, 0);


        horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .startDate(startDate.getTime())
                .endDate(endDate.getTime())
                .datesNumberOnScreen(7)
                .dayNameFormat("EEE")
                .dayNumberFormat("dd")
                .monthFormat("MMM")
                .textSize(14f, 24f, 14f)
                .showDayName(true)
                .showMonthName(false)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Date date, int position) {
                Log.e("sdvdvdvdv", "CURRENT DATE IS " + date);

                /*CURRENT DATE IS Thu Aug 25 12:41:07 GMT+05:30 2022*/

           SimpleDateFormat format1 = new SimpleDateFormat("dd-mm-yyyy");

                selectedDate = format1.format(date.getTime());

                Log.e("date", "CURRENT DATE IS " + selectedDate);

            }

        });
    }

    private void saveTask() {
        final String sTask = binding.etTask.getText().toString().trim();


        if (sTask.isEmpty()) {
            binding.etTask.setError("Task required");
            binding.etTask.requestFocus();
            return;
        }


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task = new Task();
                task.setTask(sTask);
                task.setDate(selectedDate);
                task.setPriority(strPriorty);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    private Date ConvertToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE-MMM-dd");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(convertedDate);
        Log.e("rtyt6uyt", "CURRENT DATE IS " + convertedDate);
        return convertedDate;
    }


  /*  private void startAlert() {

        //etTime.setText(simpDate.format(calNow.getTime()));

        Intent intent = new Intent(getBaseContext(), MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if (in_Date == 1) {

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Seconds, alarmManager.INTERVAL_DAY, pendingIntent);
        } else if (in_Date == 2) {

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Seconds, 1 * 60 * 60 * 1000, pendingIntent);
        }*/

/*

        public void startAlert () {
            EditText text = findViewById(R.id.etTask);
            int i = Integer.parseInt(text.getText().toString());
            Intent intent = new Intent(this, MyBroadcastReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this.getApplicationContext(), 234324243, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + (i * 1000), pendingIntent);
            Toast.makeText(this, "Alarm set in " + i + " seconds", Toast.LENGTH_LONG).show();
        }

*/


}
