package com.example.covid19sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class Tutorial extends AppCompatActivity {

    public static String first_time = "0";
    private SharedPreferences sharedPref;
    private String flag = "com.example.covid19sms.flag";
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = this.getSharedPreferences(
                "com.example.covid19sms", Context.MODE_PRIVATE);

        Log.v("covid19",  first_time);

        if ( sharedPref.getString(flag, "").equals("1")){
            Intent intent = new Intent(this,Info_UI.class );
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_tutorial);

            viewPager = findViewById(R.id.viewPager);

            IntroAdapter adapter = new IntroAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
        }
    }


    public void once_in_a_life_time(){
        Intent intent = new Intent(getApplicationContext(),Info_UI.class );
        sharedPref.edit().putString(flag, "1").apply();
        startActivity(intent);
    }
}
