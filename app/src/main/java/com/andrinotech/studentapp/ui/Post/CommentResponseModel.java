
package com.andrinotech.studentapp.ui.Post;

import com.andrinotech.studentapp.helper.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommentResponseModel {
    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @SerializedName("error")
    @Expose
    private Error error;


    @SerializedName("comments")
    @Expose
    private  ArrayList<Comment>   comments;

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
