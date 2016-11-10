package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/6.
 */
public class LifeSuggestion {
    private JSONObject l;
    private JSONObject su;
    private String la;
    //地址
    private Location location;
    //建议
    private Suggestion suggestion;
    //更新日期
    private LastUpdate lastUpdate;

    /**
     * 构造
     * @param json jsonObject
     */
    public LifeSuggestion(JSONObject json){
        try {
            l = json.getJSONObject("location");
            su= json.getJSONObject("suggestion");
            la = json.getString("last_update");
            location = new Location(l);
            suggestion = new Suggestion(su);
            lastUpdate = new LastUpdate(la);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取查询城市地址信息
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * 获取建议
     * @return Suggestion
     */
    public Suggestion getSuggestion() {
        return suggestion;
    }

    /**
     * 获取最后更新时间
     * @return LastUpdate
     */
    public LastUpdate getLastUpdate() {
        return lastUpdate;
    }

    /**
     * 重写toString
     * @return String
     */
    @Override
    public String toString(){
        String str = "location:\n"+getLocation().toString()+
                "\nsuggestion:\n"+getSuggestion().toString()+
                "\nlastUpdate:\n"+getLastUpdate().toString();
        return str;
    }

}
