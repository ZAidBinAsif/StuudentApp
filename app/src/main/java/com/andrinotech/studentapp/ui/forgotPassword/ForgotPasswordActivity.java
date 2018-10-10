package com.andrinotech.studentapp.ui.forgotPassword;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.helper.AVProgressDialog;
import com.andrinotech.studentapp.ui.base.BaseActivity;
import com.andrinotech.studentapp.ui.login.LoginActivity;
import com.andrinotech.studentapp.utils.CommonUtils;

public class ForgotPasswordActivity extends BaseActivity<ForgotPasswordViewModel> implements ForgotPasswordCallback, View.OnClickListener {
    private AppCompatEditText et_email;
    TextView havcode,sendagain;
    Button btn_sendEmail, btn_signIn;
    private AVProgressDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setCallBack(this);
        initViews();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_forgot_password;
    }

    @Override
    public ForgotPasswordViewModel initViewModel() {
        return new ForgotPasswordViewModel();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signIn:
                login();
                break;
            case R.id.btn_send_email:
                sendEmail();
                break;
            case R.id.havcode:
                openChangePasswordActivity();
                break;
            case R.id.sendagain:
                sendEmail();
                break;

            default:
                break;
        }
    }

    private void sendEmail() {
        String email = et_email.getText().toString();

        mLoadingDialog.show();
        getViewModel().sendEmail(email);
    }

    private void login() {
        Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void initViews() {
        et_email = findViewById(R.id.et_email);
        havcode = findViewById(R.id.havcode);
        sendagain = findViewById(R.id.sendagain);


        btn_sendEmail = findViewById(R.id.btn_send_email);
        btn_signIn = findViewById(R.id.btn_signIn);

        havcode.setOnClickListener(this);
        btn_sendEmail.setOnClickListener(this);
        btn_signIn.setOnClickListener(this);
        mLoadingDialog = new AVProgressDialog(this);


    }

    @Override
    public void inValidEmail(String msg) {
//        et_email.setError(msg);
        dismissDialog();
        CommonUtils.showToast(msg);

    }

    @Override
    public void inValidCode(String msg) {

    }

    public void openChangePasswordActivity() {

        Intent login = new Intent(ForgotPasswordActivity.this, ForgotPasswordCodeActivity.class);
        startActivity(login);
        finish();
    }

    @Override
    public void openLoginActivity() {
        CommonUtils.showToast("Reset Code has been sen to your email");
        dismissDialog();
        openChangePasswordActivity();

    }

    @Override
    public void UserNotFound(String msg) {
        dismissDialog();
        CommonUtils.showToast(msg);
    }

    @Override
    public void internetError(String msg) {
        dismissDialog();
        CommonUtils.showToast(msg);
    }

    private void dismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }


}
