package com.andrinotech.studentapp.network.interfaces;

import com.andrinotech.studentapp.ui.Post.AllPostResponseModel;
import com.andrinotech.studentapp.ui.Post.CommentResponseModel;
import com.andrinotech.studentapp.ui.Post.LikePostResponseModel;
import com.andrinotech.studentapp.ui.login.AuthTocken;
import com.andrinotech.studentapp.ui.ChangePassword.ChangePasswordApiModel;
import com.andrinotech.studentapp.ui.login.MetaData;
import com.andrinotech.studentapp.ui.forgotPassword.ForgotPasswordApiModel;
import com.andrinotech.studentapp.ui.profile.EditprofileApidModel;
import com.andrinotech.studentapp.ui.register.RegisterUserApidModel;

import io.reactivex.Single;

public interface IRequest {

//    public Single<String> SignOut();

    public Single<MetaData> getForgotPassword(ForgotPasswordApiModel forgotPasswordApiModel);
    public Single<MetaData> senResetCode(ForgotPasswordApiModel forgotPasswordApiModel);
    public Single<MetaData> changepassword(ChangePasswordApiModel changePasswordApiModel);

    Single<MetaData> editPassword(ChangePasswordApiModel changePasswordApiModel);

    //
    public Single<MetaData> getRegisterUser(RegisterUserApidModel registerUserApidModel);
    public Single<MetaData> Editprfofile(EditprofileApidModel registerUserApidModel);

    public Single<MetaData> signIn(AuthTocken authTocken);


    Single<LikePostResponseModel> addlike(int id);

    Single<LikePostResponseModel> posUnlike(int id);


    Single<AllPostResponseModel> getAllPosts();

    Single<AllPostResponseModel> getPostOfUstad(int id);

    Single<CommentResponseModel> getAllPostComment(int id);

    Single<CommentResponseModel> addComment(int id, String text);
}
