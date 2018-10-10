package com.andrinotech.studentapp.utils;

/**
 * Created by ZaidAs on 3/1/2018.
 */

public class Config {

    public final static String BASE_URL = "http://192.168.42.71/appserver/public/api/student/";

    public class Api {
        public final static String SignIn = BASE_URL + "login?email={email}&password={password}";
        public final static String REGISTER = BASE_URL + "register?name={name}&username={username}&email={email}&password={password}";
        public final static String EDIT_PROFIEL = BASE_URL + "edit-profile";

        public final static String GetAllPOSt = "http://192.168.42.71/appserver/public/api/post/get-all-posts";
        public final static String getAllPostsUstad = "http://192.168.42.71/appserver/public/api/post/getAllPostsUstad";
        public final static String ADD_LIKE = "http://192.168.42.71/appserver/public/api/post/like";
        public final static String UNLIKE_POST = "http://192.168.42.71/appserver/public/api/post/unlike";

        public final static String ADD_COMMENT = "http://192.168.42.71/appserver/public/api/post/postComment";
        public final static String GET_ALL_COMMENTS = "http://192.168.42.71/appserver/public/api/post/getPostComments";
        public final static String FORGOT_PASSWORD = BASE_URL + "send-code?email={email}";
        public final static String FORGOT_RESET_CODE = BASE_URL + "verify-code?email={email}&code={code}";
        public final static String CHANGE_PASSWORD = BASE_URL + "new-password?email={email}&password={password}";
        public final static String UPLOAD_IMAGE = BASE_URL + "upload-image";
    }


}
