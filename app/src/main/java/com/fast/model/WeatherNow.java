package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/5.
 */
public class WeatherNow {
    private JSONObject l;
    private JSONObject n;
    private String la;
    //地址
    private Location location;
    //当前天气信息
    private Now now;
    //更新日期
    private LastUpdate lastUpdate;

    /**
     * 构造
     * @param json jsonObject
     */
    public WeatherNow(JSONObject json){
        try {
            l = json.getJSONObject("location");
            n = json.getJSONObject("now");
            la = json.getString("last_update");
            location = new Location(l);
            now = new Now(n);
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
     * 获取查询城市当前天气信息
     * @return Now
     */
    public Now getNow(){
        return now;
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
                "\nnow:\n"+getNow().toString()+
                "\nlastUpdate:\n"+getLastUpdate().toString();
        return str;
    }
}
