package com.example.covid19sms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.ConstraintHorizontalLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Info_UI extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private String key_first_name = "com.example.covid19sms.first_name";
    private String key_last_name = "com.example.covid19sms.last_name";
    private String key_address = "com.example.covid19sms.address";
    //private String flag = "com.example.covid19sms.address";
    public static int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);


        sharedPref = this.getSharedPreferences(
                "com.example.covid19sms", Context.MODE_PRIVATE);


        //sharedPref.edit().clear().apply();
        String change = getIntent().getStringExtra("change");

        //gia metabasi kai diatirisi stoixeiwn
        fill_fields_with_prefs();

        if (flag!=1){
            swap_activity(read_from_pref());
        }


    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            this.finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Διπλό κλικ για να αποχωρήσετε", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    private void swap_activity(String info) {

        if (info != null) {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("person_info", info);
            startActivity(intent);
        }


    }

    public void fill_fields_with_prefs(){


        TextView firstname = findViewById(R.id.et_name);
        TextView lastname = findViewById(R.id.et_last_name);
        TextView address = findViewById(R.id.et_address);

        firstname.setText(sharedPref.getString(key_first_name, ""));
        lastname.setText(sharedPref.getString(key_last_name, ""));
        address.setText(sharedPref.getString(key_address, ""));


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

            swap_activity(read_from_pref());

//            AlertDialog.Builder alt_bld = new AlertDialog.Builder(Info_UI.this);
//            alt_bld.setTitle("ΠΡΟΣΟΧΗ");
//
//            alt_bld.setIcon(R.drawable.ic_alert);
//            alt_bld.setMessage("Για να βεβαιωθείτε οτι έχει ολοκληρωθεί η διαδικασία" +
//                    " θα πρέπει να λάβετε μήνυμα επιβεβαίωσης.");
//            alt_bld.setCancelable(false);
//            alt_bld.setNeutralButton("Εντάξει", new DialogInterface.OnClickListener (){
//                @Override
//                public void onClick(DialogInterface dialog,int which) {
//                    dialog.dismiss();
//                    swap_activity(read_from_pref());
//                }
//            }
//            );
//
//
//             AlertDialog alert = alt_bld.create();
//
//            alert.show();





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
