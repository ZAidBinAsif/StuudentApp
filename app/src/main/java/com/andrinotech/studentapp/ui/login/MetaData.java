
package com.andrinotech.studentapp.ui.login;

import com.andrinotech.studentapp.helper.Error;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData {
    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @SerializedName("error")
    @Expose
    private Error error;


    @SerializedName("user")
    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
