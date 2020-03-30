package com.SuncityDevs.covid19sms.Greece;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import static com.SuncityDevs.covid19sms.Greece.Info_UI.flag;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.SuncityDevs.covid19sms.Info_UI;
import com.SuncityDevs.covid19sms.ListView.Selection;
import com.SuncityDevs.covid19sms.ListView.SelectionAdapter;
import com.SuncityDevs.covid19sms.LockableViewPager;
import com.SuncityDevs.covid19sms.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends Fragment {

    public MainActivity() {
        // Required empty public constructor
    }
    private View selectedView;
    private Toast mToast = null;
    private String[] personalInfos;
    LockableViewPager viewPager;
    SharedPreferences sharedPref;
    private View view;
    private ArrayList<Selection> selectionsArrayList;
    private SelectionAdapter selectionAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.main_list_view_gr, container, false);
        viewPager = getActivity().findViewById(R.id.LockableViewPager);
        sharedPref = getActivity().getSharedPreferences("com.example.covid19sms", Context.MODE_PRIVATE);
        floatingActionButton = view.findViewById(R.id.button);
        bottomAppBar = view.findViewById(R.id.bar);
        info_button  = view.findViewById(R.id.menuChangeInfo);
        flag = 0;
        String info = "";

        info += sharedPref.getString("first_name", "");
        info += " " + sharedPref.getString("last_name", "");
        info += " " + sharedPref.getString("address", "");
        final String test = info;
        view.findViewById(R.id.button).setEnabled(false);
        ((com.SuncityDevs.covid19sms.MainActivity)getActivity()).setSupportActionBar(bottomAppBar);
        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                Toast.makeText(getContext(), test, Toast.LENGTH_LONG).show();
            }
        });

//        String s = parts[0] +" "+ parts[1]+" "+parts[2];
//        Log.v("covid19", parts[0] +" "+ parts[1]+" "+parts[2]);
        //  Toast.makeText(this.getActivity().getApplicationContext(), "s", Toast.LENGTH_SHORT).show();



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitInfo(v);
            }
        });

        fillListView(view);
        listViewListener(view);


        return view;
    }
    FloatingActionButton floatingActionButton;
    BottomAppBar bottomAppBar;
    MenuItem info_button;

    public void setSelectedItem(View view) {

        if (this.selectedView == view && this.selectedView.isSelected() == true) {
            view.findViewById(R.id.button).setEnabled(false);
            view.findViewById(R.id.button).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
            this.selectedView.setSelected(false);
            return;
        }

        if (this.selectedView != null) {

            this.selectedView.setSelected(false);

        }

        view.setSelected(true);
        view.findViewById(R.id.button).setEnabled(true);
        view.findViewById(R.id.button).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.submitButton)));
        this.selectedView = view;
    }

    @SuppressLint("ResourceAsColor")
    public void submitInfo(View view) {
        if (selectedView == null) {
            String msg = this.getActivity().getApplicationContext().getResources().getString(R.string.no_selected_item);
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT);
            mToast.show();
        } else {
            String category = selectedView.getTag().toString();
            smsSendMessage(category);

        }

    }

    private void smsSendMessage(String category) {
        String smsMessage = category + " " + personalInfos[0] + " " + personalInfos[1] + " " + personalInfos[2];

        Uri uri = Uri.parse("smsto:13033");
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsMessage);
        startActivity(it);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        this.getActivity().getMenuInflater().inflate(R.menu.bottom_menu, menu);
        return true;
    }

    public void showDialog() {
        // custom dialog
        final Dialog dialog = new Dialog(this.getActivity(), android.R.style.Theme_Light_NoTitleBar);
        dialog.setContentView(R.layout.info_layout);
        Button dialogButton = (Button) dialog.findViewById(R.id.info_close_button);
        dialog.show();


//        TextView t2 = dialog.findViewById(R.id.textView17);
//        t2.setMovementMethod(LinkMovementMethod.getInstance());

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(MainActivity.this, "ASDAFA", Toast.LENGTH_SHORT).show();
        showDialog();
        return true;
    }

    //fill arraylist -> create selectionAdapter -> setAdapter to listview -> create listener
    private void fillListView(View rootView){
        this.selectionsArrayList = new ArrayList<Selection>();
        this.selectionsArrayList.add(new Selection(R.drawable.ic_hospital, getResources().getString(R.string.option1)));
        this.selectionsArrayList.add(new Selection(R.drawable.ic_market, getResources().getString(R.string.option2)));
        this.selectionsArrayList.add(new Selection(R.drawable.ic_bank, getResources().getString(R.string.option3)));
        this.selectionsArrayList.add(new Selection(R.drawable.ic_wheelchair, getResources().getString(R.string.option4)));
        this.selectionsArrayList.add(new Selection(R.drawable.ic_church, getResources().getString(R.string.option5)));
        this.selectionsArrayList.add(new Selection(R.drawable.ic_walking, getResources().getString(R.string.option6)));

        this.selectionAdapter = new SelectionAdapter(getActivity(), this.selectionsArrayList);

        ListView selectionListView = rootView.findViewById(R.id.optionListViewLinearLayout);
        selectionListView.setAdapter(selectionAdapter);

        selectionListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(getActivity(), "Stop Clicking me", Toast.LENGTH_SHORT).show();
                Log.v("PAOKKK", "TESTTT");
            }
        });
    }

    //create listener for listview items
    private void listViewListener(View view){
        ListView lv = view.findViewById(R.id.optionListViewLinearLayout);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO select item listener , DEN EINAI SIGOURO OTI MPAINEI EDW O LISTENER!



            }
        });
    }

}
