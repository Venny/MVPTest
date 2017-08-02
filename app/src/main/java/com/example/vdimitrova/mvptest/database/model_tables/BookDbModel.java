package com.example.vdimitrova.mvptest.database.model_tables;

import com.example.vdimitrova.mvptest.database.DatabaseBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by vdimitrova on 15.05.17.
 */

@Table(database = DatabaseBase.class)
public class BookDbModel extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String author;
    @Column
    private String bookImageSrc;

    public int getId() {
        return id;
    }

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
