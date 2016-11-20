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

import com.fast.model.Statu;
import com.fast.model.WeatherDaily;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentWeatherDaily extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private boolean isRight = false;

    private SharedPreferences shared;
    private WeatherDaily weatherDaily;

    private TextView dailyFail;
    private ListView dailyList;

    public FragmentWeatherDaily() {
    }

    public static FragmentWeatherDaily newInstance(int sectionNumber) {
        FragmentWeatherDaily fragment = new FragmentWeatherDaily();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_daily, container, false);

        shared = getActivity().getSharedPreferences(String.valueOf(R.string.CONFIGFILENAME), Activity.MODE_PRIVATE);

        String str_daily;
        str_daily = shared.getString("jsonWeatherDaily","0");
        if (!str_daily.equals("0")) {
            isRight = true;
        }

        dailyFail = (TextView)rootView.findViewById(R.id.daily_fail);
        dailyList = (ListView)rootView.findViewById(R.id.daily_list);

        if(isRight){
            JSONObject jsonObject;
            Statu statu;
            try {
                jsonObject = new JSONObject(str_daily);
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
                    weatherDaily = new WeatherDaily(jsonObject);
                    setDate();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            dailyFail.setVisibility(View.VISIBLE);
            dailyList.setVisibility(View.GONE);
        }
        return rootView;
    }

    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        Map<String, Object> map = null;

        String[] str_title = {
                "明天◢",
                "时间",
                "温度",
                "白天天气",
                "夜间天气",
                "风向",
                "风向角度",
                "风速",
                "风力等级",
                "后天◢",
                "时间",
                "温度",
                "白天天气",
                "夜间天气",
                "风向",
                "风向角度",
                "风速",
                "风力等级"
        };
        String strTomorrow = weatherDaily.getDaily().getDailyTomorrow().getDate();
        String strAfterTomorrow = weatherDaily.getDaily().getDailyAfterTomorrow().getDate();
        String[] str_details =  {
                " ",
                strTomorrow,
                weatherDaily.getDaily().getDailyTomorrow().getLow()+"°/ "+weatherDaily.getDaily().getDailyTomorrow().getHigh()+"°",
                weatherDaily.getDaily().getDailyTomorrow().getTextDay(),
                weatherDaily.getDaily().getDailyTomorrow().getTextNight(),
                weatherDaily.getDaily().getDailyTomorrow().getWindDirection(),
                weatherDaily.getDaily().getDailyTomorrow().getWindDirectionDegree(),
                weatherDaily.getDaily().getDailyTomorrow().getWindSpeed()+" km/h",
                weatherDaily.getDaily().getDailyTomorrow().getWindScale(),
                " ",
                strAfterTomorrow,
                weatherDaily.getDaily().getDailyAfterTomorrow().getLow()+"°/ "+weatherDaily.getDaily().getDailyAfterTomorrow().getHigh()+"°",
                weatherDaily.getDaily().getDailyAfterTomorrow().getTextDay(),
                weatherDaily.getDaily().getDailyAfterTomorrow().getTextNight(),
                weatherDaily.getDaily().getDailyAfterTomorrow().getWindDirection(),
                weatherDaily.getDaily().getDailyAfterTomorrow().getWindDirectionDegree(),
                weatherDaily.getDaily().getDailyAfterTomorrow().getWindSpeed()+" km/h",
                weatherDaily.getDaily().getDailyAfterTomorrow().getWindScale(),
        };
        for (int i = 0; i < str_title.length; i++) {
            map=new HashMap<String, Object>();
            if(!str_details[i].equals("")){
                map.put("now_list_title",str_title[i]);
                map.put("now_list_details",str_details[i]);
                list.add(map);
            }

        }
        return list;
    }

    private void setDate(){
        List<Map<String, Object>> list=getData();
        dailyList.setAdapter(new NowListAdspter(getActivity(), list));
    }
}