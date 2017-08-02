package com.example.vdimitrova.mvptest.api.api_models.response_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vdimitrova on 23.05.17.
 */

public class ListModel {
    @SerializedName("list_name")
    @Expose
    private String name;

    @SerializedName("books")
    @Expose
    private List<BookModel> books;

    @SerializedName("list_id")
    @Expose
    private int listId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public List<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
    }
}
