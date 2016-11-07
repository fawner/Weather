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
    public SuggestionTerm getSuggestionTermCarWashing() {
        return suggestionTermCarWashing;
    }
    public SuggestionTerm getSuggestionTermDressing() {
        return suggestionTermDressing;
    }
    public SuggestionTerm getSuggestionTermFlu() {
        return suggestionTermFlu;
    }
    public SuggestionTerm getSuggestionTermSport() {
        return suggestionTermSport;
    }
    public SuggestionTerm getSuggestionTermTravel() {
        return suggestionTermTravel;
    }
    public SuggestionTerm getSuggestionTermUv() {
        return suggestionTermUv;
    }

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
