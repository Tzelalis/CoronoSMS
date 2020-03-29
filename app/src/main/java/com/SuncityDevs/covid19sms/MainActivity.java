package com.SuncityDevs.covid19sms;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;


public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    private SharedPreferences sharedPref;
    private String key_first_name = "com.example.covid19sms.first_name";
    private String key_last_name = "com.example.covid19sms.last_name";
    private String key_address = "com.example.covid19sms.address";
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutorial_activity);

        viewPager = findViewById(R.id.viewPager);

        GreeceAdapter adapter = new GreeceAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }



    public void write_to_pref(View view) {

        //write
        Log.v("covid19", "save_btn_pressed");

        TextView firstname = view.findViewById(R.id.et_name);
        TextView lastname = view.findViewById(R.id.et_last_name);
        TextView address = view.findViewById(R.id.et_address);

        String firstname_text = firstname.getText().toString();
        String lastname_text = lastname.getText().toString();
        String address_text = address.getText().toString();

        sharedPref.edit().putString(key_first_name, firstname_text).apply();
        sharedPref.edit().putString(key_last_name, lastname_text).apply();
        sharedPref.edit().putString(key_address, address_text).apply();


        if (!(firstname_text.equals("") || lastname_text.equals("") || address_text.equals(""))) {
            Log.v("covid19", "no empty values");

            swap_activity(read_from_pref());
        } else {
            Log.v("covid19", "empty values");

            Toast.makeText(this.getApplicationContext(), R.string.err_toast_info_missing_gr, Toast.LENGTH_SHORT).show();
        }
    }

        public String read_from_pref() {

            String read_firstname = sharedPref.getString(key_first_name, "");
            String read_surname = sharedPref.getString(key_last_name, "");
            String read_address = sharedPref.getString(key_address, "");

            if (!(read_address.equals("") || read_firstname.equals("") || read_surname.equals("")) ){
                JSONArray json = new JSONArray();
                json.put(read_firstname);
                json.put(read_surname);
                json.put(read_address);
                return json.toString();
            } else {
                return null;
            }
        }


        public void fill_fields_with_prefs(){


        TextView firstname = view.findViewById(R.id.et_name);
        TextView lastname = view.findViewById(R.id.et_last_name);
        TextView address = view.findViewById(R.id.et_address);

        firstname.setText(sharedPref.getString(key_first_name, ""));
        lastname.setText(sharedPref.getString(key_last_name, ""));
        address.setText(sharedPref.getString(key_address, ""));

    }
    public void swap_activity(String info) {

        if (info != null) {
            viewPager.setCurrentItem(1);
        }


    }












