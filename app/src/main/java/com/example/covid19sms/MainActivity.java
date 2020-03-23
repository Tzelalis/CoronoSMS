package com.example.covid19sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private View selectedView;
    private Toast mToast = null;
    private String[] personalInfos;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;


    boolean doubleBackToExitPressedOnce = false;

//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            this.finishAffinity();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Διπλό κλικ για να αποχωρήσετε", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personalInfos = new String[3];


        String info = getIntent().getStringExtra("person_info");

        //Log.v("covid19", info);

        info = info.replace("\"", "");
        info = info.replace("[", "");
        info = info.replace("]", "");

        personalInfos = info.split(",");

        //String s = parts[0] +" "+ parts[1]+" "+parts[2];
        //Log.v("covid19", parts[0] +" "+ parts[1]+" "+parts[2]);
        //Toast.makeText(this.getApplicationContext(), s, Toast.LENGTH_SHORT).show();

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.SEND_SMS},
                1);

//
//        ArrayList<Option> optionsList = new ArrayList<Option>();
//        optionsList.add(new Option("Νοσοκομείο", R.drawable.baseline_local_hospital_36));
//        optionsList.add(new Option("Σουπερ Μαρκετ", R.drawable.baseline_local_hospital_36));
//        optionsList.add(new Option("Βολτα τον σκυλο", R.drawable.baseline_local_hospital_36));
//        optionsList.add(new Option("Υπηρεσια", R.drawable.baseline_local_hospital_36));
//        optionsList.add(new Option("Βοηθεια σε ατομο", R.drawable.baseline_local_hospital_36));
//        optionsList.add(new Option("Αθληση", R.drawable.baseline_local_hospital_36));
//
//        OptionAdapter optionAdapter = new OptionAdapter(this, optionsList);
//
//        ListView listview = findViewById(R.id.optionsListView);
//        listview.setAdapter(optionAdapter);
//
//        int count = optionAdapter.getCount();


    }

    public void setSelectedItem(View view) {

        if (this.selectedView == view && this.selectedView.isSelected() == true){
            findViewById(R.id.button).setVisibility(View.INVISIBLE);
            this.selectedView.setSelected(false);
            return;
        }

        if (this.selectedView != null){

            this.selectedView.setSelected(false);

        }




        view.setSelected(true);
        findViewById(R.id.button).setVisibility(View.VISIBLE);
        this.selectedView = view;
    }

    @SuppressLint("ResourceAsColor")
    public void submitInfo(View view) {
        if (selectedView == null) {
            String msg = getApplicationContext().getResources().getString(R.string.no_selected_item);
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
            mToast.show();
        } else {
            String category = selectedView.getTag().toString();


            this.smsSendMessage(category);
            Toast.makeText(this.getApplicationContext(),"Το μήνυμα σας στάλθηκε!", Toast.LENGTH_SHORT);

            final Drawable e = findViewById(R.id.button).getBackground();
            final Drawable d = this.getDrawable(R.drawable.round_disabled);
            findViewById(R.id.button).setEnabled(false);
            findViewById(R.id.button).setBackground(d);
            findViewById(R.id.button).postDelayed(new Runnable() {

                @Override
                public void run() {
                    findViewById(R.id.button).setEnabled(true);
                    findViewById(R.id.button).setBackground(e);
                }
            }, 2000);

        }

    }

    private void smsSendMessage(String category) {
        String destinationAddress = "6940211909";
        String scAddress = null;
        String smsMessage = category + " " + personalInfos[0] + " " + personalInfos[1] + " " + personalInfos[2];


        // Set pending intents to broadcast
        // when message sent and when delivered, or set to null.
        PendingIntent sentIntent = null, deliveryIntent = null;
        // Use SmsManager.
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage
                (destinationAddress, scAddress, smsMessage,
                        sentIntent, deliveryIntent);
    }

    private void checkForSmsPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "DEN EXEIS DIKAIWMA", Toast.LENGTH_SHORT);
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            Toast.makeText(this, "EXEIS DIKAIWMA", Toast.LENGTH_SHORT);
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(this, "thn phraaaa", Toast.LENGTH_SHORT).show();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
