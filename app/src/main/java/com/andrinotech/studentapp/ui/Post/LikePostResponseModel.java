
package com.andrinotech.studentapp.ui.Post;

import com.andrinotech.studentapp.helper.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikePostResponseModel {
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
    private PostModelResponse postModel;

    public PostModelResponse getPostModel() {
        return postModel;
    }

    public void setPostModel(PostModelResponse postModel) {
        this.postModel = postModel;
    }
}
