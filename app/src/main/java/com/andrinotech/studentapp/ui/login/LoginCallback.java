package com.andrinotech.studentapp.ui.login;


import com.andrinotech.studentapp.ui.base.BaseCallBack;

/**
 * Created by ZaidAs on 2/28/2018.
 */

public interface LoginCallback extends BaseCallBack {
    public void openMainActivity();


    public void showLoginError(String msg, String error);

    public void inValidEmail(String msg);
    public void passwordLength(String msg);

    public void inValidPassword(String msg);

}
