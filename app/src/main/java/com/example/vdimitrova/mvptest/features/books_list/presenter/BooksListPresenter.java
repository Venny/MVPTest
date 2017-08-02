package com.example.vdimitrova.mvptest.features.books_list.presenter;

import android.content.Context;
import android.os.Handler;

import com.example.vdimitrova.mvptest.database.model_tables.BookDbModel;
import com.example.vdimitrova.mvptest.features.books_list.model.BooksListModel;
import com.example.vdimitrova.mvptest.features.books_list.view.BooksListViewContractor;

import java.util.List;

/**
 * Created by vdimitrova on 15.05.17.
 */

public class BooksListPresenter {
    private Context context;
    private BooksListViewContractor view;
    private BooksListModel interactor;

    public BooksListPresenter(Context context, BooksListViewContractor view) {
        this.context = context;
        this.view = view;
        this.interactor = new BooksListModel();
    }

    public void loadEvents() {
        // Creating a handler to delay the answer a couple of seconds.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<BookDbModel> events = interactor.getUpcomingEvents(context);
                view.onResponseReceived(events);
            }
        }, 200);
    }

    public void downloadBooksIfNeeded() {
        view.onRequestStarted();
        interactor.downloadBooks();
    }
}
