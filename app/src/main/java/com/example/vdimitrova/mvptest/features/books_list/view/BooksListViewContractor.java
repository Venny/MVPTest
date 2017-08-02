package com.example.vdimitrova.mvptest.features.books_list.view;

import com.example.vdimitrova.mvptest.database.model_tables.BookDbModel;

import java.util.List;

/**
 * Created by vdimitrova on 15.05.17.
 */

public interface BooksListViewContractor {
    void loadEvents(List<BookDbModel> books);

    void onRequestStarted();

    void onResponseReceived(List<BookDbModel> books);

    boolean isAppVisitedBefore();
}
