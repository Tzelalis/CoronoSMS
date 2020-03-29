package com.SuncityDevs.covid19sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tutorial extends AppCompatActivity {

    private SharedPreferences sharedPref;

    public static String first_time = "0";
    private String flag = "com.example.covid19sms.flag";
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = this.getSharedPreferences(
                "com.example.covid19sms", Context.MODE_PRIVATE);

        Log.v("covid19",  first_time);

        if ( sharedPref.getString(flag, "").equals("1")){
            Intent intent = new Intent(this,MainActivity.class );
            startActivity(intent);
        }
        else {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.SEND_SMS},
//                    1);

            setContentView(R.layout.activity_tutorial);

            viewPager = findViewById(R.id.viewPager);

            IntroAdapter adapter = new IntroAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
        }
    }


    public void once_in_a_life_time(){


        sharedPref.edit().putString(flag, "1").apply();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class );
        startActivity(intent);

        // if button is clicked, close the custom dialog
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sharedPref.edit().putString(flag, "1").apply();
//                dialog.dismiss();
//            }
//        });
//        startActivity(intent);
//
//        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light_NoTitleBar);
//        dialog.setContentView(R.layout.info_layout);
//        Button dialogButton = (Button) dialog.findViewById(R.id.info_close_button);
//        dialog.show();
//        // if button is clicked, close the custom dialog
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sharedPref.edit().putString(flag, "1").apply();
//                Intent intent = new Intent(getApplicationContext(),Info_UI.class );
//
//                dialog.dismiss();
//                startActivity(intent);
//
//
//            }
//        });

    }


}
