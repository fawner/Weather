package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/6.
 */
public class DailyData {
    private JSONObject json;

    public DailyData(JSONObject json){
        this.json = json;
    }
    private String GETINFO(String info){
        String str = null;
        try {
            str = json.getString(info);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getDate(){
        return GETINFO("date");
    }
    public String getTextDay(){
        return GETINFO("text_day");
    }
    public String getCodeDay(){
        return GETINFO("code_day");
    }
    public String getTextNight(){
        return GETINFO("text_night");
    }
    public String getCodeNight(){
        return GETINFO("code_Night");
    }
    public String getHigh(){
        return GETINFO("high");
    }
    public String getLow(){
        return GETINFO("low");
    }
    public String getPrecip(){
        return GETINFO("precip");
    }
    public String getWindDirection(){
        return GETINFO("wind_direction");
    }
    public String getWindDirectionDegree(){
        return GETINFO("wind_direction_degree");
    }
    public String getWindSpeed(){
        return GETINFO("wind_speed");
    }
    public String getWindScale(){
        return GETINFO("wind_scale");
    }
    @Override
    public String toString(){
        String str = "date: "+getDate()+
                "\ntext_day: "+getTextDay()+
                "\ncode_day: "+getCodeDay()+
                "\ntext_night: "+getTextNight()+
                "\ncode_night: "+getCodeNight()+
                "\nhigh: "+getHigh()+
                "\nlow: "+getLow()+
                "\nprecip: "+getPrecip()+
                "\nwind_direction: "+getWindDirection()+
                "\nwind_direction_degree: "+getWindDirectionDegree()+
                "\nwind_speed: "+getWindSpeed()+
                "\nwind_scale: "+getWindScale();
        return str;
    }
}
