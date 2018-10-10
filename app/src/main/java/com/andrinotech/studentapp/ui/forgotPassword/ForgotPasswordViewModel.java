package com.andrinotech.studentapp.ui.forgotPassword;


import android.util.Patterns;

import com.andrinotech.studentapp.data.UserManager;
import com.andrinotech.studentapp.ui.base.BaseViewModel;
import com.andrinotech.studentapp.ui.login.MetaData;
import com.andrinotech.studentapp.utils.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ForgotPasswordViewModel extends BaseViewModel<ForgotPasswordCallback> {
    public boolean isEmailValid(String email) {
        boolean isValid = true;
        if (StringUtils.isNullOrEmpty(email)) {
            getmCallback().inValidEmail("Email Address Required.");
            isValid = false;
        } else if (!StringUtils.isNullOrEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            getmCallback().inValidEmail("Invalid email");
            isValid = false;
        }
        return isValid;
    }

    public void sendEmail(String email) {
        if (isEmailValid(email)) {
            ForgotPasswordApiModel apiModel = new ForgotPasswordApiModel();
            apiModel.setEmail(email);
            getmCompositeDisposable().add(getmRequestHandler().getForgotPassword(apiModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<MetaData>() {
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

                            getmCallback().openLoginActivity();


                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            getmCallback().internetError("Please check your internet");
                        }
                    }));

        }

    }

    public void sendConfirmCode(String email, String code) {
        if (StringUtils.isNullOrEmpty(email)) {
            getmCallback().inValidCode("Code Required.");
            return;
        }
        if (isEmailValid(email)) {
            ForgotPasswordApiModel apiModel = new ForgotPasswordApiModel(email, code);
            getmCompositeDisposable().add(getmRequestHandler().senResetCode(apiModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<MetaData>() {
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
                            getmCallback().openLoginActivity();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            getmCallback().internetError("Please check your internet");
                        }
                    }));

        }

    }
}
