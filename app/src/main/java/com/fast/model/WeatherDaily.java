package com.fast.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/5.
 */
public class WeatherDaily {
    private JSONObject l;
    public JSONArray d;
    private String la;

    private Location location;
    private Daily daily;
    private LastUpdate lastUpdate;

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

    public Location getLocation(){
        return location;
    }
    public Daily getDaily(){
        return daily;
    }
    public LastUpdate getLastUpdate(){
        return lastUpdate;
    }

    @Override
    public String toString(){
        String str = "location:\n"+getLocation().toString()+
                "\ndaily:\n"+getDaily().toString()+
                "\nlastUpdate:\n"+getLastUpdate().toString();
        return str;
    }
}
