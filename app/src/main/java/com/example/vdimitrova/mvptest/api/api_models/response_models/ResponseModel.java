package com.example.vdimitrova.mvptest.api.api_models.response_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by vdimitrova on 23.05.17.
 */

public class ResponseModel {
    @SerializedName("results")
    @Expose
    private ResultModel results;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("copyright")
    @Expose
    private String copyright;

    @SerializedName("num_results")
    @Expose
    private int numResults;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public ResultModel getResults() {
        return results;
    }

    public void setResults(ResultModel results) {
        this.results = results;
    }
}
