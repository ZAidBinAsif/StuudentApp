package com.andrinotech.studentapp.ui.ChangePassword;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.data.UserManager;
import com.andrinotech.studentapp.helper.AVProgressDialog;
import com.andrinotech.studentapp.ui.base.BaseActivity;
import com.andrinotech.studentapp.ui.login.LoginActivity;
import com.andrinotech.studentapp.utils.CommonUtils;

public class ChangePasswordActvitiy extends BaseActivity<ChanePasswordViewModel> implements ChangePasswordCallback, View.OnClickListener {
    private EditText et_password, et_confirm_password, et_email;
    private Button btn_changepassword;
    TextInputLayout input_email,input_password,input_confirmpassword;

    private AVProgressDialog mLoadingDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setCallBack(this);
        initViews();


    }

    @Override
    public int getLayout() {
        return R.layout.activity_changepassword;
    }

    @Override
    public ChanePasswordViewModel initViewModel() {
        return new ChanePasswordViewModel();
    }


    public void initViews() {
        et_confirm_password = findViewById(R.id.et_confirmpassword);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_newpassword);
        btn_changepassword = findViewById(R.id.btn_changepassword);
        btn_changepassword.setOnClickListener(this);
        input_email = findViewById(R.id.inputemail);
        input_password = findViewById(R.id.inputpassword);
        input_confirmpassword = findViewById(R.id.inputconfirmpassword);
        input_email = findViewById(R.id.inputemail);
        input_password.setEnabled(true);
        input_confirmpassword.setEnabled(true);
        input_email.setEnabled(true);
        checkArguments();
    }

    public boolean checkArguments() {
        if (getIntent() != null) {
            if (getIntent().getStringExtra("fromlogin") != null) {
                et_email.setVisibility(View.GONE);
                return true;

            }
            return false;
        }
        return false;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_changepassword:
                changepassword();
                break;

        }
    }


    private void changepassword() {

        mLoadingDialog = new AVProgressDialog(this);
        mLoadingDialog.show();
        String password = et_password.getText().toString();
        String confirmpassword = et_confirm_password.getText().toString();
        String email = " ";
        if (checkArguments()) {
            email = UserManager.getInstance().getMetaData().getUser().getEmail();
        } else {
            email=et_email.getText().toString();
        }
        getViewModel().login(password, confirmpassword, email);

    }

    @Override
    public void openMainActivity() {
        dismissDialog();
        if (checkArguments()) {
            finish();
        } else {
            Intent intent = new Intent(ChangePasswordActvitiy.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showError(String msg, String error) {

        dismissDialog();
        CommonUtils.showToast(msg);
    }

    private void dismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }


    @Override
    public void passwordLength(String msg) {
        dismissDialog();
        input_password.setError(msg);
        input_password.requestFocus();

    }

    @Override
    public void inValidPassword(String msg) {
        dismissDialog();

        if (msg.startsWith("confirm")) {
            input_confirmpassword.setError(msg);
            input_confirmpassword.requestFocus();

        } else {
            input_password.setError(msg);
            input_password.requestFocus();
        }
    }

    @Override
    public void inValidEmail(String msg) {
        dismissDialog();

        input_email.setError(msg);
        input_email.requestFocus();

    }


}
