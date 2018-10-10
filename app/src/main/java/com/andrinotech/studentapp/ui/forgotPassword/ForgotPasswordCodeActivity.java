package com.andrinotech.studentapp.ui.forgotPassword;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.helper.AVProgressDialog;
import com.andrinotech.studentapp.ui.ChangePassword.ChangePasswordActvitiy;
import com.andrinotech.studentapp.ui.base.BaseActivity;
import com.andrinotech.studentapp.ui.login.LoginActivity;
import com.andrinotech.studentapp.utils.CommonUtils;

public class ForgotPasswordCodeActivity extends BaseActivity<ForgotPasswordViewModel> implements ForgotPasswordCallback, View.OnClickListener {
    private EditText et_email, et_code;
    Button btn_send_code, btn_signIn;
    private AVProgressDialog mLoadingDialog;
    TextInputLayout inputcode,input_email,input_usrname,input_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setCallBack(this);
        initViews();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_reset_code;
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
            case R.id.btn_send_code:
                sendEmail();
                break;
            default:
                break;
        }
    }

    private void sendEmail() {
        String email = et_email.getText().toString();
        String code = et_code.getText().toString();

        mLoadingDialog.show();
        getViewModel().sendConfirmCode(email, code);
    }

    private void login() {
        Intent i = new Intent(ForgotPasswordCodeActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void initViews() {
        et_email = findViewById(R.id.et_email);
        et_code = findViewById(R.id.et_code);

        input_email = findViewById(R.id.inputemail);
        inputcode = findViewById(R.id.inputcode);

        btn_send_code = findViewById(R.id.btn_send_code);
        btn_signIn = findViewById(R.id.btn_signIn);

        btn_send_code.setOnClickListener(this);
        btn_signIn.setOnClickListener(this);
        mLoadingDialog = new AVProgressDialog(this);


    }

    @Override
    public void inValidEmail(String msg) {
//        et_email.setError(msg);
//        CommonUtils.showToast(msg);
        input_email.setEnabled(true);
        input_email.setError(msg);
        dismissDialog();

    }

    @Override
    public void inValidCode(String msg) {
        inputcode.setEnabled(true);
        inputcode.setError(msg);

    }

    @Override
    public void openLoginActivity() {
//        CommonUtils.showToast("Please check your mailbox");
        dismissDialog();
        Intent intent = new Intent(ForgotPasswordCodeActivity.this, ChangePasswordActvitiy.class);
        startActivity(intent);
        finish();
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

    @Override
    public void onBackPressed() {
        Intent login = new Intent(ForgotPasswordCodeActivity.this, LoginActivity.class);
        startActivity(login);
        finish();
    }
}
