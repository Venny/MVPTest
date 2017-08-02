package com.example.vdimitrova.mvptest.api;

import com.example.vdimitrova.mvptest.api.api_models.response_models.ResponseModel;
import com.example.vdimitrova.mvptest.utils.Constants;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by vdimitrova on 22.05.17.
 */

public interface ApiInterface {
    @GET(Constants.ApiConstants.BOOKS_API_URL)
    Observable<ResponseModel> getBooks();
}
