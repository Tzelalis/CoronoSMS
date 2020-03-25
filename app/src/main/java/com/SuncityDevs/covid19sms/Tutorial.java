package com.SuncityDevs.covid19sms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tutorial extends AppCompatActivity {

    public static String first_time = "0";
    private SharedPreferences sharedPref;
    private String flag = "com.example.covid19sms.flag";
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = this.getSharedPreferences(
                "com.example.covid19sms", Context.MODE_PRIVATE);

        Log.v("covid19",  first_time);

        if ( sharedPref.getString(flag, "").equals("1")){
            Intent intent = new Intent(this,Info_UI.class );
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_tutorial);

            viewPager = findViewById(R.id.viewPager);

            IntroAdapter adapter = new IntroAdapter(getSupportFragmentManager());
            viewPager.setAdapter(adapter);
        }
    }


    public void once_in_a_life_time(){

        sharedPref.edit().putString(flag, "1").apply();
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Light_NoTitleBar);
        dialog.setContentView(R.layout.privacy_policy_layout);
        Button dialogButton = (Button) dialog.findViewById(R.id.privacy_policy_button);
        dialog.show();
        Intent intent = new Intent(getApplicationContext(),Info_UI.class );


        String privacy_policy_text = "<h2>Privacy Policy</h2> <p>\n" +
                "                    Suncity Devs built the CoronoSMS app as\n" +
                "                    a Free app. This SERVICE is provided by\n" +
                "                    Suncity Devs at no cost and is intended for\n" +
                "                    use as is.\n" +
                "                  </p> <p>\n" +
                "                    This page is used to inform visitors regarding\n" +
                "                    my policies with the collection, use, and\n" +
                "                    disclosure of Personal Information if anyone decided to use\n" +
                "                    my Service.\n" +
                "                  </p> <p>\n" +
                "                    If you choose to use my Service, then you agree\n" +
                "                    to the collection and use of information in relation to this\n" +
                "                    policy. The Personal Information that I collect is\n" +
                "                    used for providing and improving the Service.\n" +
                "                    I will not use or share your\n" +
                "                    information with anyone except as described in this Privacy\n" +
                "                    Policy.\n" +
                "                  </p> <p>\n" +
                "                    The terms used in this Privacy Policy have the same meanings\n" +
                "                    as in our Terms and Conditions, which is accessible at\n" +
                "                    CoronoSMS unless otherwise defined in this Privacy\n" +
                "                    Policy.\n" +
                "                  </p> <p><strong>Information Collection and Use</strong></p> <p>\n" +
                "                    For a better experience, while using our Service,\n" +
                "                    I may require you to provide us with certain\n" +
                "                    personally identifiable information, including but not limited to firstname, lastname, street address. The\n" +
                "                    information that I request will be\n" +
                "                    retained on your device and is not collected by me in any way.\n" +
                "                  </p><p>These data are sent only to the emergency number 13033. This service is provided to the Greek citizens in order to send a free telecommunication message (SMS) directly from the interested person's cell phone whenever the citizen wants to leave his home. </p><p>\n" +
                "                    These data are only sent to special number 13033, the service provided to the citizen to send a free\n" +
                "                    telecommunication message (sms) directly from the interested person's cell phone whenever the citizen leaves his home.\n" +
                "                  </p><p>\n" +
                "                    The app does use third party services that may collect\n" +
                "                    information used to identify you.\n" +
                "                  </p> <div><p>\n" +
                "                      Link to privacy policy of third party service providers\n" +
                "                      used by the app\n" +
                "                    </p> <ul><li><a href=\"https://www.google.com/policies/privacy/\" target=\"_blank\">Google Play Services</a></li><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----><!----></ul></div> <p><strong>Log Data</strong></p> <p>\n" +
                "                    I want to inform you that whenever\n" +
                "                    you use my Service, in a case of an error in the\n" +
                "                    app I collect data and information (through third\n" +
                "                    party products) on your phone called Log Data. This Log Data\n" +
                "                    may include information such as your device Internet\n" +
                "                    Protocol (“IP”) address, device name, operating system\n" +
                "                    version, the configuration of the app when utilizing\n" +
                "                    my Service, the time and date of your use of the\n" +
                "                    Service, and other statistics.\n" +
                "                  </p> <p><strong>Cookies</strong></p> <p>\n" +
                "                    Cookies are files with a small amount of data that are\n" +
                "                    commonly used as anonymous unique identifiers. These are\n" +
                "                    sent to your browser from the websites that you visit and\n" +
                "                    are stored on your device's internal memory.\n" +
                "                  </p> <p>\n" +
                "                    This Service does not use these “cookies” explicitly.\n" +
                "                    However, the app may use third party code and libraries that\n" +
                "                    use “cookies” to collect information and improve their\n" +
                "                    services. You have the option to either accept or refuse\n" +
                "                    these cookies and know when a cookie is being sent to your\n" +
                "                    device. If you choose to refuse our cookies, you may not be\n" +
                "                    able to use some portions of this Service.\n" +
                "                  </p> <p><strong>Service Providers</strong></p> <p>\n" +
                "                    I may employ third-party companies\n" +
                "                    and individuals due to the following reasons:\n" +
                "                  </p> <ul><li>To facilitate our Service;</li> <li>To provide the Service on our behalf;</li> <li>To perform Service-related services; or</li> <li>To assist us in analyzing how our Service is used.</li></ul> <p>\n" +
                "                    I want to inform users of this\n" +
                "                    Service that these third parties have access to your\n" +
                "                    Personal Information. The reason is to perform the tasks\n" +
                "                    assigned to them on our behalf. However, they are obligated\n" +
                "                    not to disclose or use the information for any other\n" +
                "                    purpose.\n" +
                "                  </p> <p><strong>Security</strong></p> <p>\n" +
                "                    I value your trust in providing us\n" +
                "                    your Personal Information, thus we are striving to use\n" +
                "                    commercially acceptable means of protecting it. But remember\n" +
                "                    that no method of transmission over the internet, or method\n" +
                "                    of electronic storage is 100% secure and reliable, and\n" +
                "                    I cannot guarantee its absolute security.\n" +
                "                  </p> <p><strong>Links to Other Sites</strong></p> <p>\n" +
                "                    This Service may contain links to other sites. If you click\n" +
                "                    on a third-party link, you will be directed to that site.\n" +
                "                    Note that these external sites are not operated by\n" +
                "                    me. Therefore, I strongly advise you to\n" +
                "                    review the Privacy Policy of these websites.\n" +
                "                    I have no control over and assume no\n" +
                "                    responsibility for the content, privacy policies, or\n" +
                "                    practices of any third-party sites or services.\n" +
                "                  </p> <p><strong>Children’s Privacy</strong></p> <p>\n" +
                "                    These Services do not address anyone under the age of 13.\n" +
                "                    I do not knowingly collect personally\n" +
                "                    identifiable information from children under 13. In the case\n" +
                "                    I discover that a child under 13 has provided\n" +
                "                    me with personal information,\n" +
                "                    I immediately delete this from our servers. If you\n" +
                "                    are a parent or guardian and you are aware that your child\n" +
                "                    has provided us with personal information, please contact\n" +
                "                    me so that I will be able to do\n" +
                "                    necessary actions.\n" +
                "                  </p> <p><strong>Changes to This Privacy Policy</strong></p> <p>\n" +
                "                    I may update our Privacy Policy from\n" +
                "                    time to time. Thus, you are advised to review this page\n" +
                "                    periodically for any changes. I will\n" +
                "                    notify you of any changes by posting the new Privacy Policy\n" +
                "                    on this page. These changes are effective immediately after\n" +
                "                    they are posted on this page.\n" +
                "                  </p> <p><strong>Contact Us</strong></p> <p>\n" +
                "                    If you have any questions or suggestions about\n" +
                "                    my Privacy Policy, do not hesitate to contact\n" +
                "                    me at suncitydevs@gmail.com.\n" +
                "                  </p> <p>\n" +
                "                    This privacy policy page was created at\n" +
                "                    <a href=\"https://privacypolicytemplate.net\" target=\"_blank\">privacypolicytemplate.net</a>\n" +
                "                    and modified/generated by\n" +
                "                    <a href=\"https://app-privacy-policy-generator.firebaseapp.com/\" target=\"_blank\">App Privacy Policy Generator</a></p>";
        TextView privacy_textview = dialog.findViewById(R.id.privacy_policy_textview);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            privacy_textview.setText(Html.fromHtml(privacy_policy_text, Html.FROM_HTML_MODE_COMPACT));
//        } else {
//            privacy_textview.setText(Html.fromHtml(privacy_policy_text));
//        }

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.edit().putString(flag, "1").apply();
                dialog.dismiss();
            }
        });
        startActivity(intent);

    }


}
