package com.andrinotech.studentapp.ui.forgotPassword;

/**
 * Created by AliAh on 08/03/2018.
 */

public class ForgotPasswordApiModel {
    String email;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    String code;

    public ForgotPasswordApiModel() {
    }

    public  ForgotPasswordApiModel(String email,String code){
        this.email=email;
        this.code=code;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
