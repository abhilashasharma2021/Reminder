package com.reminder.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reminder.Entity.Task;
import com.reminder.databinding.RowHomeLayoutBinding;
import com.reminder.utils.AppConstats;
import com.reminder.utils.SharedHelper;

import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


    private Context mContext;
    private List<Task> availabilityData;
    String stPriority="";

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


        if (modelObject != null) {

          holder.rowHomeLayoutBinding.txTittle.setText(modelObject.getTask());
          holder.rowHomeLayoutBinding.txDate.setText(modelObject.getDate());
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
}
