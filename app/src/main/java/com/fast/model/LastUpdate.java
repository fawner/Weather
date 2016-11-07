package com.fast.model;

/**
 * Created by 亲爱的~ on 2016/11/5.
 */
public class LastUpdate {
    private String time;

    /***
     * 构造 设置time数据
     * @param str
     */
    public LastUpdate(String str){
        time = str;
    }
    public String getLastUpdate(){
        return time;
    }
    @Override
    public String toString(){
        return "last_update: "+time;
    }
}
