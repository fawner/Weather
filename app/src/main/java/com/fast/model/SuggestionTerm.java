package com.fast.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 亲爱的~ on 2016/11/6.
 */
public class SuggestionTerm {
    private JSONObject json;

    public SuggestionTerm(JSONObject json){
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

    public String getBrief(){
        return GETINFO("brief");
    }
    public String getDetails(){
        return GETINFO("details");
    }
    @Override
    public String toString(){
        String str = "\nbrief: "+getBrief()+
                "\ndetails: "+getDetails()+"\n";
        return str;
    }
}
