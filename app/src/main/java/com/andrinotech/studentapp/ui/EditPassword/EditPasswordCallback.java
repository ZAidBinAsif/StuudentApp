package com.andrinotech.studentapp.ui.EditPassword;


import com.andrinotech.studentapp.ui.base.BaseCallBack;

/**
 * Created by ZaidAs on 2/28/2018.
 */

public interface EditPasswordCallback extends BaseCallBack {
    public void openMainActivity();

    public void invalidEmaim(String msg);

    public void showError(String msg, String error);

    public void passwordLength(String msg);

    public void inValidPassword(String msg);

}
