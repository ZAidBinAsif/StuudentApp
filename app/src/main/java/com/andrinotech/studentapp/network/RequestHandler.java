package com.andrinotech.studentapp.network;


import com.andrinotech.studentapp.data.UserManager;
import com.andrinotech.studentapp.network.interfaces.IRequest;
import com.andrinotech.studentapp.ui.ChangePassword.ChangePasswordApiModel;
import com.andrinotech.studentapp.ui.Post.AllPostResponseModel;
import com.andrinotech.studentapp.ui.Post.CommentResponseModel;
import com.andrinotech.studentapp.ui.Post.LikePostResponseModel;
import com.andrinotech.studentapp.ui.forgotPassword.ForgotPasswordApiModel;
import com.andrinotech.studentapp.ui.login.AuthTocken;
import com.andrinotech.studentapp.ui.login.MetaData;
import com.andrinotech.studentapp.ui.profile.EditprofileApidModel;
import com.andrinotech.studentapp.ui.register.RegisterUserApidModel;
import com.andrinotech.studentapp.utils.Config;
import com.rx2androidnetworking.Rx2ANRequest;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Single;

public class RequestHandler implements IRequest {
    @Override
    public Single<MetaData> signIn(AuthTocken authTocken) {
        return Rx2AndroidNetworking.post(Config.Api.SignIn)
                .addBodyParameter("email", authTocken.getEmail())
                .addBodyParameter("password", authTocken.getPass())
                .build()
                .getObjectSingle(MetaData.class);
    }


    @Override
    public Single<MetaData> getForgotPassword(ForgotPasswordApiModel forgotPasswordApiModel) {
        return Rx2AndroidNetworking.post(Config.Api.FORGOT_PASSWORD)
                .addBodyParameter("email", forgotPasswordApiModel.getEmail())
                .build()
                .getObjectSingle(MetaData.class);
    }

    @Override
    public Single<MetaData> senResetCode(ForgotPasswordApiModel forgotPasswordApiModel) {
        return Rx2AndroidNetworking.post(Config.Api.FORGOT_RESET_CODE)
                .addBodyParameter("email", forgotPasswordApiModel.getEmail())
                .addBodyParameter("code", forgotPasswordApiModel.getCode())
                .build()
                .getObjectSingle(MetaData.class);
    }

    @Override
    public Single<MetaData> changepassword(ChangePasswordApiModel changePasswordApiModel) {
        return Rx2AndroidNetworking.post(Config.Api.CHANGE_PASSWORD)
                .addBodyParameter("email", changePasswordApiModel.getEmail())
                .addBodyParameter("password", changePasswordApiModel.getPassword())
                .build()
                .getObjectSingle(MetaData.class);
    }

    @Override
    public Single<MetaData> editPassword(ChangePasswordApiModel changePasswordApiModel) {
        return Rx2AndroidNetworking.post(Config.Api.CHANGE_PASSWORD)
                .addBodyParameter("email", changePasswordApiModel.getEmail())
                .addBodyParameter("password", changePasswordApiModel.getPassword())
                .build()
                .getObjectSingle(MetaData.class);
    }

    @Override
    public Single<MetaData> getRegisterUser(RegisterUserApidModel registerUserApidModel) {

        return Rx2AndroidNetworking.post(Config.Api.REGISTER)
                .addBodyParameter("name", registerUserApidModel.getName())
                .addBodyParameter("username", registerUserApidModel.getUsername())
                .addBodyParameter("email", registerUserApidModel.getEmail())
                .addBodyParameter("password", registerUserApidModel.getPassword())
                .build()
                .getObjectSingle(MetaData.class);

    }

