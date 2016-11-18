package com.fast.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/5.
 */
public class WeatherDaily {
    private JSONObject l;
    private JSONArray d;
    private String la;
    //地址
    private Location location;
    //天气信息
    private Daily daily;
    //更新日期
    private LastUpdate lastUpdate;

    /**
     * 构造
     * @param json jsonObject
     */
    public WeatherDaily(JSONObject json){
        try {
            l = json.getJSONObject("location");
            d = json.getJSONArray("daily");
            la = json.getString("last_update");
            location = new Location(l);
            daily = new Daily(d);
            lastUpdate = new LastUpdate(la);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取查询城市地址信息
     * @return Location
     */
    public Location getLocation(){
        return location;
    }

    /**
     * 获取查询城市天气信息
     * @return Daily
     */
    public Daily getDaily(){
        return daily;
    }
    /**
     * 获取最后更新时间
     * @return LastUpdate
     */
    public LastUpdate getLastUpdate(){
        return lastUpdate;
    }
    /**
     * 重写toString
     * @return String
     */
    @Override
    public String toString(){
        String str = "location:\n"+getLocation().toString()+
                "\ndaily:\n"+getDaily().toString()+
                "\nlastUpdate:\n"+getLastUpdate().toString();
        return str;
    }
}
