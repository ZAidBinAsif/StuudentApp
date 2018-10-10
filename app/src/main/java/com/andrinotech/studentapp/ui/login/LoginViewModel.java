package com.andrinotech.studentapp.ui.login;


import android.util.Patterns;

import com.andrinotech.studentapp.StudentApp;
import com.andrinotech.studentapp.data.UserManager;
import com.andrinotech.studentapp.ui.base.BaseViewModel;
import com.andrinotech.studentapp.utils.NetworkUtils;
import com.andrinotech.studentapp.utils.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel<LoginCallback> {


    public boolean isEmailAndPasswordValid(String email, String password) {
        boolean isValid = true;
        //validate email and password
        if (StringUtils.isNullOrEmpty(email)) {
            getmCallback().inValidEmail("Email Address Required.");
            isValid = false;
        }
        if (!StringUtils.isNullOrEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            getmCallback().inValidEmail("Invalid Email.");
            isValid = false;
        }
        if (!StringUtils.passwordLength(password)) {
            getmCallback().passwordLength("Pasword length must be between 3 to 15 character.");
            isValid = false;

        }
        if (StringUtils.isNullOrEmpty(password)) {
            getmCallback().inValidPassword("Password Required.");
            isValid = false;
        }
        return isValid;
    }

    public void login(String email, final String password) {
        if (isEmailAndPasswordValid(email, password)) {
            final AuthTocken authTocken = new AuthTocken();
            authTocken.setAuthTockenForUserName(email, password);
            getmCompositeDisposable().add(getmRequestHandler().signIn(authTocken).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new Consumer<MetaData>() {
                        @Override
                        public void accept(MetaData metaData) throws Exception {
                            if (metaData == null) {
                                getmCallback().showLoginError("Not Available", "");
                                return;
                            } else if (metaData.getError().getCode() == 302) {
                                getmCallback().showLoginError(metaData.getError().getMessage(), "login");
                                return;
                            }
                            saveUserData(metaData);
                            getmCallback().openMainActivity();

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            if (NetworkUtils.isNetworkConnected(StudentApp.getInstance().getApplicationContext())) {
                                getmCallback().showLoginError("Check your internet connection", "");
                            } else {
                                getmCallback().showLoginError("No Internet Connection", "");

                            }

                        }
                    }));
        }
    }

    private void saveUserData(MetaData metaData) {
        UserManager.getInstance().setMetaData(metaData);
        UserManager.getInstance().setUserLogin();


    }


}
