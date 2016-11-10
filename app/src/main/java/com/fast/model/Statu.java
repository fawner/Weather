package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/7.
 */
public class Statu {
    private JSONObject json;
    public Statu(JSONObject json){
        this.json = json;
    }
    /**
     * 格式化获取数据
     * @param info json的key
     * @return String
     */
    private String GETINFO(String info){
        String str = null;
        try {
            str = json.getString(info);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取错误信息
     * @return String
     */
    public String getStatus() {
        return GETINFO("status");
    }

    /**
     * 获取作物信息代码
     * @return String
     */
    public String getStatus_code() {
        return GETINFO("status_code");
    }
    /**
     * 重写toStrinig
     * @return String
     */
    @Override
    public String toString(){
        String str = "status: "+getStatus()+
                "\nstatus_code: "+getStatus_code();
        return str;
    }
}
