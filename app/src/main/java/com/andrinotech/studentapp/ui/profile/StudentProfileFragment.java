package com.andrinotech.studentapp.ui.profile;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.data.UserManager;
import com.andrinotech.studentapp.ui.EditPassword.EditPasswordActvitiy;
import com.andrinotech.studentapp.ui.login.User;
import com.andrinotech.studentapp.utils.GlideHelper;


@SuppressLint("ValidFragment")
public class StudentProfileFragment extends Fragment implements View.OnClickListener {

    TextView name, username, phone, email, birthday, address, changepasword, editprofile;
    ImageView imageView_logo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_screen, container, false);
        imageView_logo = view.findViewById(R.id.imageView_logo);
        name = view.findViewById(R.id.name);
        username = view.findViewById(R.id.username);
        phone = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.email);
        birthday = view.findViewById(R.id.birthday);
        address = view.findViewById(R.id.address);
        changepasword = view.findViewById(R.id.changepasword);
        editprofile = view.findViewById(R.id.editprofile);
        editprofile.setOnClickListener(this);
        changepasword.setOnClickListener(this);
        setData();
        return view;
    }

    private void setData() {
        User user = UserManager.getInstance().getMetaData().getUser();
        name.setText(user.getName());
        username.setText(user.getUsername());
        phone.setText(user.getPhone());
        email.setText(user.getEmail());
        birthday.setText(user.getBirthday() == null ? " " : user.getBirthday());
        address.setText(user.getAddress() == null ? " " : user.getAddress());
        String path = user.getLogo() == null ? "" : user.getLogo();
        GlideHelper.loadImage(getContext(), path, imageView_logo, R.drawable.ic_profile_plc);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editprofile:
                startActivity(new Intent(getActivity(), StudentProfileEdit.class));
                break;
            case R.id.changepasword:
                Intent intent = new Intent(getActivity(), EditPasswordActvitiy.class);
                intent.putExtra("fromlogin", "false");
                startActivity(intent);
                break;


            default:
                break;
        }

    }
}
