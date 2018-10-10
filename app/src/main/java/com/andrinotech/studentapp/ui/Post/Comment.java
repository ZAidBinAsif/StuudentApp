package com.andrinotech.studentapp.ui.Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("postId")
    @Expose
    private String postId;

    @SerializedName("ustad")
    @Expose
    private Ustad ustad;

    public Ustad getUstad() {
        return ustad;
    }

    public void setUstad(Ustad ustad) {
        this.ustad = ustad;
    }

    @SerializedName("time")
    @Expose
    private long time;

    @SerializedName("text")
    @Expose
    private String text;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
