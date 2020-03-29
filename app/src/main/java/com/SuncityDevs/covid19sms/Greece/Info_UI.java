package com.SuncityDevs.covid19sms.Greece;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.SuncityDevs.covid19sms.MainActivity;
import com.SuncityDevs.covid19sms.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;


public class Info_UI extends Fragment {

    public Info_UI() {
        // Required empty public constructor
    }
    SharedPreferences sharedPref;
    private View view;
    private ViewPager viewPager;
    private FloatingActionButton submitButton;
    //private String flag = "com.example.covid19sms.address";
    public static int flag = 0;
    private String key_first_name = "com.example.covid19sms.first_name";
    private String key_last_name = "com.example.covid19sms.last_name";
    private String key_address = "com.example.covid19sms.address";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPref = this.getActivity().getSharedPreferences(
                "com.example.covid19sms", Context.MODE_PRIVATE);
        view =  inflater.inflate(R.layout.fragment_info_gr, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager);
        submitButton = view.findViewById(R.id.btn_save);
        //sharedPref.edit().clear().apply();
        String change = this.getActivity().getIntent().getStringExtra("change");
        //gia metabasi kai diatirisi stoixeiwn
        fill_fields_with_prefs(view);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write_to_pref(view);
            }
        });
        if (flag!=1){
            swap_activity(read_from_pref());
        }
        return view;
    }


    public void write_to_pref(View view) {

        //write
        Log.v("covid19", "save_btn_pressed");

        TextView firstname = view.findViewById(R.id.et_name);
        TextView lastname = view.findViewById(R.id.et_last_name);
        TextView address = view.findViewById(R.id.et_address);

        String firstname_text = firstname.getText().toString();
        String lastname_text = lastname.getText().toString();
        String address_text = address.getText().toString();

        sharedPref.edit().putString("key_first_name", firstname_text).apply();
        sharedPref.edit().putString("key_last_name", lastname_text).apply();
        sharedPref.edit().putString("key_address", address_text).apply();


        if (!(firstname_text.equals("") || lastname_text.equals("") || address_text.equals(""))) {
            Log.v("covid19", "no empty values");

            viewPager.setCurrentItem(1);
        } else {
            Log.v("covid19", "empty values");

            Toast.makeText(this.getActivity().getApplicationContext(), R.string.err_toast_info_missing_gr, Toast.LENGTH_SHORT).show();
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


    public void fill_fields_with_prefs(View view){


        TextView firstname = view.findViewById(R.id.et_name);
        TextView lastname = view.findViewById(R.id.et_last_name);
        TextView address = view.findViewById(R.id.et_address);

        firstname.setText(sharedPref.getString(key_first_name, ""));
        lastname.setText(sharedPref.getString(key_last_name, ""));
        address.setText(sharedPref.getString(key_address, ""));

    }
    public void swap_activity(String info) {

        if (info != null) {
            viewPager.setCurrentItem(1);
        }
    }


    boolean doubleBackToExitPressedOnce = false;

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            this.getActivity().finishAffinity();
            return;
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(this.getActivity(), "Διπλό κλικ για να αποχωρήσετε", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }



    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (this.getActivity().getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(this.getActivity().getCurrentFocus().getWindowToken(), 0);
        }
        return super.getActivity().dispatchTouchEvent(ev);
    }


//    private void swap_activity(String info) {
//
//        if (info != null) {
//            viewPager.setCurrentItem(1);
//        }
//
//
//    }
}
