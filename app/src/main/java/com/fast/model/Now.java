package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/5.
 */
public class Now {
    private JSONObject json;

    /***
     * 构造 设置now数据
     * @param json
     */
    public Now(JSONObject json){
        this.json = json;
    }
    /***
     * 格式化获取数据
     * @param info
     * @return
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

    /***
     * 获取now的text（天气）
     * @return
     */
    public String getWeatherNowText(){
        return GETINFO("text");
    }

    /***
     * 获取now的code（天气代码）
     * @return
     */
    public String getWeatherNowCode(){
        return GETINFO("code");
    }

    /***
     * 获取now的temperature（温度）
     * @return
     */
    public String getWeatherNowTemperature(){
        return GETINFO("temperature");
    }
    @Override
    public String toString(){
        String str = "text: "+getWeatherNowText()+
                "\ncode :"+getWeatherNowCode()+
                "\ntemperature: "+getWeatherNowTemperature();
        return str;
    }
}
