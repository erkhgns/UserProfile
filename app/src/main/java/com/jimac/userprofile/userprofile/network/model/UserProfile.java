package com.jimac.userprofile.userprofile.network.model;

public class UserProfile {
    private String fullname;
    private String email;
    private String mobile;
    private String birthDate;
    private String age;
    private String gender;


    public UserProfile(String fullname, String email, String mobile, String birthDate, String age, String gender) {
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.birthDate = birthDate;
        this.age = age;
        this.gender = gender;
    }


}
