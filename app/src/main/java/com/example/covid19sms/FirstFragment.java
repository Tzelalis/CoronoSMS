package com.example.covid19sms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class FirstFragment extends Fragment {

    TextView next;
    ViewPager viewPager;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        //initialize viewpager from tutorial activity
        viewPager = getActivity().findViewById(R.id.viewPager);

        next = view.findViewById(R.id.slider_one_next);

        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }

        });


        return view;
    }


}
