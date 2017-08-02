package com.example.vdimitrova.mvptest.database;

import com.raizlabs.android.dbflow.annotation.Database;
/**
 * Created by vdimitrova on 15.05.17.
 */

@Database(name = DatabaseBase.NAME, version = DatabaseBase.VERSION)
public class DatabaseBase {
    public static final String NAME = "booksDb";
    public static final int VERSION = 1;
}
