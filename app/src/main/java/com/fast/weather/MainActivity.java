package com.fast.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button send = null;
    EditText edit_city = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_city = (EditText)findViewById(R.id.edit_city);
        send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //指定跳转
                Intent intent = new Intent(MainActivity.this,DetailedActivity.class);
                String str = edit_city.getText().toString();
                intent.putExtra("city_name",str);
                startActivity(intent);
            }
        });
    }
}
