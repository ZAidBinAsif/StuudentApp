package com.andrinotech.studentapp.ui.profile;


import android.net.Uri;

import com.andrinotech.studentapp.ui.base.BaseCallBack;
import com.andrinotech.studentapp.ui.register.RegisterViewModel;

import java.io.File;

public interface ProfileEditCallBack extends BaseCallBack {
    public  void ValidationError(RegisterViewModel.validationEnum validationEnum);
    public void SuccessFullyUpdated();
    public void ErrorOnEdit(String str);
    public void InternetError(String str);
    void getImageFromIntent(Uri inputImageUri1, File p);

}
