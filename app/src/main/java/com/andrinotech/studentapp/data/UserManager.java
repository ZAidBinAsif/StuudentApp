package com.andrinotech.studentapp.data;

import android.content.SharedPreferences;

import com.andrinotech.studentapp.StudentApp;
import com.andrinotech.studentapp.ui.login.MetaData;
import com.andrinotech.studentapp.utils.StringUtils;


/**
 * Created by Zaid on 01/03/2018.
 */

public class UserManager {
    private static UserManager instance;
    public static final String PREFS_NAME = "app_prefs";
    public static final String PREFS_USER = "prefs_user";
    public static final String PREFS_METADATA = "metadtaa";
    public static final String PREFS_VIDEOTIME = "vidtime";

    public static final String PREFS_AUTHTOKEN = "authtoken";
    public static final int MaxAtemptstotal = 5;
    public static final int MaxChangePintotal = 3;
    public static final String MaxAtempts = "maxAtempts";

    public static final String MaxChangePinAtempts = "maxAChangePintempts";
    public static final String SigninBlockTime = "signinBlockTime";
    public static final String CHANGEPINTIME = "changepinTime";


    private static final String PREF_KEY_USER_LOGGED_IN = "PREF_KEY_USER_LOGGED_IN";
    private static final String PREF_KEY_USER_REMEBER = "PREF_KEY_USER_REMEBER";

    public static boolean firstTime = false;
    public MetaData metaData = null;

    private UserManager() {

    }

    public static UserManager getInstance() {
        if (instance == null)
            instance = new UserManager();

        return instance;
    }

    public void setUserLogin() {
        SharedPreferences sharedPreferences = StudentApp.getInstance().getPrefs();
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(PREFS_USER, StringUtils.getGson().toJson(user));
        editor.putBoolean(PREF_KEY_USER_LOGGED_IN, true);

        editor.commit();
        //     this.user = user;
    }


    public boolean checkUserIfLoggedIn() {
        SharedPreferences sharedPreferences = StudentApp.getInstance().getPrefs();
        boolean userLoggedIn = sharedPreferences.getBoolean(PREF_KEY_USER_LOGGED_IN, false);
        return userLoggedIn;
    }


    public void setMetaData(MetaData metaData) {
        SharedPreferences sharedPreferences = StudentApp.getInstance().getPrefs();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFS_METADATA, StringUtils.getGson().toJson(metaData));

        editor.commit();
    }

    public MetaData getMetaData() {
        SharedPreferences sharedPreferences = StudentApp.getInstance().getPrefs();
        String jsonUser = sharedPreferences.getString(PREFS_METADATA, null);
        if (!StringUtils.isNullOrEmpty(jsonUser)) {
            this.metaData = StringUtils.getGson().fromJson(jsonUser, MetaData.class);
            return metaData;
        }
        return null;
    }

    public void logoutCurrentUser() {
        SharedPreferences sharedPreferences = StudentApp.getInstance().getPrefs();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PREF_KEY_USER_LOGGED_IN);
        editor.remove(PREFS_METADATA);

        editor.commit();
        editor.clear();

//        this.user = null;
    }


}
