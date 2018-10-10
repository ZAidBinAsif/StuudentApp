package com.andrinotech.studentapp.ui.ChangePassword;


import android.util.Patterns;


import com.andrinotech.studentapp.StudentApp;
import com.andrinotech.studentapp.data.UserManager;
import com.andrinotech.studentapp.ui.base.BaseViewModel;
import com.andrinotech.studentapp.ui.login.MetaData;
import com.andrinotech.studentapp.utils.CommonUtils;
import com.andrinotech.studentapp.utils.NetworkUtils;
import com.andrinotech.studentapp.utils.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChanePasswordViewModel extends BaseViewModel<ChangePasswordCallback> {


    public boolean isEmailAndPasswordValid(String pasword, String confirmpassword, String email) {
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
        if (!StringUtils.passwordMatches(pasword, confirmpassword)) {
            getmCallback().inValidPassword("Password and Confirm Password does not match.");
            isValid = false;
        }
        if (StringUtils.isNullOrEmpty(email)) {
            getmCallback().inValidEmail("Email Required");
            isValid = false;
        }
        if (!StringUtils.isNullOrEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            getmCallback().inValidEmail("invalid Email");
            isValid = false;
        }
        return isValid;
    }

    public void login(String password, final String confirmpassword, String s) {
        if (isEmailAndPasswordValid(password, confirmpassword, s)) {
            final ChangePasswordApiModel changePasswordApiModel = new ChangePasswordApiModel(password, s);
            getmCompositeDisposable().add(getmRequestHandler().changepassword(changePasswordApiModel).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new Consumer<MetaData>() {
                        @Override
                        public void accept(MetaData metaData) throws Exception {
                            if (metaData == null) {
                                getmCallback().inValidEmail("Not Available");
                                return;
                            } else if (metaData.getError().getCode() == 302) {
                                getmCallback().inValidEmail(metaData.getError().getMessage());
                                return;
                            }
                            UserManager.getInstance().setMetaData(metaData);
                            getmCallback().openMainActivity();
                            CommonUtils.showToast("Password has been changed Successfully, please login");

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
