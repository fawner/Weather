package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/6.
 */
public class SuggestionTerm {
    private JSONObject json;

    /**
     * 构造
     * @param json jsonObject
     */
    public SuggestionTerm(JSONObject json){
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
     * 获取简单建议
     * @return String
     */
    public String getBrief(){
        return GETINFO("brief");
    }

    /**
     * 获取详细建议
     * @return String
     */
    public String getDetails(){
        return GETINFO("details");
    }
    /**
     * 重写toStrinig
     * @return String
     */
    @Override
    public String toString(){
        String str = "\nbrief: "+getBrief()+
                "\ndetails: "+getDetails()+"\n";
        return str;
    }
}
