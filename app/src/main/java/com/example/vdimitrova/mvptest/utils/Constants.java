package com.example.vdimitrova.mvptest.utils;

import com.example.vdimitrova.mvptest.BuildConfig;

/**
 * Created by vdimitrova on 18.05.17.
 */

public class Constants {
    public static final String APP_FIRST_VISITED = "app_first_visited";
    public static final String KEY_POSITION = "key_position";


    public static class ApiConstants {
        // Base URL must end with /, because of Retrofit.
        public static final String BASE_URL = BuildConfig.baseUrl + "svc/books/v3/";

        public static final String BOOKS_API_URL = BASE_URL + "lists/overview.json";
    }
}
