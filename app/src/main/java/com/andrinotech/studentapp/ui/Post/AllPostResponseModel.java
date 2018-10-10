
package com.andrinotech.studentapp.ui.Post;

import com.andrinotech.studentapp.helper.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllPostResponseModel {
    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @SerializedName("error")
    @Expose
    private Error error;


    @SerializedName("posts")
    @Expose
    private ArrayList<PostModelResponse>  postModel;

    public ArrayList<PostModelResponse> getPostModel() {
        return postModel;
    }

    public void setPostModel(ArrayList<PostModelResponse> postModel) {
        this.postModel = postModel;
    }
}
