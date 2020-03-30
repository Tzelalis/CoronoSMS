package com.SuncityDevs.covid19sms.Tutorial;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.SuncityDevs.covid19sms.R;


public class ThirdFragment extends Fragment {

    TextView done;
    //TextView back;
    ViewPager viewPager;
    public static int privacy_flag = 0;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_third, container, false);
        //initialize viewpager from tutorial activity
        viewPager = getActivity().findViewById(R.id.viewPager);


        done = view.findViewById(R.id.slider_end);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3);
            }

        });




//        back = view.findViewById(R.id.slider_three_back);
//        back.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(1);
//            }
//
//        });


        return view;
    }

}
