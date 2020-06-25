package com.jimac.userprofile.userprofile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jimac.userprofile.userprofile.network.WebServiceRepository;
import com.jimac.userprofile.userprofile.network.model.UserProfile;
import com.jimac.userprofile.userprofile.utils.DatePickerListener;
import com.jimac.userprofile.userprofile.utils.INetworkListener;
import com.jimac.userprofile.userprofile.utils.Utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class UserProfileViewModel extends AndroidViewModel {
    public MutableLiveData<String> fullName = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> mobileNumber = new MutableLiveData<>();
    public MutableLiveData<String> birthDate = new MutableLiveData<>();
    public MutableLiveData<String> age = new MutableLiveData<>();

    private DatePickerListener.ViewListener datepickerListener;
    private Calendar birthDateInCalendar;
    private MutableLiveData<String> systemMessage = new MutableLiveData<>();
    private String gender = "";
    private WebServiceRepository webServiceRepository = new WebServiceRepository();


    public UserProfileViewModel(@NonNull Application application) {
        super(application);

        birthDateInCalendar = Calendar.getInstance();
        birthDateInCalendar.add(Calendar.YEAR, -1);
        birthDate.setValue(Utils.simpleDateFormat.format(birthDateInCalendar.getTime()));
        calculateAge();
    }

    public LiveData<String> getSystemMessage() {
        return systemMessage;
    }


    public void initDatePickerListener(DatePickerListener.ViewListener datepickerListener) {
        this.datepickerListener = datepickerListener;
    }

    public void onGenderSelected(String gender){
        this.gender = gender;
    }

    public void onBirthDateClick() {
        datepickerListener.showDatePicker(new DatePickerListener.DateListener() {
            @Override
            public void onSetDate(Calendar calendar) {
                birthDate.setValue(Utils.simpleDateFormat.format(calendar.getTime()));
                birthDateInCalendar = calendar;
                calculateAge();
            }
        });
    }


    public void onSubmitUserProfile() {

        if (isCompleteFields()) {
            if (Utils.isValidName(fullName.getValue())) {
                if (Integer.parseInt(age.getValue()) >= 18) {
                    if (Utils.isValidEmail(email.getValue())) {
                        if (Utils.isValidNumber(mobileNumber.getValue())) {
                            UserProfile userProfile = new UserProfile(fullName.getValue(), email.getValue(), mobileNumber.getValue(), birthDate.getValue(), age.getValue(), gender);

                            webServiceRepository.registerUserProfile(userProfile, new INetworkListener() {
                                @Override
                                public void showNetworkResponse(String message) {
                                    systemMessage.postValue(message);
                                }
                            });


                        } else {
                            systemMessage.setValue("Invalid mobile number");
                        }
                    } else {
                        systemMessage.setValue("Invalid email format");

                    }

                } else {
                    systemMessage.setValue("Age must be greater than or equal to 18");

                }
            } else {
                systemMessage.setValue("Invalid name");

            }


        } else {
            systemMessage.setValue("Incomplete fields");
        }

    }


    private boolean isCompleteFields() {
        return fullName.getValue() != null && !fullName.getValue().isEmpty() &&
                email.getValue() != null && !email.getValue().isEmpty() &&
                mobileNumber.getValue() != null && !mobileNumber.getValue().isEmpty();
    }

    private void calculateAge() {
        LocalDate l1 = LocalDate.of(birthDateInCalendar.get(Calendar.YEAR), birthDateInCalendar.get(Calendar.MONTH), birthDateInCalendar.get(Calendar.DATE));
        LocalDate now1 = LocalDate.now();
        Period diff1 = Period.between(l1, now1);

        age.setValue(String.valueOf(diff1.getYears()));
    }

}
