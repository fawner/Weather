package com.fast.weather;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
        final SharedPreferences.Editor edit = shared.edit();

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
                search();
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(SetActivity.this.getCurrentFocus().getWindowToken()
                            ,InputMethodManager.HIDE_NOT_ALWAYS);
                    search();
                }
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                set_city_id = locations[i].getLocationId();
                edit.putString("setCityId",set_city_id);
                edit.commit();
                startActivity(new Intent(SetActivity.this,MainActivity.class));
                finish();
            }
        });
    }
    private void search(){
        query_city_name = editText.getText().toString();
        if (query_city_name.equals("")) {
            Toast.makeText(SetActivity.this,R.string.editText_null,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SetActivity.this,MainActivity.class));
            finish();
        }else{
            query_city_name = pinyin.getStringPinYin(query_city_name);
            SetList setList = new SetList();
            setList.execute();
        }

    }
    private class SetList extends AsyncTask<Integer, Integer, JSONObject> {
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
                        Toast.makeText(SetActivity.this, R.string.status_AP010006, Toast.LENGTH_SHORT).show();
                        break;
                    case "AP010010":
                        Toast.makeText(SetActivity.this, R.string.status_AP010010, Toast.LENGTH_SHORT).show();
                        break;
                    case "AP010011":
                        Toast.makeText(SetActivity.this, R.string.status_AP010011, Toast.LENGTH_SHORT).show();
                        break;
                    case "AP100001":
                        Toast.makeText(SetActivity.this, R.string.status_AP100001, Toast.LENGTH_SHORT).show();
                        break;
                    case "AP100002":
                        Toast.makeText(SetActivity.this, R.string.status_AP100002, Toast.LENGTH_SHORT).show();
                        break;
                    case "AP100003":
                        Toast.makeText(SetActivity.this, R.string.status_AP100003, Toast.LENGTH_SHORT).show();
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
