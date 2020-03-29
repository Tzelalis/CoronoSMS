package com.SuncityDevs.covid19sms.Greece;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import static com.SuncityDevs.covid19sms.Greece.Info_UI.flag;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.SuncityDevs.covid19sms.Info_UI;
import com.SuncityDevs.covid19sms.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivity extends Fragment {

    public MainActivity() {
        // Required empty public constructor
    }

    private View selectedView;
    private Toast mToast = null;
    private String[] personalInfos;
    ViewPager viewPager;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.activity_main_gr, container, false);
        viewPager = getActivity().findViewById(R.id.viewPager);
        floatingActionButton = view.findViewById(R.id.button);
        bottomAppBar = view.findViewById(R.id.bar);
        info_button  = view.findViewById(R.id.menuChangeInfo);
        ((com.SuncityDevs.covid19sms.MainActivity)getActivity()).setSupportActionBar(bottomAppBar);
        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        flag = 0;
        personalInfos = new String[3];

//        String info = this.getActivity().getIntent().getStringExtra("person_info");
//
//        view.findViewById(R.id.button).setEnabled(false);
//
//        info = info.replace("\"", "");
//        info = info.replace("[", "");
//        info = info.replace("]", "");
//
//        personalInfos = info.split(",");
//
//        //String s = parts[0] +" "+ parts[1]+" "+parts[2];
//        //Log.v("covid19", parts[0] +" "+ parts[1]+" "+parts[2]);
//        Toast.makeText(this.getActivity().getApplicationContext(), "s", Toast.LENGTH_SHORT).show();



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitInfo(v);
            }
        });
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
        String destinationAddress = "13033";
        String scAddress = null;
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

}
