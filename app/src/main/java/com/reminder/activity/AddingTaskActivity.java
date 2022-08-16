package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.reminder.R;
import com.reminder.databinding.ActivityAddingTaskBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarListener;


public class AddingTaskActivity extends AppCompatActivity {
    ActivityAddingTaskBinding binding;
    String[] convention;
    String selectedconvention;
    int sp_convention;
    private HorizontalCalendar horizontalCalendar;
    CalendarView simpleCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddingTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String myString = "Convention";

        convention = getResources().getStringArray(R.array.spConvention);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(AddingTaskActivity.this, android.R.layout.simple_spinner_dropdown_item,convention);
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

                Log.e("etferwtfre", "onItemSelected: "+selectedconvention );
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

        simpleCalendarView = (CalendarView) findViewById(R.id.calendar); // get the reference of CalendarView
        simpleCalendarView.setFocusedMonthDateColor(Color.RED); // set the red color for the dates of  focused month
        simpleCalendarView.setUnfocusedMonthDateColor(Color.BLUE); // set the yellow color for the dates of an unfocused month
        simpleCalendarView.setSelectedWeekBackgroundColor(Color.RED); // red color for the selected week's background
        simpleCalendarView.setWeekSeparatorLineColor(Color.GREEN); // green color for the week separator line

        // perform setOnDateChangeListener event on CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }
}