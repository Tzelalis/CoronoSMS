package com.SuncityDevs.covid19sms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class PolicyFragment extends Fragment {

    ViewPager viewPager;
    Button btn;
    public PolicyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.privacy_policy_layout, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager);
         btn = view.findViewById(R.id.privacy_policy_button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((Tutorial) getActivity()).once_in_a_life_time();

//                Intent intent = new Intent(getActivity(), Info_UI.class);
//                Tutorial.first_time = "1";
//
//                startActivity(intent);
            }

        });
        return view;
    }
}
