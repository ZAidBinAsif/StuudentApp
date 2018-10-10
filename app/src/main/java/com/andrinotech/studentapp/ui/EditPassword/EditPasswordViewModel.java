package com.andrinotech.studentapp.ui.EditPassword;


import com.andrinotech.studentapp.StudentApp;
import com.andrinotech.studentapp.data.UserManager;
import com.andrinotech.studentapp.ui.ChangePassword.ChangePasswordApiModel;
import com.andrinotech.studentapp.ui.base.BaseViewModel;
import com.andrinotech.studentapp.ui.login.MetaData;
import com.andrinotech.studentapp.utils.NetworkUtils;
import com.andrinotech.studentapp.utils.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EditPasswordViewModel extends BaseViewModel<EditPasswordCallback> {


    public boolean isEmailAndPasswordValid(String pasword, String confirmpassword, String oldpassword) {
        boolean isValid = true;
        if (StringUtils.isNullOrEmpty(pasword)) {
            getmCallback().inValidPassword("Password Required.");
            isValid = false;
        }
        if (!StringUtils.passwordLength(pasword)) {
            getmCallback().passwordLength("length must be between 3 to 15 character.");
            isValid = false;

        }
        if (StringUtils.isNullOrEmpty(confirmpassword)) {
            getmCallback().inValidPassword("confirm password Password Required.");
            isValid = false;
        }
        if (StringUtils.passwordMatches(pasword, confirmpassword)) {
            getmCallback().inValidPassword("Password and Confirm Password does not match.");
            isValid = false;
        }
        if (StringUtils.isNullOrEmpty(oldpassword)) {
            getmCallback().invalidEmaim("Current Password Required");
            isValid = false;
        }
        return isValid;
    }

    public void editpassword(String password, final String confirmpassword, String s) {
        if (isEmailAndPasswordValid(password, confirmpassword, s)) {
            final ChangePasswordApiModel changePasswordApiModel = new ChangePasswordApiModel(password, UserManager.getInstance().getMetaData().getUser().getEmail());
            getmCompositeDisposable().add(getmRequestHandler().editPassword(changePasswordApiModel).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new Consumer<MetaData>() {
                        @Override
                        public void accept(MetaData metaData) throws Exception {
                            if (metaData == null) {
                                getmCallback().showError("Not Available", "");
                                return;
                            } else if (metaData.getError() != null) {
                                getmCallback().showError(metaData.getError().getMessage(), "login");
                                return;
                            }
                            UserManager.getInstance().setMetaData(metaData);
                            getmCallback().openMainActivity();

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            if (NetworkUtils.isNetworkConnected(StudentApp.getInstance().getApplicationContext())) {
                                getmCallback().showError("Check your internet connection", "");
                            } else {
                                getmCallback().showError("No Internet Connection", "");

                            }

                        }
                    }));
        }
    }

}
