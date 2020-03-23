package com.example.covid19sms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Info_UI extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private String key_first_name = "com.example.covid19sms.first_name";
    private String key_last_name = "com.example.covid19sms.last_name";
    private String key_address = "com.example.covid19sms.address";
    private String flag = "com.example.covid19sms.address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        sharedPref = this.getSharedPreferences(
                "com.example.covid19sms", Context.MODE_PRIVATE);




        //sharedPref.edit().clear().apply();
        swap_activity(read_from_pref());

    }

    private void swap_activity(String info) {

        if (info != null) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("person_info", info);
            startActivity(intent);
        }

    }

    public String read_from_pref() {

        String read_firstname = sharedPref.getString(key_first_name, "");
        String read_surname = sharedPref.getString(key_last_name, "");
        String read_address = sharedPref.getString(key_address, "");


        if (!(read_address.equals("") || read_firstname.equals("") || read_surname.equals("")) ){
            JSONArray json = new JSONArray();
            json.put(read_firstname);
            json.put(read_surname);
            json.put(read_address);
            return json.toString();
        } else {
            return null;
        }


    }


    public void write_to_pref(View view) {

        //write
        Log.v("covid19", "save_btn_pressed");

        TextView firstname = findViewById(R.id.et_name);
        TextView lastname = findViewById(R.id.et_last_name);
        TextView address = findViewById(R.id.et_address);

        String firstname_text = firstname.getText().toString();
        String lastname_text = lastname.getText().toString();
        String address_text = address.getText().toString();

        sharedPref.edit().putString(key_first_name, firstname_text).apply();
        sharedPref.edit().putString(key_last_name, lastname_text).apply();
        sharedPref.edit().putString(key_address, address_text).apply();




        if (!(firstname_text.equals("") || lastname_text.equals("") || address_text.equals(""))) {
            Log.v("covid19", "no empty values");

            AlertDialog.Builder alt_bld = new AlertDialog.Builder(Info_UI.this);
            alt_bld.setTitle("ΠΡΟΣΟΧΗ");
            alt_bld.setIcon(R.drawable.ic_alert);
            alt_bld.setMessage("Για να βεβαιωθείτε οτι έχει ολοκληρωθεί η διαδικασία" +
                    " θα πρέπει να λάβετε μήνυμα επιβεβαίωσης.");
            alt_bld.setCancelable(true);
            alt_bld.setNeutralButton("Εντάξει", new DialogInterface.OnClickListener (){
                @Override
                public void onClick(DialogInterface dialog,int which) {
                    dialog.dismiss();
                    swap_activity(read_from_pref());

                }
            });

            AlertDialog alert = alt_bld.create();
            alert.show();

        } else {
            Log.v("covid19", "empty values");

            Toast.makeText(this.getApplicationContext(), R.string.err_toast_info_missing_gr, Toast.LENGTH_SHORT).show();
        }


    }


    //Getters and Setters
    public String getKey_first_name() {
        return key_first_name;
    }

    public String getKey_last_name() {
        return key_last_name;
    }

    public String getKey_address() {
        return key_address;
    }


}
