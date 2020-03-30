package com.SuncityDevs.covid19sms.Greece;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.SuncityDevs.covid19sms.Greece.Info_UI;
import com.SuncityDevs.covid19sms.Greece.MainActivity;

public class GreeceAdapter extends FragmentPagerAdapter {

    public GreeceAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Info_UI();
            case 1:
                return new MainActivity();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
