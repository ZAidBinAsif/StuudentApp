package com.andrinotech.studentapp.ui.profile;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.data.UserManager;
import com.andrinotech.studentapp.helper.AVProgressDialog;
import com.andrinotech.studentapp.ui.base.BaseActivity;
import com.andrinotech.studentapp.ui.login.LoginActivity;
import com.andrinotech.studentapp.ui.login.User;
import com.andrinotech.studentapp.ui.register.RegisterViewModel;
import com.andrinotech.studentapp.utils.CommonUtils;
import com.andrinotech.studentapp.utils.GlideHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StudentProfileEdit extends BaseActivity<ProfileEditViewModel> implements ProfileEditCallBack, View.OnClickListener {
    private EditText et_fullname, et_username, et_phone, email, address;
    TextView changephoto,birthday;
    ImageView imageView_logo, cancel, confirm;

    private AVProgressDialog mLoadingDialog;
    Calendar c;
    int year, month, day;
    private Bitmap bitmap;
    private Uri inputImageUri1;
    private File imageFile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setCallBack(this);
        initViews();

    }

    @Override
    public int getLayout() {
        return R.layout.edit_profile_screen;

    }


    @Override
    public ProfileEditViewModel initViewModel() {
        return new ProfileEditViewModel();
    }

    public void initViews() {

        et_fullname = findViewById(R.id.et_fullname);
        et_username = findViewById(R.id.et_username);
        et_phone = findViewById(R.id.et_phone);
        email = findViewById(R.id.email);
        birthday = findViewById(R.id.birthday);
        imageView_logo = findViewById(R.id.imageView_logo);
        changephoto = findViewById(R.id.changephoto);
        cancel = findViewById(R.id.cancel);
        confirm = findViewById(R.id.confirm);
        address = findViewById(R.id.address);
        imageView_logo.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
        changephoto.setOnClickListener(this);

        mLoadingDialog = new AVProgressDialog(this);
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dd = new DatePickerDialog(StudentProfileEdit.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                try                     //0000-00-00
                                {
                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    //  String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                    String dateInString = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                    Date date = formatter.parse(dateInString);
                                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                                    birthday.setText(/*todate.getText().toString() + "\n" +*/ formatter.format(date).toString());
                                    birthday.setText(birthday.getText().toString() /*+ "\n" + formatter.format(date).toString()*/);
                                    // dates = Edate.getText().toString();

                                } catch (Exception ex) {
                                }
                            }
                        }, year, month, day);
                dd.show();
            }
        });
        setData();
    }

    private void setData() {
        User user = UserManager.getInstance().getMetaData().getUser();
        et_fullname.setText(user.getName());
        et_username.setText(user.getUsername());
        et_phone.setText(user.getPhone());
        email.setText(user.getEmail());
        birthday.setText(user.getBirthday() == null ? " " : user.getBirthday());
        address.setText(user.getAddress() == null ? " " : user.getAddress());
        String path = user.getLogo() == null ? "" : user.getLogo();
        GlideHelper.loadImage(this, path, imageView_logo, R.drawable.ic_profile_plc);

    }

    @Override
    public void getImageFromIntent(Uri uri, File p) {
        inputImageUri1 = uri;
        imageFile = p;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), inputImageUri1);
//            imageView_logo.setImageBitmap(bitmap);
            Glide.with(this)
                    .load(uri).dontAnimate()
                    .fitCenter()
                    .error(R.drawable.ic_profile_plc)
                    .placeholder(R.drawable.ic_profile_plc)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView_logo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                register();
                break;
            case R.id.changephoto:
                getViewModel().askForDangerousPermissions(this);
                break;

            case R.id.cancel:
                finish();
                break;
            default:
                break;
        }
    }


    private void dismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }


    private void register() {
        mLoadingDialog.show();
        String name = et_fullname.getText().toString();
        String username = et_username.getText().toString();
        String et_phonetext = et_phone.getText().toString();
        String emailtext = email.getText().toString();
        String birthdaytext = birthday.getText().toString();
        String addresstext = address.getText().toString();
        if (imageFile != null) {
            getViewModel().editUser(imageFile, name, username, et_phonetext, emailtext, birthdaytext, addresstext);

        } else {
            getViewModel().editUser(imageFile, name, username, et_phonetext, emailtext, birthdaytext, addresstext);

        }

    }

    @Override
    public void ValidationError(RegisterViewModel.validationEnum validationEnum) {
        if (validationEnum.equals(RegisterViewModel.validationEnum.Name)) {
            et_fullname.setError(" Name Required.");
        }
        if (validationEnum.equals(RegisterViewModel.validationEnum.UserNAme)) {
            et_username.setError("UserName Required.");
        }
        if (validationEnum.equals(RegisterViewModel.validationEnum.EMAIL)) {
            email.setError("Email Required.");
        }
        if (validationEnum.equals(RegisterViewModel.validationEnum.WRONG_EMAIL)) {
            email.setError("Invalid email format.");
        }

        dismissDialog();
    }

    @Override
    public void SuccessFullyUpdated() {
        dismissDialog();

    }

    @Override
    public void ErrorOnEdit(String str) {
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
        Intent login = new Intent(StudentProfileEdit.this, LoginActivity.class);
        startActivity(login);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getViewModel().onRequestPermissionsResult(StudentProfileEdit.this, requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getViewModel().onActivityResult(this, requestCode, resultCode, data);
    }

}
