package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/5.
 */
public class Location {
    private JSONObject json = null;
    private String str = null;
    /***
     *构造 设置location数据
     * @param json
     */
    public Location(JSONObject json){
        this.json = json;
    }
    /***
     * 格式化获取数据
     * @param info
     * @return
     */
    private String GETINFO(String info){
        try {
            str = json.getString(info);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    /***
     * 获取location的id
     * @return
     */
    public String getLocationId(){
        return GETINFO("id");
    }

    /***
     * 获取location的name
     * @return
     */
    public String getLocationName(){
        return GETINFO("name");
    }

    /***
     * 获取location的country
     * @return
     */
    public String getLocationCountry(){
        return GETINFO("country");
    }

    /***
     * 获取location的path（如："北京,北京,中国"）
     * @return
     */
    public String getLocationPath(){
        return GETINFO("path");
    }

    /***
     * 获取location的timezone（如："Asia/Shanghai"）
     * @return
     */
    public String getLocationTimezone(){
        return GETINFO("timezone");
    }

    /***
     * 获取location的timezone_offset（如："+08:00"）
     * @return
     */
    public String getLocationTimezoneOffset(){
        return GETINFO("timezone_offset");
    }

    @Override
    public String toString(){
        String str = "id: "+getLocationId()+
                "\nname: "+getLocationName()+
                "\ncountry: "+getLocationCountry()+
                "\npath: "+getLocationPath()+
                "\ntimezome: "+getLocationTimezone()+
                "\ntimezone_offset: "+getLocationTimezoneOffset();
        return str;
    }
}
