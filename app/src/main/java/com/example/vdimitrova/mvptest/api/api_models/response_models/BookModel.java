package com.example.vdimitrova.mvptest.api.api_models.response_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vdimitrova on 23.05.17.
 */

public class BookModel {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("book_image")
    private String bookImageSrc;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookImageSrc() {
        return bookImageSrc;
    }

    public void setBookImageSrc(String bookImageSrc) {
        this.bookImageSrc = bookImageSrc;
    }
}
