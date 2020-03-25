package com.SuncityDevs.covid19sms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;


public class PolicyFragment extends Fragment {

    ViewPager viewPager;
    Button btn;
    CheckBox checkBox;

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
        checkBox = view.findViewById(R.id.checkBox);
        btn.setEnabled(false);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    btn.setEnabled(true);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((Tutorial)getActivity()).once_in_a_life_time();
                        }
                    });
                }
                else{
                    btn.setEnabled(false);
                }
            }
        });
    return view;
    }
}
