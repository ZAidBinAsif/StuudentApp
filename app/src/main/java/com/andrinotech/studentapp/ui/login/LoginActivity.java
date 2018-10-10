package com.andrinotech.studentapp.ui.login;


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
import com.andrinotech.studentapp.data.UserManager;
import com.andrinotech.studentapp.helper.AVProgressDialog;
import com.andrinotech.studentapp.ui.base.BaseActivity;
import com.andrinotech.studentapp.ui.forgotPassword.ForgotPasswordActivity;
import com.andrinotech.studentapp.ui.register.RegisterUserActivity;
import com.andrinotech.studentapp.utils.CommonUtils;

public class LoginActivity extends BaseActivity<LoginViewModel> implements LoginCallback, View.OnClickListener {
    private EditText et_email, et_password;
    private TextView tv_forogotpassword, btn_signUp;
    private Button btn_signIn;
    TextInputLayout inputfullname,input_email,input_usrname,input_password;

    private AVProgressDialog mLoadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(UserManager.getInstance().checkUserIfLoggedIn()){
            openMainActivity();
            return;
        }
        getViewModel().setCallBack(this);
        initViews();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel initViewModel() {
        return new LoginViewModel();
    }


    public void initViews() {
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_signIn = findViewById(R.id.btn_signIn);
        input_email = findViewById(R.id.inputemail);
        input_password = findViewById(R.id.inputpassword);

        tv_forogotpassword = findViewById(R.id.tv_forogotpassword);
        btn_signUp = findViewById(R.id.signup);
        btn_signIn.setOnClickListener(this);
        btn_signUp.setOnClickListener(this);
        tv_forogotpassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signIn:
                login();
                break;
            case R.id.signup:
                signup();
                break;
            case R.id.tv_forogotpassword:
                forgotPassword();
                break;
            default:
                break;
        }
    }


    private void forgotPassword() {
        Intent forgotActivity = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(forgotActivity);
        finish();
    }

    private void signup() {
        Intent signup = new Intent(LoginActivity.this, RegisterUserActivity.class);
        startActivity(signup);
        finish();

    }


    private void login() {

        mLoadingDialog = new AVProgressDialog(this);
        mLoadingDialog.show();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        email = email.trim().toLowerCase();
        getViewModel().login(email, password);

    }

    @Override
    public void openMainActivity() {
        dismissDialog();
        finish();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);

    }

    @Override
    public void showLoginError(String msg, String error) {

        dismissDialog();
        CommonUtils.showToast(msg);
    }

    private void dismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }


    @Override
    public void inValidEmail(String msg) {
        dismissDialog();
        CommonUtils.showToast(msg);
        input_email.setEnabled(true);
        input_email.setError(msg);

//        et_email.setError(msg);
//        et_email.requestFocus();

    }

    @Override
    public void passwordLength(String msg) {
        dismissDialog();
        input_password.setEnabled(true);
        input_password.setError(msg);

//        CommonUtils.showToast(msg);
//        et_password.setError("asads");
//        et_password.requestFocus();
    }

    @Override
    public void inValidPassword(String msg) {
        dismissDialog();
        input_password.setEnabled(true);
        input_password.setError(msg);
//        CommonUtils.showToast(msg);

//        et_password.setError(msg);
//        et_password.requestFocus();

    }
}
