package com.fast.weather;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fast.model.WeatherDaily;
import com.fast.model.WeatherNow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentWeatherNow extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private boolean isRight = false;

    private WeatherNow weatherNow;
    private WeatherDaily weatherDaily;
    private SharedPreferences shared;
    private JSONObject jsonNow;

    private ImageView nowImgWeatherCode;
    private TextView nowTextUpdatetime;
    private TextView nowTextTemperature;
    private TextView nowTextCity;
    private TextView nowText;
    private TextView nowListTi;
    private ListView nowList;

    public FragmentWeatherNow() {
    }

    public static FragmentWeatherNow newInstance(int sectionNumber) {
        FragmentWeatherNow fragment = new FragmentWeatherNow();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_now, container, false);

        shared = getActivity().getSharedPreferences(String.valueOf(R.string.CONFIGFILENAME), Activity.MODE_PRIVATE);
        final SharedPreferences.Editor edit = shared.edit();

        String str_now;
        String str_daily;
        str_now = shared.getString("jsonWeatherNow","0");
        str_daily = shared.getString("jsonWeatherDaily","0");
        if (!str_now.equals("0") && !str_daily.equals("0")) {
            isRight = true;
        }

        nowImgWeatherCode = (ImageView)rootView.findViewById(R.id.now_img_weather_code);
        nowTextUpdatetime = (TextView)rootView.findViewById(R.id.now_text_updatetime);
        nowTextTemperature = (TextView)rootView.findViewById(R.id.now_text_temperature);
        nowTextCity = (TextView)rootView.findViewById(R.id.now_text_city);
        nowText = (TextView)rootView.findViewById(R.id.now_text);
        nowListTi = (TextView)rootView.findViewById(R.id.now_list_ti);
        nowList = (ListView)rootView.findViewById(R.id.now_list);
        if(isRight){
            try {
                weatherNow = new WeatherNow(new JSONObject(str_now));
                weatherDaily = new WeatherDaily(new JSONObject(str_daily));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            setDate();

        }else {

            nowImgWeatherCode.setVisibility(View.GONE);
            nowTextTemperature.setVisibility(View.GONE);
            nowTextUpdatetime.setVisibility(View.GONE);
            nowTextCity.setVisibility(View.GONE);
            nowText.setVisibility(View.GONE);
            nowListTi.setVisibility(View.GONE);
            nowList.setVisibility(View.GONE);
        }



        return rootView;
    }
    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        Map<String, Object> map = null;

        String[] str_title = {
                "温度",
                "白天天气",
                "夜间天气",
                "风向",
                "风向角度",
                "风速",
                "风力等级"
        };
        String[] str_details =  {
                weatherDaily.getDaily().getDailyToday().getLow()+"° / "+weatherDaily.getDaily().getDailyToday().getHigh()+"°",
                weatherDaily.getDaily().getDailyToday().getTextDay(),
                weatherDaily.getDaily().getDailyToday().getTextNight(),
                weatherDaily.getDaily().getDailyToday().getWindDirection(),
                weatherDaily.getDaily().getDailyToday().getWindDirectionDegree(),
                weatherDaily.getDaily().getDailyToday().getWindSpeed()+" km/h",
                weatherDaily.getDaily().getDailyToday().getWindScale()
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
        switch (weatherNow.getNow().getWeatherNowCode()) {
            case "0": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_0);break;
            case "1": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_1);break;
            case "2": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_2);break;
            case "3": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_3);break;
            case "4": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_4);break;
            case "5": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_5);break;
            case "6": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_6);break;
            case "7": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_7);break;
            case "8": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_8);break;
            case "9": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_9);break;
            case "10": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_10);break;
            case "11": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_11);break;
            case "12": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_12);break;
            case "13": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_13);break;
            case "14": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_14);break;
            case "15": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_15);break;
            case "16": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_16);break;
            case "17": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_17);break;
            case "18": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_18);break;
            case "19": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_19);break;
            case "20": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_20);break;
            case "21": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_21);break;
            case "22": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_22);break;
            case "23": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_23);break;
            case "24": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_24);break;
            case "25": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_25);break;
            case "26": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_26);break;
            case "27": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_27);break;
            case "28": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_28);break;
            case "29": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_29);break;
            case "30": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_30);break;
            case "31": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_31);break;
            case "32": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_32);break;
            case "33": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_33);break;
            case "34": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_34);break;
            case "35": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_35);break;
            case "36": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_36);break;
            case "37": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_37);break;
            case "38": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_38);break;
            case "99": nowImgWeatherCode.setImageResource(R.mipmap.img_weathercode_99);break;
        }
        String str;
        str = weatherNow.getLastUpdate().getLastUpdate();
        str = str.substring(0,10);
        nowTextUpdatetime.setText(str);
        nowText.setText(weatherNow.getNow().getWeatherNowText());
        nowTextTemperature.setText(weatherNow.getNow().getWeatherNowTemperature()+" °C");
        nowTextCity.setText(weatherNow.getLocation().getLocationName());
        List<Map<String, Object>> list=getData();
        nowList.setAdapter(new NowListAdspter(getActivity(), list));
    }
}
