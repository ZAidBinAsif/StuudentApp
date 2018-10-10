package com.andrinotech.studentapp.ui.register;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andrinotech.studentapp.HomeActivity;
import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.helper.AVProgressDialog;
import com.andrinotech.studentapp.ui.base.BaseActivity;
import com.andrinotech.studentapp.ui.login.LoginActivity;
import com.andrinotech.studentapp.utils.CommonUtils;

public class RegisterUserActivity extends BaseActivity<RegisterViewModel> implements RegisterUserCallBack, View.OnClickListener {
    private EditText et_name, et_username, et_email, et_password;
    TextView signIn;
    Button signUp;
TextInputLayout inputfullname,input_email,input_usrname,input_password;
    private AVProgressDialog mLoadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setCallBack(this);
        initViews();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }


    @Override
    public RegisterViewModel initViewModel() {
        return new RegisterViewModel();
    }

    public void initViews() {
        et_name = findViewById(R.id.et_fullname);
        inputfullname = findViewById(R.id.inputfullname);
        input_email = findViewById(R.id.inputemail);
        input_password = findViewById(R.id.inputpassword);
        input_usrname = findViewById(R.id.inputusername);
        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        signUp = findViewById(R.id.btn_signUp);
        signIn = findViewById(R.id.signIn);
        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);
        mLoadingDialog = new AVProgressDialog(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signUp:
                register();
                break;
            case R.id.signIn:
                login();
                break;
            default:
                break;
        }
    }


    private void dismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }


    private void login() {
        Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void register() {
        mLoadingDialog.show();
        String name = et_name.getText().toString();
        String username = et_username.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        getViewModel().registerUser(name, username, email, password);

    }

    @Override
    public void ValidationError(RegisterViewModel.validationEnum validationEnum) {
        inputfullname.setEnabled(true);
        input_usrname.setEnabled(true);
        input_email.setEnabled(true);
        input_password.setEnabled(true);

        if (validationEnum.equals(RegisterViewModel.validationEnum.Name)) {
            inputfullname.setError(" Name Required.");
//            CommonUtils.showToast(" Name Required.");

        }
        if (validationEnum.equals(RegisterViewModel.validationEnum.UserNAme)) {
            input_usrname.setError("UserName Required.");
//            CommonUtils.showToast("UserName Required.");

        }
        if (validationEnum.equals(RegisterViewModel.validationEnum.EMAIL)) {
            input_email.setError("Email Required.");
//            CommonUtils.showToast("Email Required.");

        }
        if (validationEnum.equals(RegisterViewModel.validationEnum.WRONG_EMAIL)) {
            input_email.setError("Invalid email format.");
//            CommonUtils.showToast("Invalid email format.");


        }

        if (validationEnum.equals(RegisterViewModel.validationEnum.PASSWORD)) {
            input_password.setError("Password Required.");
//            CommonUtils.showToast("Password Required.");

        }
        if (validationEnum.equals(RegisterViewModel.validationEnum.ALL_SPACES)) {
            input_password.setError("Password must contain one character");
//            CommonUtils.showToast("Password must contain one character");

        }

        if (validationEnum.equals(RegisterViewModel.validationEnum.PASWWORD_LENGTH)) {
            input_password.setError("Password length must be between 3 to 15 character");
//            CommonUtils.showToast("Password length must be between 3 to 15 character");

        }


        dismissDialog();
    }

    @Override
    public void openLoginActivity() {
        dismissDialog();
        Intent intent = new Intent(RegisterUserActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void EmailAlreadyExist(String str) {
        dismissDialog();
        CommonUtils.showToast(str);
    }

    @Override
    public void InternetError(String str) {
        dismissDialog();
        CommonUtils.showToast(str);
    }

    @Override
    public void onBackPressed() {
        Intent login = new Intent(RegisterUserActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }
}
