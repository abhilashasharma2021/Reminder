package com.reminder.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.ConditionVariable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reminder.R;
import com.reminder.activity.EditPersonalInfoActivity;
import com.reminder.databinding.FragmentHomeBinding;
import com.reminder.databinding.FragmentSettingBinding;


public class SettingFrag extends Fragment {
FragmentSettingBinding binding;
View view;
Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();
        context = getActivity();

        binding.txEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getActivity(), EditPersonalInfoActivity.class));

            }
        });
        return view;
    }
}