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
import android.widget.TextView;
import android.widget.Toast;

import com.SuncityDevs.covid19sms.MainActivity;
import com.SuncityDevs.covid19sms.R;

import org.json.JSONArray;


public class Info_UI extends Fragment {

    public Info_UI() {
        // Required empty public constructor
    }
    SharedPreferences sharedPref;
    private View view;
    private ViewPager viewPager;
    //private String flag = "com.example.covid19sms.address";
    public static int flag = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPref = this.getActivity().getSharedPreferences(
                "com.example.covid19sms", Context.MODE_PRIVATE);
        view =  inflater.inflate(R.layout.fragment_info_gr, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager);
        //sharedPref.edit().clear().apply();
        String change = this.getActivity().getIntent().getStringExtra("change");
        MainActivity handler = new MainActivity();
        //gia metabasi kai diatirisi stoixeiwn
        handler.fill_fields_with_prefs();

        if (flag!=1){
            handler.swap_activity(handler.read_from_pref());
        }
        return view;
    }

//    public void fill_fields_with_prefs(){
//
//
//        TextView firstname = view.findViewById(R.id.et_name);
//        TextView lastname = view.findViewById(R.id.et_last_name);
//        TextView address = view.findViewById(R.id.et_address);
//
//        firstname.setText(sharedPref.getString(key_first_name, ""));
//        lastname.setText(sharedPref.getString(key_last_name, ""));
//        address.setText(sharedPref.getString(key_address, ""));
//
//    }
//    public String read_from_pref() {
//
//        String read_firstname = sharedPref.getString(key_first_name, "");
//        String read_surname = sharedPref.getString(key_last_name, "");
//        String read_address = sharedPref.getString(key_address, "");
//
//        if (!(read_address.equals("") || read_firstname.equals("") || read_surname.equals("")) ){
//            JSONArray json = new JSONArray();
//            json.put(read_firstname);
//            json.put(read_surname);
//            json.put(read_address);
//            return json.toString();
//        } else {
//            return null;
//        }
//    }


    boolean doubleBackToExitPressedOnce = false;

    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            this.getActivity().finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
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
