package com.example.vdimitrova.mvptest.api.api_models.response_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vdimitrova on 23.05.17.
 */

public class ResultModel {
    @SerializedName("lists")
    @Expose
    private List<ListModel> lists;

    public List<ListModel> getLists() {
        return lists;
    }

    public void setLists(List<ListModel> lists) {
        this.lists = lists;
    }
}
