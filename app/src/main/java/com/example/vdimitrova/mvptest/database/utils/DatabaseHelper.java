package com.example.vdimitrova.mvptest.database.utils;

import android.content.Context;

import com.example.vdimitrova.mvptest.api.api_models.response_models.BookModel;
import com.example.vdimitrova.mvptest.database.model_tables.BookDbModel;
import com.example.vdimitrova.mvptest.database.model_tables.BookDbModel_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by vdimitrova on 15.05.17.
 */


public class DatabaseHelper {

    private static DatabaseHelper instance;
    private Context context;

    private DatabaseHelper(Context context) {
        this.context = context;
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

    /**
     * Add a new book
     *
     * @param title
     * @param description
     * @param author
     * @param imageOriginal
     */
    public void addNewEvent(String title, String description, String author, String imageOriginal) {
        BookDbModel newBook = new BookDbModel();
        newBook.setTitle(title);
        newBook.setDescription(description);
        newBook.setAuthor(author);
        newBook.setBookImageSrc(imageOriginal);
        newBook.save();
    }

    /**
     * Add a bundle of books
     *
     * @param books
     */
    public void addNewEvents(List<BookModel> books) {
        for (BookModel book : books) {
            addNewEvent(book.getTitle(), book.getDescription(), book.getAuthor(), book.getBookImageSrc());
        }
    }

    public List<BookDbModel> getAllBooks() {
        return SQLite.select().from(BookDbModel.class).queryList();
    }

    public BookDbModel getBook(int bookId) {
        return SQLite.select().from(BookDbModel.class)
                .where(BookDbModel_Table.id.eq(bookId)).querySingle();
    }
}
