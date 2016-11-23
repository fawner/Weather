package com.fast.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fast.model.LifeSuggestion;
import com.fast.model.Statu;
import com.fast.model.WeatherDaily;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentLifeSuggestion extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private boolean isRight = false;

    private SharedPreferences shared;
    private LifeSuggestion lifeSuggestion;

    private TextView suggestionFail;
    private ListView suggestionList;

    public FragmentLifeSuggestion() {
    }

    public static FragmentLifeSuggestion newInstance(int sectionNumber) {
        FragmentLifeSuggestion fragment = new FragmentLifeSuggestion();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_life_suggestion, container, false);

        shared = getActivity().getSharedPreferences(String.valueOf(R.string.CONFIGFILENAME), Activity.MODE_PRIVATE);

        String str_suggestion;
        str_suggestion = shared.getString("jsonLifeSuggestion","0");
        if (!str_suggestion.equals("0")) {
            isRight = true;
        }

        suggestionFail = (TextView)rootView.findViewById(R.id.suggestion_fail);
        suggestionList = (ListView)rootView.findViewById(R.id.suggestion_list);

        if(isRight){
            JSONObject jsonObject;
            Statu statu;
            try {
                jsonObject = new JSONObject(str_suggestion);
                if (jsonObject.has("status")) {
                    statu = new Statu(jsonObject);
                    switch (statu.getStatus_code()) {
                        case "AP010006":
                            Toast.makeText(getActivity(), R.string.status_AP010006+"，请重新设置城市", Toast.LENGTH_SHORT).show();
                            break;
                        case "AP010010":
                            Toast.makeText(getActivity(), R.string.status_AP010010+"，请重新设置城市", Toast.LENGTH_SHORT).show();
                            break;
                        case "AP010011":
                            Toast.makeText(getActivity(), R.string.status_AP010011+"，请重新设置城市", Toast.LENGTH_SHORT).show();
                            break;
                        case "AP100001":
                            Toast.makeText(getActivity(), R.string.status_AP100001+"，请重新设置城市", Toast.LENGTH_SHORT).show();
                            break;
                        case "AP100002":
                            Toast.makeText(getActivity(), R.string.status_AP100002+"，请重新设置城市", Toast.LENGTH_SHORT).show();
                            break;
                        case "AP100003":
                            Toast.makeText(getActivity(), R.string.status_AP100003+"，请重新设置城市", Toast.LENGTH_SHORT).show();
                            break;
                    }
                    startActivity(new Intent(getActivity(),SetActivity.class));
                }else{
                    lifeSuggestion = new LifeSuggestion(jsonObject.getJSONArray("results").getJSONObject(0));
                    setDate();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            suggestionFail.setVisibility(View.VISIBLE);
            suggestionList.setVisibility(View.GONE);
        }
        return rootView;
    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        Map<String, Object> map = null;

        String[] str_title = {
                "洗车",
                "穿衣",
                "感冒",
                "运动",
                "旅游",
                "紫外线"
        };
        String[] str_suggestion =  {
                lifeSuggestion.getSuggestion().getSuggestionTermCarWashing().getBrief(),
                lifeSuggestion.getSuggestion().getSuggestionTermDressing().getBrief(),
                lifeSuggestion.getSuggestion().getSuggestionTermFlu().getBrief(),
                lifeSuggestion.getSuggestion().getSuggestionTermSport().getBrief(),
                lifeSuggestion.getSuggestion().getSuggestionTermTravel().getBrief(),
                lifeSuggestion.getSuggestion().getSuggestionTermUv().getBrief()
        };
        for (int i = 0; i < str_title.length; i++) {
            map=new HashMap<String, Object>();
            if(!str_suggestion[i].equals("")){
                map.put("now_list_title",str_title[i]);
                map.put("now_list_details",str_suggestion[i]);
                list.add(map);
            }
        }
        return list;
    }

    private void setDate(){
        List<Map<String, Object>> list=getData();
        suggestionList.setAdapter(new NowListAdspter(getActivity(), list));
    }
}
