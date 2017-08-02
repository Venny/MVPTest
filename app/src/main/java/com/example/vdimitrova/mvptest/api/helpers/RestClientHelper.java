package com.example.vdimitrova.mvptest.api.helpers;

import android.support.annotation.NonNull;

import com.example.vdimitrova.mvptest.api.ApiInterface;
import com.example.vdimitrova.mvptest.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vdimitrova on 28.06.17.
 * <p>
 * MVPTest
 * <p>
 * Base controller for Retrofit
 */

public class RestClientHelper {
    private static volatile RestClientHelper instance;
    private static ApiInterface apiInterface;
    private static Retrofit retrofit;

    private RestClientHelper() {
        //Prevent form the reflection api.
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static RestClientHelper getInstance() {
        // Double check locking method.
        if (instance == null) {

            synchronized (RestClientHelper.class) {
                //Thread safe. Check for the second time.
                //if there is no instance available... create new one
                if (instance == null) instance = new RestClientHelper();
            }

        }
        return instance;
    }

    public ApiInterface getClient() {
        if (apiInterface == null) {
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                    Request originalRequest = chain.request();

                    Request.Builder builder = originalRequest.newBuilder().headers(HeadersHelper.getAppHeaders());

                    Request newRequest = builder.build();
                    return chain.proceed(newRequest);
                }
            }).build();

            // create the retrofit client
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.ApiConstants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            apiInterface = retrofit.create(ApiInterface.class);
        }

        return apiInterface;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
