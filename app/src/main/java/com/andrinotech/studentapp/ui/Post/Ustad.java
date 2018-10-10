
package com.andrinotech.studentapp.ui.Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ustad {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("firebaseid")
    @Expose
    private String firebaseid;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("status")
    @Expose
    private String status;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSkils() {
        return skils;
    }

    public void setSkils(String skils) {
        this.skils = skils;
    }

    @SerializedName("logo")
    @Expose
    private String logo;

    @SerializedName("active")
    @Expose
    private String active;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseid() {
        return firebaseid;
    }

    public void setFirebaseid(String firebaseid) {
        this.firebaseid = firebaseid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("phone")

    @Expose
    private String phone;

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("info")
    @Expose
    private String info;


    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("skils")
    @Expose
    private String skils;


    @SerializedName("password")
    @Expose
    private String password;
}
