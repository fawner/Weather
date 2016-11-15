package com.fast.weather;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fast.model.Location;
import com.fast.model.LocationSearch;
import com.fast.model.Statu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetActivity extends AppCompatActivity {
    private SharedPreferences shared;
    private String query_city_name;
    private String set_city_id;

    private QueryGET queryGET;
    private LocationSearch locationSearch;
    private Location[] locations;

    private Pinyin pinyin;

    private EditText editText;
    private Button button;
    private ListView listView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        shared = getSharedPreferences(String.valueOf(R.string.CONFIGFILENAME), Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();

        queryGET = new QueryGET();

        editText = (EditText)findViewById(R.id.edit_cityName);
        button = (Button)findViewById(R.id.send);
        listView = (ListView)findViewById(R.id.set_list);
        progressBar = (ProgressBar)findViewById(R.id.pro);
        progressBar.setVisibility(View.GONE);

        pinyin = new Pinyin();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query_city_name = editText.getText().toString();
                query_city_name = pinyin.getStringPinYin(query_city_name);
                SetList setList = new SetList();
                setList.execute();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                set_city_id = locations[i].getLocationId();
                Toast.makeText(SetActivity.this,set_city_id,Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected class SetList extends AsyncTask<Integer, Integer, JSONObject> {
        @Override
        protected JSONObject doInBackground(Integer... integers) {
            this.publishProgress(1);
            JSONObject query_json;
            Statu statu;
            query_json = queryGET.getLocationSearch(query_city_name);
            this.publishProgress(50);
            if (query_json.has("status")){
                statu = new Statu(query_json);
                switch (statu.getStatus_code()) {
                    case "AP010006":
                        Toast.makeText(SetActivity.this, "没有权限访问这个地点", Toast.LENGTH_SHORT).show();
                        break;
                    case "AP010010":
                        Toast.makeText(SetActivity.this, "没有这个地点", Toast.LENGTH_SHORT).show();
                        break;
                    case "AP010011":
                        Toast.makeText(SetActivity.this, "无法查找到制定IP地址对应的城市", Toast.LENGTH_SHORT).show();
                        break;
                    case "AP100001":
                        Toast.makeText(SetActivity.this, "系统内部错误：数据缺失", Toast.LENGTH_SHORT).show();
                        break;
                    case "AP100002":
                        Toast.makeText(SetActivity.this, "系统内部错误：数据错误", Toast.LENGTH_SHORT).show();
                        break;
                    case "AP100003":
                        Toast.makeText(SetActivity.this, "系统内部错误：服务内部错误", Toast.LENGTH_SHORT).show();
                        break;
                }
                return null;
            }else if(query_json.has("results")){
                this.publishProgress(100);
                return query_json;
            }
            return query_json;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(progress[0]);
            if(progress[0] == 100){
                progressBar.setVisibility(View.GONE);
            }

        }

        @Override
        protected void onPostExecute(JSONObject result) {
            if(result!=null){
                try {
                    locationSearch = new LocationSearch(result.getJSONArray("results"));
                    locations = locationSearch.getLocations();
                    List<Map<String, Object>> list=getData();
                    listView.setAdapter(new SetListAdspter(SetActivity.this, list));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0;i < locations.length;i++){
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("set_list_name",locations[i].getLocationName());
            map.put("set_list_country",locations[i].getLocationCountry());
            map.put("set_list_path",locations[i].getLocationPath());
            list.add(map);
        }
        return list;
    }
}
