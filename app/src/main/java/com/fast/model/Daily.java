package com.fast.model;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by 亲爱的~ on 2016/11/5.
 */
public class Daily {
    //今天
    private DailyData dailyToday;
    //明天
    private DailyData dailyTomorrow;
    //后天
    private DailyData dailtAfterTomorrow;

    /**
     * 构造
     * @param jsonArray jsonArray
     */
    public Daily(JSONArray jsonArray){
        try {
            dailyToday = new DailyData(jsonArray.getJSONObject(0));
            dailyTomorrow = new DailyData(jsonArray.getJSONObject(1));
            dailtAfterTomorrow = new DailyData(jsonArray.getJSONObject(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取今天天气的数据
     * @return DailyData对象
     */
    public DailyData getDailyToday(){
        return dailyToday;
    }
    /**
     * 获取明天天气的数据
     * @return DailyData对象
     */
    public DailyData getDailyTomorrow(){
        return dailyTomorrow;
    }
    /**
     * 获取后天天气的数据
     * @return DailyData对象
     */
    public DailyData getDailtAfterTomorrow(){
        return dailtAfterTomorrow;
    }

    /**
     * 重写toString
     * @return String
     */
    @Override
    public String toString(){
        String str = "Today:\n"+getDailyToday().toString()+
                "\nTomorrow:\n"+getDailyTomorrow().toString()+
                "\nAfterTomorrow:\n"+getDailtAfterTomorrow().toString();
        return str;
    }
}
