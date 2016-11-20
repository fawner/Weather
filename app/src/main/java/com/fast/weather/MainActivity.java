package com.fast.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences shared;
    private SharedPreferences.Editor edit;
    private QueryGET queryGET;
    private String cityId;
    private JSONObject jsonWeatherNow;
    private JSONObject jsonWeatherDaily;
    private JSONObject jsonLifeSuggestion;
    private Threaded thread;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabTextColors(Color.rgb(78,78,78),Color.rgb(120,120,120));

        shared = getSharedPreferences(String.valueOf(R.string.CONFIGFILENAME), Activity.MODE_PRIVATE);
        edit = shared.edit();
        queryGET = new QueryGET();
        cityId = shared.getString("setCityId","beijing");

        thread = new Threaded();
        thread.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,SetActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:return FragmentWeatherNow.newInstance(position + 1);
                case 1:return FragmentWeatherDaily.newInstance(position + 1);
                case 2:return FragmentLifeSuggestion.newInstance(position + 1);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "实时天气";
                case 1:
                    return "天气预报";
                case 2:
                    return "建议";
            }
            return null;
        }
    }
    private class Threaded extends Thread{
        @Override
        public void run() {
            jsonWeatherNow = queryGET.getWeatherNow(cityId);
            jsonWeatherDaily = queryGET.getWeatherDaily(cityId);
            jsonLifeSuggestion = queryGET.getLifeSuggestion(cityId);
            edit.putString("jsonWeatherNow",MainActivity.this.jsonWeatherNow.toString());
            edit.putString("jsonWeatherDaily",MainActivity.this.jsonWeatherDaily.toString());
            edit.putString("jsonLifeSuggestion",MainActivity.this.jsonLifeSuggestion.toString());
            edit.commit();
        }
    }
}