//    public void setSelectedItem(View view) {
//
//        if (this.selectedView == view && this.selectedView.isSelected() == true) {
//            findViewById(R.id.button).setEnabled(false);
//            findViewById(R.id.button).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
//            this.selectedView.setSelected(false);
//            return;
//        }
//
//        if (this.selectedView != null) {
//
//            this.selectedView.setSelected(false);
//
//        }
//
//        view.setSelected(true);
//        findViewById(R.id.button).setEnabled(true);
//        findViewById(R.id.button).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.submitButton)));
//        this.selectedView = view;
//    }
//
//    @SuppressLint("ResourceAsColor")
//    public void submitInfo(View view) {
//        if (selectedView == null) {
//            String msg = getApplicationContext().getResources().getString(R.string.no_selected_item);
//            if (mToast != null) {
//                mToast.cancel();
//            }
//            mToast = Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT);
//            mToast.show();
//        } else {
//            String category = selectedView.getTag().toString();
//            MainActivity.this.smsSendMessage(category);
////            if (checkForSmsPermission()) {
////                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
////
////                builder.setTitle("Επιβεβαίωση");
////                builder.setMessage("Θέλετε να στείλετε το μήνυμα;\n\n" +
////                        selectedView.getTag().toString() + " " + personalInfos[0] + " " + personalInfos[1] + " " + personalInfos[2]);
////
////                builder.setPositiveButton("Επιβεβαίωση", new DialogInterface.OnClickListener() {
////
////                    public void onClick(DialogInterface dialog, int which) {
////                        // Do nothing but close the dialog
////
////                        String category = selectedView.getTag().toString();
////
////                        MainActivity.this.smsSendMessage(category);
////
////                        final Drawable e = findViewById(R.id.button).getBackground();
////                        final Drawable d = MainActivity.this.getDrawable(R.drawable.round_disabled);
////                        //findViewById(R.id.button).setBackgroundColor(R.color.submitButtondisable);
////                        findViewById(R.id.button).setBackground(d);
////                        findViewById(R.id.button).setEnabled(false);
////
////
////                        dialog.dismiss();
////                        //Toast.makeText(MainActivity.this,"Το μήνυμα σας στάλθηκε!", Toast.LENGTH_SHORT).show();
////
////                        findViewById(R.id.button).postDelayed(new Runnable() {
////
////                            @Override
////                            public void run() {
////                                findViewById(R.id.button).setEnabled(true);
////                                findViewById(R.id.button).setBackground(e);
////                            }
////                        }, 2000);
////
////                    }
////                });
////
////                builder.setNegativeButton("Ακύρωση", new DialogInterface.OnClickListener() {
////
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
//////                    Intent intent = new Intent(getBaseContext(), Info_UI.class);
//////                    Info_UI.flag = 1;
//////                    startActivity(intent);
////                    }
////                });
////
////                AlertDialog alert = builder.create();
////                alert.setCancelable(true);
////                alert.show();
////
////            }
//
//        }
//
//    }
//
//    private void smsSendMessage(String category) {
//        String destinationAddress = "13033";
//        String scAddress = null;
//        String smsMessage = category + " " + personalInfos[0] + " " + personalInfos[1] + " " + personalInfos[2];
//
//
//
//            Uri uri = Uri.parse("smsto:13033");
//            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
//            it.putExtra("sms_body", smsMessage);
//            startActivity(it);
//
////        // Set pending intents to broadcast
////        // when message sent and when delivered, or set to null.
////        PendingIntent sentIntent = null, deliveryIntent = null;
////        // Use SmsManager.
////        SmsManager smsManager = SmsManager.getDefault();
////        smsManager.sendTextMessage
////                (destinationAddress, scAddress, smsMessage,
////                        sentIntent, deliveryIntent);
//    }
//
////    private boolean checkForSmsPermission() {
////        if (ActivityCompat.checkSelfPermission(this,
////                Manifest.permission.SEND_SMS) !=
////                PackageManager.PERMISSION_GRANTED) {
////
////            // Permission not yet granted. Use requestPermissions().
////            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
////            // app-defined int constant. The callback method gets the
////            // result of the request.
////            ActivityCompat.requestPermissions(this,
////                    new String[]{Manifest.permission.SEND_SMS},
////                    MY_PERMISSIONS_REQUEST_SEND_SMS);
////            return false;
////        } else {
////            return true;
////        }
////    }
//
////    public void onRequestPermissionsResult(int requestCode,
////                                           String permissions[], int[] grantResults) {
////        switch (requestCode) {
////            case 1: {
////
////                // If request is cancelled, the result arrays are empty.
////                if (grantResults.length > 0
////                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////
////                    // permission was granted, yay! Do the
////                    // contacts-related task you need to do.
////                } else {
////                    // permission denied, boo! Disable the
////                    // functionality that depends on this permission.
////                    Toast.makeText(MainActivity.this, "Το μήνυμα δε μπορεί να σταλεί δίχως την άδεια για το τη χρήση SMS.", Toast.LENGTH_SHORT).show();
////                }
////                return;
////            }
////
////            // other 'case' lines to check for other
////            // permissions this app might request
////        }
////    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.bottom_menu, menu);
//        return true;
//    }
//
//    public void showDialog() {
//        // custom dialog
//        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light_NoTitleBar);
//        dialog.setContentView(R.layout.info_layout);
//        Button dialogButton = (Button) dialog.findViewById(R.id.info_close_button);
//        dialog.show();
//
//
////        TextView t2 = dialog.findViewById(R.id.textView17);
////        t2.setMovementMethod(LinkMovementMethod.getInstance());
//
//        // if button is clicked, close the custom dialog
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.dismiss();
//            }
//        });
//
//
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //Toast.makeText(MainActivity.this, "ASDAFA", Toast.LENGTH_SHORT).show();
//        showDialog();
//        return true;
//    }

}