    @Override
    public Single<MetaData> Editprfofile(EditprofileApidModel registerUserApidModel) {
        Rx2ANRequest.PostRequestBuilder postRequestBuilder = new Rx2ANRequest.PostRequestBuilder(Config.Api.EDIT_PROFIEL);
        if (!registerUserApidModel.getEmail().equalsIgnoreCase(UserManager.getInstance().getMetaData().getUser().getEmail())) {
            postRequestBuilder.addBodyParameter("newEmail", registerUserApidModel.getEmail());
        }
        if (!registerUserApidModel.getUsername().equalsIgnoreCase(UserManager.getInstance().getMetaData().getUser().getUsername())) {
            postRequestBuilder.addBodyParameter("newUsername", registerUserApidModel.getUsername());
        }

        postRequestBuilder.addBodyParameter("name", registerUserApidModel.getName());
        postRequestBuilder.addBodyParameter("username", registerUserApidModel.getUsername());
        postRequestBuilder.addBodyParameter("email", registerUserApidModel.getEmail());
        postRequestBuilder.addBodyParameter("phone", registerUserApidModel.getPhone());
        postRequestBuilder.addBodyParameter("birthday", registerUserApidModel.getBirthday());
        postRequestBuilder.addBodyParameter("address", registerUserApidModel.getAddress());

        return postRequestBuilder.build().getObjectSingle(MetaData.class);
//        return Rx2AndroidNetworking.post(Config.Api.EDIT_PROFIEL)
//                .addBodyParameter("name", registerUserApidModel.getName())
//                .addBodyParameter("username", registerUserApidModel.getUsername())
//                .addBodyParameter("email", registerUserApidModel.getEmail())
//
//                .build()
//                .getObjectSingle(MetaData.class);
    }

    @Override
    public Single<LikePostResponseModel> addlike(int id) {
        Rx2ANRequest.PostRequestBuilder postRequestBuilder = new Rx2ANRequest.PostRequestBuilder(Config.Api.ADD_LIKE);
        postRequestBuilder.addBodyParameter("postId", String.valueOf(id));
        postRequestBuilder.addBodyParameter("userId", UserManager.getInstance().getMetaData().getUser().getId());
        postRequestBuilder.addBodyParameter("userType", "student");
        return postRequestBuilder.build().getObjectSingle(LikePostResponseModel.class);
    }

    @Override
    public Single<LikePostResponseModel> posUnlike(int id) {
        Rx2ANRequest.PostRequestBuilder postRequestBuilder = new Rx2ANRequest.PostRequestBuilder(Config.Api.UNLIKE_POST);
        postRequestBuilder.addBodyParameter("postId", String.valueOf(id));
        postRequestBuilder.addBodyParameter("userId", UserManager.getInstance().getMetaData().getUser().getId());
        postRequestBuilder.addBodyParameter("userType", "student");
        return postRequestBuilder.build().getObjectSingle(LikePostResponseModel.class);
    }


    @Override
    public Single<AllPostResponseModel> getAllPosts() {

        Rx2ANRequest.PostRequestBuilder postRequestBuilder = new Rx2ANRequest.PostRequestBuilder(Config.Api.GetAllPOSt);
        postRequestBuilder.addBodyParameter("userId", UserManager.getInstance().getMetaData().getUser().getId());
        postRequestBuilder.addBodyParameter("userType", "student");

        return postRequestBuilder.build().getObjectSingle(AllPostResponseModel.class);

    }

    @Override
    public Single<AllPostResponseModel> getPostOfUstad(int id) {

        Rx2ANRequest.PostRequestBuilder postRequestBuilder = new Rx2ANRequest.PostRequestBuilder(Config.Api.getAllPostsUstad);
        postRequestBuilder.addBodyParameter("userId", UserManager.getInstance().getMetaData().getUser().getId());
        postRequestBuilder.addBodyParameter("userType", "student");
        postRequestBuilder.addBodyParameter("ustadId", String.valueOf(id));

        return postRequestBuilder.build().getObjectSingle(AllPostResponseModel.class);

    }

    @Override
    public Single<CommentResponseModel> getAllPostComment(int id) {

        Rx2ANRequest.PostRequestBuilder postRequestBuilder = new Rx2ANRequest.PostRequestBuilder(Config.Api.GET_ALL_COMMENTS);
        postRequestBuilder.addBodyParameter("postId", String.valueOf(id));
        return postRequestBuilder.build().getObjectSingle(CommentResponseModel.class);

    }

    @Override
    public Single<CommentResponseModel> addComment(int id, String text) {
        Rx2ANRequest.PostRequestBuilder postRequestBuilder = new Rx2ANRequest.PostRequestBuilder(Config.Api.ADD_COMMENT);
        postRequestBuilder.addBodyParameter("postId", String.valueOf(id));
        postRequestBuilder.addBodyParameter("userId", UserManager.getInstance().getMetaData().getUser().getId());
        postRequestBuilder.addBodyParameter("userType", "student");
        postRequestBuilder.addBodyParameter("text", text);
        return postRequestBuilder.build().getObjectSingle(CommentResponseModel.class);
    }
}
