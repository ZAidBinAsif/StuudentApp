package com.andrinotech.studentapp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.andrinotech.studentapp.data.PreferencesManager;
import com.andrinotech.studentapp.data.UserManager;

public class StudentApp extends Application {

    public static PreferencesManager preferencesManager;
    static StudentApp instance;

    public SharedPreferences getPrefs() {
        return prefs;
    }

    private SharedPreferences prefs;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        preferencesManager.initializeInstance(this, PreferencesManager.PREF_NAME);
        preferencesManager = PreferencesManager.getInstance();
        prefs = getSharedPreferences(UserManager.PREFS_NAME, Context.MODE_PRIVATE);

    }

    public static StudentApp getInstance() {
        return instance;
    }

    public static PreferencesManager getPreferencesManager() {
        return preferencesManager;
    }


}
