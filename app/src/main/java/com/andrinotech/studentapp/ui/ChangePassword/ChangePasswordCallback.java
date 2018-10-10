package com.andrinotech.studentapp.ui.ChangePassword;


import com.andrinotech.studentapp.ui.base.BaseCallBack;

/**
 * Created by ZaidAs on 2/28/2018.
 */

public interface ChangePasswordCallback extends BaseCallBack {
    public void openMainActivity();


    public void showError(String msg, String error);

    public void passwordLength(String msg);

    public void inValidPassword(String msg);
    public void inValidEmail(String msg);

}
