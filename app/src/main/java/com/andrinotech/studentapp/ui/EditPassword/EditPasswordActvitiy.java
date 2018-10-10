package com.andrinotech.studentapp.ui.EditPassword;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.helper.AVProgressDialog;
import com.andrinotech.studentapp.ui.base.BaseActivity;
import com.andrinotech.studentapp.utils.CommonUtils;

public class EditPasswordActvitiy extends BaseActivity<EditPasswordViewModel> implements EditPasswordCallback, View.OnClickListener {
    private EditText et_password, et_confirm_password, et_email;
    private Button btn_changepassword;
    private AVProgressDialog mLoadingDialog;
    private EditText et_currentpassword;

    TextInputLayout input_email, inputold_pasword, input_password, input_confirmpassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setCallBack(this);
        initViews();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_editpassword;
    }

    @Override
    public EditPasswordViewModel initViewModel() {
        return new EditPasswordViewModel();
    }


    public void initViews() {
        et_currentpassword = findViewById(R.id.et_currentpassword);
        et_email = findViewById(R.id.et_email);
        input_email = findViewById(R.id.inputemail);
        input_password = findViewById(R.id.inputpassword);
        input_confirmpassword = findViewById(R.id.inputconfirmpassword);
        input_email = findViewById(R.id.inputemail);
        inputold_pasword = findViewById(R.id.inputold_pasword);
        input_password.setEnabled(true);
        inputold_pasword.setEnabled(true);
        input_confirmpassword.setEnabled(true);
        input_email.setEnabled(true);

        et_confirm_password = findViewById(R.id.et_confirmpassword);
        et_password = findViewById(R.id.et_password);
        btn_changepassword = findViewById(R.id.btn_changepassword);
        btn_changepassword.setOnClickListener(this);
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
        getViewModel().editpassword(password, confirmpassword, et_currentpassword.getText().toString());
    }

    @Override
    public void openMainActivity() {
        dismissDialog();
        CommonUtils.showToast("Password Changed Successfully");
        finish();

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
    public void invalidEmaim(String msg) {
        dismissDialog();
        input_email.setError(msg);
        input_email.requestFocus();

    }
}
