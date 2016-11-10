package com.fast.weather;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.fast.model.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailedActivity extends AppCompatActivity {
    private TextView text_city_name = null;
    String city_name = null;

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

    /**
     * 异步显示查询结果
     */
    protected class Text_set extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... integers) {
            return "return";
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
