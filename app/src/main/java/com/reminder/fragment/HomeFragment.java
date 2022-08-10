package com.reminder.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.reminder.R;
import com.reminder.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
FragmentHomeBinding binding;
    private int prog =0;
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
        binding.txName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prog<=90){
                    prog+=10;
                    updateProgreesbar();
                }

            }
        });
        binding.txDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prog>=10){
                    prog-=10;
                    updateProgreesbar();
                }

            }
        });
        return view;
    }


    private void updateProgreesbar(){
     binding.progressBar.setProgress(prog);
     binding.txViewProgress.setText(prog+"%");


    }
}