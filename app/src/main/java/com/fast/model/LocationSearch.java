package com.fast.model;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by 亲爱的~ on 2016/11/9.
 */
public class LocationSearch {
    private JSONArray jsonArray;
    //查询城市数组
    private Location[] locations;

    /**
     * 构造
     * @param jsonArray jsonArray
     */
    public LocationSearch(JSONArray jsonArray){
        this.jsonArray = jsonArray;
        locations = new Location[jsonArray.length()];
        setLocation();
    }

    /**
     * 根据json数据初始化数组
     */
    private void setLocation(){
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                locations[i] = new Location(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取查询的城市数据
     * @return location[]
     */
    public Location[] getLocations() {
        return locations;
    }
    /**
     * 重写toString
     * @return String
     */
    @Override
    public String toString(){
        String str = "";
        for (int i = 0; i < locations.length; i++) {
            str+= "" + (i+1) + ": \n" + locations[i].toString() + "\n";
        }
        return str;
    }
}
