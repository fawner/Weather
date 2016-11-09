package com.fast.model;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by 亲爱的~ on 2016/11/9.
 */
public class LocationSearch {
    private JSONArray jsonArray;
    private Location[] locations;

    public LocationSearch(JSONArray jsonArray){
        this.jsonArray = jsonArray;
        locations = new Location[jsonArray.length()];
        setLocation();
    }
    private void setLocation(){
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                locations[i] = new Location(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public Location[] getLocations() {
        return locations;
    }
    @Override
    public String toString(){
        String str = "";
        for (int i = 0; i < locations.length; i++) {
            str+= "" + (i+1) + ": \n" + locations[i].toString() + "\n";
        }
        return str;
    }
}
