package com.jimac.userprofile.userprofile.network.model;

public class UserProfileResponse {

    public String status;
    public String message;

    public UserProfileResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
