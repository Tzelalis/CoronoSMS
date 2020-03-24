package com.SuncityDevs.covid19sms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SecondFragment extends Fragment {

    TextView next;
    TextView back;
    ViewPager viewPager;


    public SecondFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_second, container, false);
        //initialize viewpager from tutorial activity
        viewPager = getActivity().findViewById(R.id.viewPager);


        next = view.findViewById(R.id.slider_two_next);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }

        });


        back = view.findViewById(R.id.slider_two_back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }

        });


        return view;
    }
}
