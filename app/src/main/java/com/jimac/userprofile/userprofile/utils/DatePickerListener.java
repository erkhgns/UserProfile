package com.jimac.userprofile.userprofile.utils;

import java.util.Calendar;

public interface DatePickerListener {
    interface DateListener{
        void onSetDate(Calendar calendar);
    }

    interface ViewListener{
        void showDatePicker(DateListener dateListener);
    }
}
