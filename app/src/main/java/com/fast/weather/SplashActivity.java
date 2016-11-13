package com.fast.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private boolean isFirst;
    private SharedPreferences shared;
    private Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        shared = getSharedPreferences(String.valueOf(R.string.CONFIGFILENAME), Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = shared.edit();
        isFirst = shared.getBoolean("isFirst",true);
        //是否第一次进入
        if (isFirst) {
            intent = new Intent(SplashActivity.this, SetActivity.class);
            edit.putBoolean("isFirst",false);
            edit.commit();
        }else{
            intent = new Intent(SplashActivity.this, MainActivity.class);
        }
        //延时
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}
