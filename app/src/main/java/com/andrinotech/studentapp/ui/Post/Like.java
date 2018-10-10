package com.andrinotech.studentapp.ui.Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Like {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("postId")
    @Expose
    private String postId;


    @SerializedName("type")
    @Expose
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }


}
