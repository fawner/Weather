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
    private Location location;
    private Suggestion suggestion;
    private LastUpdate lastUpdate;

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
    public Location getLocation() {
        return location;
    }
    public Suggestion getSuggestion() {
        return suggestion;
    }
    public LastUpdate getLastUpdate() {
        return lastUpdate;
    }
    @Override
    public String toString(){
        String str = "location:\n"+getLocation().toString()+
                "\nsuggestion:\n"+getSuggestion().toString()+
                "\nlastUpdate:\n"+getLastUpdate().toString();
        return str;
    }

}
