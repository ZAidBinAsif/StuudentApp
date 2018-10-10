package com.andrinotech.studentapp.utils;

import android.util.Log;

/**
 * Created by ZaidAs on 2/28/2018.
 */

public class Logger {

    private static final String TAG_APP_INFO = "Cinemo_Info";
    private static final String TAG_APP_ERROR = "Cinemo_Errors";
    private static final String TAG_APP_DEBUG = "Cinemo_debug";

    private Logger() {
    }

    public static void info(String msg) {
        Log.i(TAG_APP_INFO, msg);
    }

    public static void debug(String msg) {
        Log.d(TAG_APP_DEBUG, msg);
    }

    public static void error(String msg) {
        Log.e(TAG_APP_ERROR, msg);
    }

    public static void info(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void debug(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void error(String tag, String msg) {
        Log.e(tag, msg);
    }

}
