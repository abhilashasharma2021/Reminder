package com.reminder.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reminder.Database.DatabaseClient;
import com.reminder.Entity.Task;
import com.reminder.R;
import com.reminder.activity.AddingTaskActivity;
import com.reminder.activity.AssigningTaskActivity;
import com.reminder.activity.LoginActivity;
import com.reminder.activity.SignUpActivity;
import com.reminder.adapter.HomeAdapter;
import com.reminder.databinding.FragmentHomeBinding;

import java.util.List;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    private int prog = 0;
    View view;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();
        context = getActivity();
        updateProgreesbar();
        binding.rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.txName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prog <= 90) {
                    prog += 10;
                    updateProgreesbar();
                }

            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddingTaskActivity.class));

            }
        });
        binding.txDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prog >= 10) {
                    prog -= 10;
                    updateProgreesbar();
                }

            }
        });

        getTasks();
        return view;
    }


    private void updateProgreesbar() {
        binding.progressBar.setProgress(prog);
        binding.txViewProgress.setText(prog + "%");


    }


    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getActivity())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                HomeAdapter adapter = new HomeAdapter(getActivity(),tasks);
                binding.rvHome.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }
}