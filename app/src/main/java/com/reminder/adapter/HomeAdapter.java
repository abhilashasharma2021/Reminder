package com.reminder.adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reminder.Entity.Task;
import com.reminder.Reminder.MyBroadcastReceiver;
import com.reminder.activity.AddingTaskActivity;
import com.reminder.databinding.RowHomeLayoutBinding;
import com.reminder.utils.AppConstats;
import com.reminder.utils.NotificationPublisher;
import com.reminder.utils.SharedHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


    private Context mContext;
    private List<Task> availabilityData;
    String stPriority="";
    PendingIntent pi;
    private AlarmManager alarmManager = null;
    final    Calendar calendar = Calendar.getInstance();
    private ScheduledExecutorService scheduleTaskExecutor;
    HomeAdapter adapter;
    String getTime="",currentTime="";
    public HomeAdapter(Context mContext, List<Task> availabilityData) {
        this.mContext = mContext;
        this.availabilityData = availabilityData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowHomeLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task modelObject = availabilityData.get(position);
        adapter = new HomeAdapter(mContext, availabilityData);

        if (modelObject != null) {

          holder.rowHomeLayoutBinding.txTittle.setText(modelObject.getTask_description());


            getTime = modelObject.getTime();
            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            Date dateObj = null;
            try {
                dateObj = sdf.parse(getTime );
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String timein12Format=new SimpleDateFormat("KK:mm a").format(dateObj);

            Log.e("sdjfdshf", "onBindViewHolder: "+timein12Format);

            holder.rowHomeLayoutBinding.txTime.setText(timein12Format);
          holder.rowHomeLayoutBinding.txDate.setText(modelObject.getAdded_date());
            String getDate = modelObject.getAdded_date();
            Log.e("sndsndjs", "getDate: "+getDate);

            Date d = new Date();
            CharSequence currentDate  = DateFormat.format("dd-MM-yyyy", d.getTime());




            scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

            scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    String time;
                    Calendar calander = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");

                    currentTime = simpleDateFormat.format(calander.getTime());
                    Log.e("dsfkdsjfkj", "currentTime: " + currentTime);
                    getTime = modelObject.getTime();
                    final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
                    Date dateObj = null;
                    try {
                        dateObj = sdf.parse(getTime );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String getTime=new SimpleDateFormat("KK:mm a").format(dateObj);

                    Log.e("sdjfdshf", "getTime: "+getTime);


                    Log.e("dsfkdsjfkj", "getTime: " + getTime);
                    if (currentTime.equals(getTime)) {
                        Log.e("jhjhhhjkjk", "getTime: " + getTime);
                        Log.e("jhjhhhjkjk", "currentTime: " + currentTime);
                        String[] units = getTime.split(":");
                        int hours = Integer.parseInt(units[0]); //first element
                        Log.e("hghghgh", "hours: "+units[1]);
                        int minutes = Integer.parseInt((units[1].substring(0,2)));
                        Log.e("hghghgh", "minutes: "+minutes);
                        int grand_min=hours*60+minutes;
                        int seconds=grand_min*60;

                        Log.e("sjdfshfjh", "run: "+seconds);
                        Intent intent = new Intent(mContext, NotificationPublisher.class);
                        pi = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            pi = PendingIntent.getBroadcast(mContext, 100, intent,
                                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                        } else {
                            pi = PendingIntent.getBroadcast(mContext, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        }
                        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                                + seconds, pi);
                        Toast.makeText(mContext, "Alarm set in " + seconds + " seconds", Toast.LENGTH_LONG).show();

                        //Toast.makeText(jobSchdulerActivity.this, "Its been 5 seconds", Toast.LENGTH_SHORT).show();
                    }

                }
            },0, 5, TimeUnit.SECONDS);



          stPriority=modelObject.getPriority();

          if (stPriority.equals("High")){
              holder.rowHomeLayoutBinding.vRed.setVisibility(View.VISIBLE);
              holder.rowHomeLayoutBinding.vYellow.setVisibility(View.GONE);
              holder.rowHomeLayoutBinding.vGreen.setVisibility(View.GONE);
          }
          else if (stPriority.equals("Low")){
              holder.rowHomeLayoutBinding.vYellow.setVisibility(View.VISIBLE);
              holder.rowHomeLayoutBinding.vRed.setVisibility(View.GONE);
              holder.rowHomeLayoutBinding.vGreen.setVisibility(View.GONE);
          }

          else if (stPriority.equals("Medium")){
              holder.rowHomeLayoutBinding.vYellow.setVisibility(View.GONE);
              holder.rowHomeLayoutBinding.vRed.setVisibility(View.GONE);
              holder.rowHomeLayoutBinding.vGreen.setVisibility(View.VISIBLE);
          }

        }


    }

    @Override
    public int getItemCount() {
        return availabilityData == null ? 0 : availabilityData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowHomeLayoutBinding rowHomeLayoutBinding;

        public MyViewHolder(RowHomeLayoutBinding rowHomeLayoutBinding) {
            super(rowHomeLayoutBinding.getRoot());
            this.rowHomeLayoutBinding = rowHomeLayoutBinding;
        }

    }

    private void update() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                adapter.notifyDataSetChanged(); //or notifyItemInserted or notifyItemRemoved as per your need.
                update(); // recursive call



            }
        }, 300);
    }
}
