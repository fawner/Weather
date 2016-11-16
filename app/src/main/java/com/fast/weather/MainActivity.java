package com.fast.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        shared = getSharedPreferences(String.valueOf(R.string.CONFIGFILENAME), Activity.MODE_PRIVATE);
        edit = shared.edit();
        queryGET = new QueryGET();
        cityId = shared.getString("setCityId","beijing");

        thread = new Threaded();
        thread.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this,SetActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:return FragmentWeatherNow.newInstance(position + 1);
                case 1:return FragmentWeatherDaily.newInstance(position + 1);
                case 2:return FragmentLifeSuggestion.newInstance(position + 1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
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
            try {
                jsonWeatherNow = queryGET.getWeatherNow(cityId)
                        .getJSONArray("results")
                        .getJSONObject(0);
                jsonWeatherDaily = queryGET.getWeatherDaily(cityId)
                        .getJSONArray("results")
                        .getJSONObject(0);
                jsonLifeSuggestion = queryGET.getLifeSuggestion(cityId)
                        .getJSONArray("results")
                        .getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            edit.putString("jsonWeatherNow",MainActivity.this.jsonWeatherNow.toString());
            edit.putString("jsonWeatherDaily",MainActivity.this.jsonWeatherDaily.toString());
            edit.putString("jsonLifeSuggestion",MainActivity.this.jsonLifeSuggestion.toString());
            edit.commit();
        }
    }
}
