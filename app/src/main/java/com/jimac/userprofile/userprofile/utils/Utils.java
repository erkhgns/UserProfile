package com.jimac.userprofile.userprofile.utils;

import android.text.TextUtils;
import android.util.Patterns;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

public class Utils {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    public static String getStringCurrentDate(){
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidNumber(String phoneNumber){
      return  Pattern.matches( "^(09|\\+639)\\d{9}$", phoneNumber);
    }

    public static boolean isValidName(String name){
        return  Pattern.matches( "^[a-zA-Z,.!? ]*$", name);
    }

    public static ArrayList<String> getGender (){
        return new ArrayList<>(Arrays.asList("Male","Female"));
    }

}
