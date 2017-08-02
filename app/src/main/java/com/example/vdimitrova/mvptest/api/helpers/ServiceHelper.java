package com.example.vdimitrova.mvptest.api.helpers;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vdimitrova on 28.06.17.
 * <p>
 * MVPTest
 */

public class ServiceHelper {
    //  volatile --> the variable changes at runtime and that the compiler should not cache its value for any reason.
    private static volatile ServiceHelper instance;

    private ServiceHelper() {
        //Prevent form the reflection api.
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static synchronized ServiceHelper getInstance() {
        if (instance == null) {
            instance = new ServiceHelper();
        }
        return instance;
    }

    public <T> void createAsynchronousRequest(Observable<T> observable, Observer<T> observer) {

        // Create an Observable to get the API result
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
