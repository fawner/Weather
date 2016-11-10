package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/6.
 */
public class Suggestion {
    private SuggestionTerm suggestionTermCarWashing;//洗车
    private SuggestionTerm suggestionTermDressing;//穿衣
    private SuggestionTerm suggestionTermFlu;//感冒
    private SuggestionTerm suggestionTermSport;//运动
    private SuggestionTerm suggestionTermTravel;//旅游
    private SuggestionTerm suggestionTermUv;//紫外线

    /**
     * 构造
     * @param json jsonObject
     */
    public Suggestion(JSONObject json){
        try {
            suggestionTermCarWashing = new SuggestionTerm(json.getJSONObject("car_washing"));
            suggestionTermDressing = new SuggestionTerm(json.getJSONObject("dressing"));
            suggestionTermFlu = new SuggestionTerm(json.getJSONObject("flu"));
            suggestionTermSport = new SuggestionTerm(json.getJSONObject("sport"));
            suggestionTermTravel = new SuggestionTerm(json.getJSONObject("travel"));
            suggestionTermUv = new SuggestionTerm(json.getJSONObject("uv"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取洗车建议
     * @return SuggestionTerm
     */
    public SuggestionTerm getSuggestionTermCarWashing() {
        return suggestionTermCarWashing;
    }
    /**
     * 获取穿衣建议
     * @return SuggestionTerm
     */
    public SuggestionTerm getSuggestionTermDressing() {
        return suggestionTermDressing;
    }
    /**
     * 获取感冒程度
     * @return SuggestionTerm
     */
    public SuggestionTerm getSuggestionTermFlu() {
        return suggestionTermFlu;
    }
    /**
     * 获取运动建议
     * @return SuggestionTerm
     */
    public SuggestionTerm getSuggestionTermSport() {
        return suggestionTermSport;
    }
    /**
     * 获取旅游建议
     * @return SuggestionTerm
     */
    public SuggestionTerm getSuggestionTermTravel() {
        return suggestionTermTravel;
    }
    /**
     * 获取紫外线程度
     * @return SuggestionTerm
     */
    public SuggestionTerm getSuggestionTermUv() {
        return suggestionTermUv;
    }
    /**
     * 重写toStrinig
     * @return String
     */
    @Override
    public String toString(){
        String str = "car_washing: "+getSuggestionTermCarWashing().toString()+
                "dressing: "+getSuggestionTermDressing().toString()+
                "flu: "+getSuggestionTermFlu().toString()+
                "sport: "+getSuggestionTermSport().toString()+
                "travel: "+getSuggestionTermTravel().toString()+
                "uv: "+getSuggestionTermUv().toString();
        return str;
    }

}
