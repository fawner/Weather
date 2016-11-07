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

    private Location location;
    private Now now;
    private LastUpdate lastUpdate;

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


    public Location getLocation(){
        return location;
    }
    public Now getNow(){
        return now;
    }
    public LastUpdate getLastUpdate(){
        return lastUpdate;
    }

    @Override
    public String toString(){
        String str = "location:\n"+getLocation().toString()+
                "\nnow:\n"+getNow().toString()+
                "\nlastUpdate:\n"+getLastUpdate().toString();
        return str;
    }
}
