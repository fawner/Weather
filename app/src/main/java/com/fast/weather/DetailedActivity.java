package com.fast.weather;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fast.model.LifeSuggestion;
import com.fast.model.WeatherDaily;
import com.fast.model.WeatherNow;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailedActivity extends AppCompatActivity {
    private TextView text_city_name = null;
    String city_name = null;
    String httpUrl = "https://api.thinkpage.cn/v3/weather/now.json?key=ihgy05vjoxn9kqig&location=";
    String httpArg = "&language=zh-Hans&unit=c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        text_city_name = (TextView) findViewById(R.id.text_city_name);

        Intent intent = getIntent();
        city_name = intent.getStringExtra("city_name");

        Text_set setText = new Text_set();
        setText.execute();


    }

    /***
     * 异步显示查询结果
     */
    protected class Text_set extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... integers) {
            String str = null;
            String str_return;
            QueryGET a = new QueryGET();
            JSONObject jsonReturn;
            JSONObject json;
            jsonReturn = a.getWeatherNow(city_name);//查询
            str_return = jsonReturn.toString();
            /*json = a.getWeatherNow(city_name)
                       .getJSONArray("results")//获取结果
                       .getJSONObject(0);*/
            return str_return;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

        @Override
        protected void onPostExecute(String result) {
            text_city_name.setText(result);
        }
    }
}
