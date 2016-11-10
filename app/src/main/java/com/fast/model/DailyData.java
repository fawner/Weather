package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/6.
 */
public class DailyData {
    private JSONObject json;

    /**
     * 构造
     * @param json JSONObject
     */
    public DailyData(JSONObject json){
        this.json = json;
    }
    /***
     * 格式化获取数据
     * @param info json的key
     * @return String
     */
    private String GETINFO(String info){
        String str = null;
        try {
            str = json.getString(info);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取日期
     * @return String
     */
    public String getDate(){
        return GETINFO("date");
    }

    /**
     * 白天天气现象文字
     * @return String
     */
    public String getTextDay(){
        return GETINFO("text_day");
    }

    /**
     * 白天天气现象代码
     * @return String
     */
    public String getCodeDay(){
        return GETINFO("code_day");
    }

    /**
     * 晚间天气现象文字
     * @return String
     */
    public String getTextNight(){
        return GETINFO("text_night");
    }

    /**
     * 晚间天气现象代码
     * @return String
     */
    public String getCodeNight(){
        return GETINFO("code_Night");
    }

    /**
     * 当天最高温度
     * @return String
     */
    public String getHigh(){
        return GETINFO("high");
    }

    /**
     * 当天最低温度
     * @return String
     */
    public String getLow(){
        return GETINFO("low");
    }

    /**
     * 降水概率，范围0~100，单位百分比
     * @return String
     */
    public String getPrecip(){
        return GETINFO("precip");
    }

    /**
     * 风向文字
     * @return String
     */
    public String getWindDirection(){
        return GETINFO("wind_direction");
    }

    /**
     * 风向角度，范围0~360
     * @return String
     */
    public String getWindDirectionDegree(){
        return GETINFO("wind_direction_degree");
    }

    /**
     * 风速，单位km/h（当unit=c时）、mph
     * @return String
     */
    public String getWindSpeed(){
        return GETINFO("wind_speed");
    }

    /**
     * 风力等级
     * @return String
     */
    public String getWindScale(){
        return GETINFO("wind_scale");
    }
    /**
     * 重写toString
     * @return String
     */
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
