package com.andrinotech.studentapp.ui.ChangePassword;

/**
 * Created by AliAh on 08/03/2018.
 */

public class ChangePasswordApiModel {
    String password;
    String email;
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password, String email) {
        this.password = password;
        this.email=email;
    }

    public ChangePasswordApiModel(String password, String email) {
        this.email=email;

        this.password = password;
    }
}
