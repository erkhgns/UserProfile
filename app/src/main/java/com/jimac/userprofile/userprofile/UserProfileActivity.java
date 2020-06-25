package com.jimac.userprofile.userprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.jimac.userprofile.R;
import com.jimac.userprofile.databinding.ActivityMainBinding;
import com.jimac.userprofile.userprofile.utils.DatePickerListener;
import com.jimac.userprofile.userprofile.utils.Utils;

import java.util.Calendar;

public class UserProfileActivity extends AppCompatActivity implements DatePickerListener.ViewListener {

    private ActivityMainBinding binding;
    private UserProfileViewModel userProfileViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        userProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        binding.setViewModel(userProfileViewModel);
        binding.setLifecycleOwner(this);

        userProfileViewModel.initDatePickerListener(this);
        observeSystemMessage();
        showGender();
    }

    private void showGender(){
        binding.spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                userProfileViewModel.onGenderSelected(Utils.getGender().get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Utils.getGender());
        binding.spinnerGender.setAdapter(arrayAdapter);
        binding.spinnerGender.setSelection(0);
    }

    private void observeSystemMessage(){
        userProfileViewModel.getSystemMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(UserProfileActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showDatePicker(DatePickerListener.DateListener dateListener) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-1);
        DatePickerDialog datePickerDialog = new DatePickerDialog(UserProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                dateListener.onSetDate(calendar);

            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}