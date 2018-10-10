package com.andrinotech.studentapp.ui.register;


import android.util.Patterns;

import com.andrinotech.studentapp.StudentApp;
import com.andrinotech.studentapp.data.UserManager;
import com.andrinotech.studentapp.ui.base.BaseViewModel;
import com.andrinotech.studentapp.ui.login.MetaData;
import com.andrinotech.studentapp.utils.NetworkUtils;
import com.andrinotech.studentapp.utils.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegisterViewModel extends BaseViewModel<RegisterUserCallBack> {
    public enum validationEnum {
        Name, UserNAme, EMAIL, PASSWORD, WRONG_EMAIL, PASWWORD_LENGTH, ALL_SPACES
    }

    public boolean areFieldsValid(String name, String username, String email, String password) {
        boolean isValid = true;
        if (StringUtils.isNullOrEmpty(name)) {
            getmCallback().ValidationError(validationEnum.Name);
            isValid = false;
        }
        if (StringUtils.isNullOrEmpty(username)) {
            getmCallback().ValidationError(validationEnum.UserNAme);
            isValid = false;
        }
        if (StringUtils.isNullOrEmpty(email)) {
            getmCallback().ValidationError(validationEnum.EMAIL);
            isValid = false;
        }
        if (!StringUtils.isNullOrEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            getmCallback().ValidationError(validationEnum.WRONG_EMAIL);
            isValid = false;
        }
        if (StringUtils.isNullOrEmpty(password)) {
            getmCallback().ValidationError(validationEnum.PASSWORD);
            isValid = false;
        }
        if (!StringUtils.passwordLength(password)) {
            getmCallback().ValidationError(validationEnum.PASWWORD_LENGTH);
            isValid = false;
        }
        if (StringUtils.isAllSpaces(password)) {
            getmCallback().ValidationError(validationEnum.ALL_SPACES);
            isValid = false;
        }


        return isValid;
    }


    public void registerUser(String name, String username, String email, String password) {
        if (areFieldsValid(name, username, email, password)) {

            RegisterUserApidModel registerUserApidModel = new RegisterUserApidModel();
            registerUserApidModel.setRegisterUserApidModel(name, username, email, password);
//            getmRequestHandler().tRegistserUser(registerUserApidModel);
            getmCompositeDisposable().add(getmRequestHandler()
                    .getRegisterUser(registerUserApidModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MetaData>() {
                        @Override
                        public void accept(MetaData metaData) throws Exception {
                            if (metaData == null ) {
                                getmCallback().EmailAlreadyExist("Not Available");
                                return;
                            } else if (metaData.getError().getCode()==302){
                                getmCallback().EmailAlreadyExist(metaData.getError().getMessage());
                                return;
                            }
                            saveUserData(metaData);
                            getmCallback().openLoginActivity();

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            if (NetworkUtils.isNetworkConnected(StudentApp.getInstance().getApplicationContext())) {
                                getmCallback().EmailAlreadyExist("Check your internet connection");
                            } else {
                                getmCallback().EmailAlreadyExist("No Internet Connection");

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
