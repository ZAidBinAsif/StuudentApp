
package com.andrinotech.studentapp.ui.Post;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostModelResponse {
    @SerializedName("id")
    @Expose
    private String id;

    public Ustad getUstad() {
        return ustad;
    }

    public void setUstad(Ustad ustad) {
        this.ustad = ustad;
    }

    @SerializedName("ustad")
    @Expose
    private Ustad ustad;

    @SerializedName("myLikeStatus")
    @Expose
    private Like like;

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    public PostModelResponse(String title, String text, String type, String category, String userId, String time, Ustad ustad) {
        this.title = title;
        this.ustad = ustad;
        this.text = text;
        this.type = type;
        this.category = category;
        this.userId = userId;
        this.time = time;
    }

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("userId")
    @Expose
    private String userId;

    @SerializedName("time")
    @Expose
    private String time;

    public String getTotal_like() {
        return total_like;
    }

    public void setTotal_like(String total_like) {
        this.total_like = total_like;
    }

    public String getTotal_unlike() {
        return total_unlike;
    }

    public void setTotal_unlike(String total_unlike) {
        this.total_unlike = total_unlike;
    }

    @SerializedName("likes")
    @Expose
    private String total_like;
    @SerializedName("unlikes")
    @Expose
    private String total_unlike;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
