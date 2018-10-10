package com.andrinotech.studentapp.ui.register;


import com.andrinotech.studentapp.ui.base.BaseCallBack;

public interface RegisterUserCallBack extends BaseCallBack {
    public  void ValidationError(RegisterViewModel.validationEnum validationEnum);
    public void openLoginActivity();
    public void EmailAlreadyExist(String str);
    public void InternetError(String str);
}
