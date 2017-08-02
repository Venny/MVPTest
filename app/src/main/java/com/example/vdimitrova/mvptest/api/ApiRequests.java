package com.example.vdimitrova.mvptest.api;

import android.content.Context;

import com.example.vdimitrova.mvptest.api.api_models.response_models.BookModel;
import com.example.vdimitrova.mvptest.api.api_models.response_models.ListModel;
import com.example.vdimitrova.mvptest.api.api_models.response_models.ResponseModel;
import com.example.vdimitrova.mvptest.api.helpers.RestClientHelper;
import com.example.vdimitrova.mvptest.api.helpers.ServiceHelper;
import com.example.vdimitrova.mvptest.base.model.custom_events.UpdateBooksEvent;
import com.example.vdimitrova.mvptest.database.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * Created by vdimitrova on 28.06.17.
 * <p>
 * MVPTest
 * <p>
 * All requests should be listed here.
 */

public class ApiRequests {
    public static void getBooks(final Context context) {
        ServiceHelper.getInstance().createAsynchronousRequest(
                RestClientHelper.getInstance().getClient().getBooks(),
                new Observer<ResponseModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseModel responseModel) {
                        //handle response
                        List<BookModel> books = getBooksFromResponse(responseModel);
                        DatabaseHelper.getInstance(context).addNewEvents(books);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //handle error
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        EventBus.getDefault().post(new UpdateBooksEvent(true));
                    }
                });
    }

    private static List<BookModel> getBooksFromResponse(ResponseModel response) {
        List<BookModel> books = new ArrayList<>();

        if (response.getNumResults() > 0) {
            List<ListModel> booksWrapperList = response.getResults().getLists();

            for (ListModel list : booksWrapperList) {
                books.addAll(list.getBooks());
            }
        }

        return books;
    }

}
