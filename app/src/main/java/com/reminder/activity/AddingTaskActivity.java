package com.reminder.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.reminder.R;
import com.reminder.databinding.ActivityAddingTaskBinding;


public class AddingTaskActivity extends AppCompatActivity {
    ActivityAddingTaskBinding binding;
    String[] convention;
    String selectedconvention;
    int sp_convention;
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



    }
}