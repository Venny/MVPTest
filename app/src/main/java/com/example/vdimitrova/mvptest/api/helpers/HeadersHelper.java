package com.example.vdimitrova.mvptest.api.helpers;

import android.util.Log;

import com.example.vdimitrova.mvptest.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.Headers;

import static android.content.ContentValues.TAG;

/**
 * Created by vdimitrova on 23.05.17.
 */

public class HeadersHelper {
    private static final String CONTENT_TYPE_NAME_HEADER = "Content-Type";
    private static final String CONTENT_TYPE_JSON_VALUE_HEADER = "application/json";
    private static final String API_KEY_NAME_HEADER = "api-key";
    private static final String API_KEY_VALUE_HEADER = BuildConfig.apiKey;
    private static final String PUBLISHED_DATE_NAME_HEADER = "published_date";
    private static final String PUBLISHED_DATE_VALUE_HEADER = getCurrentDate();  // Current date.

    /**
     * Method for creating all the headers used in the app
     *
     * @return
     */
    public static Headers getAppHeaders() {
        HashMap<String, String> headersMap = new HashMap<>();

        //headersMap.put(CONTENT_TYPE_NAME_HEADER, CONTENT_TYPE_JSON_VALUE_HEADER);
        headersMap.put(API_KEY_NAME_HEADER, API_KEY_VALUE_HEADER);
        headersMap.put(PUBLISHED_DATE_NAME_HEADER, PUBLISHED_DATE_VALUE_HEADER);

        return Headers.of(headersMap);
    }

    private static String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Log.d(TAG, "getCurrentDate: " + newDateFormat.format(currentDate));
        return newDateFormat.format(currentDate);
    }

}
