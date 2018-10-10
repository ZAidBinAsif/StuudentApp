package com.andrinotech.studentapp.ui.forgotPassword;


import com.andrinotech.studentapp.ui.base.BaseCallBack;

public interface ForgotPasswordCallback extends BaseCallBack {
    public void inValidEmail(String msg);
    public void inValidCode(String msg);

    public void openLoginActivity();

    public void UserNotFound(String msg);

    public void internetError(String msg);
}
