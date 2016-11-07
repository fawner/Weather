package com.fast.model;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by 亲爱的~ on 2016/11/5.
 */
public class Daily {
    private DailyData dailyToday;
    private DailyData dailyTomorrow;
    private DailyData dailtAfterTomorrow;
    public Daily(JSONArray json){
        try {
            dailyToday = new DailyData(json.getJSONObject(0));
            dailyTomorrow = new DailyData(json.getJSONObject(1));
            dailtAfterTomorrow = new DailyData(json.getJSONObject(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public DailyData getDailyToday(){
        return dailyToday;
    }
    public DailyData getDailyTomorrow(){
        return dailyTomorrow;
    }
    public DailyData getDailtAfterTomorrow(){
        return dailtAfterTomorrow;
    }
    @Override
    public String toString(){
        String str = "Today:\n"+getDailyToday().toString()+
                "\nTomorrow:\n"+getDailyTomorrow().toString()+
                "\nAfterTomorrow:\n"+getDailtAfterTomorrow().toString();
        return str;
    }
}
