package com.SuncityDevs.covid19sms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ThirdFragment extends Fragment {

    TextView done;
    //TextView back;
    ViewPager viewPager;


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
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                ((Tutorial)getActivity()).once_in_a_life_time();
//                Intent intent = new Intent(getActivity(), Info_UI.class);
//                Tutorial.first_time = "1";
//
//                startActivity(intent);
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
