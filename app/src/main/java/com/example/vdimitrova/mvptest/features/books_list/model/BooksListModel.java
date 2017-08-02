package com.example.vdimitrova.mvptest.features.books_list.model;

import android.content.Context;

import com.example.vdimitrova.mvptest.api.ApiRequests;
import com.example.vdimitrova.mvptest.database.model_tables.BookDbModel;
import com.example.vdimitrova.mvptest.database.utils.DatabaseHelper;

import java.util.List;

/**
 * Created by vdimitrova on 18.05.17.
 * <p>
 * MVPTest
 */

public class BooksListModel {
    private Context context;

    public List<BookDbModel> getUpcomingEvents(Context context) {
        this.context = context;

        return DatabaseHelper.getInstance(context).getAllBooks();
    }

    public void downloadBooks() {
        ApiRequests.getBooks(context);
    }
}
