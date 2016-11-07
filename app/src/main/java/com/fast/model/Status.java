package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/7.
 */
public class Status {
    private JSONObject json;
    private String status;
    private String status_code;
    public Status(JSONObject json){
        this.json = json;
        try {
            status = json.getString("status");
            status_code = json.getString("status_code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String getStatus() {
        return status;
    }
    public String getStatus_code() {
        return status_code;
    }
}
