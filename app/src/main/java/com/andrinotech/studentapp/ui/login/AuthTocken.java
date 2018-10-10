package com.andrinotech.studentapp.ui.login;



/**
 * Created by ZaidAs on 3/2/2018.
 */

public class AuthTocken {
    private String email;
    private String pass;

    public AuthTocken setAuthTockenForUserName(String email, String pwd) {
        this.email = email;
        this.pass = pwd;

        return this;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
