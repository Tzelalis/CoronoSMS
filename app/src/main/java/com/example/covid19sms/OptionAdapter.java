package com.example.covid19sms;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OptionAdapter extends ArrayAdapter<Option> {
    public OptionAdapter(@NonNull Context context, @NonNull List<Option> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.option_list_item, parent, false);

        Option currentOption = getItem(position);

        //set text
        TextView titleTextView =listItemView.findViewById(R.id.textView);
        titleTextView.setText(currentOption.getPrimaryText());

        //set icon
        ImageView iconImageView = listItemView.findViewById(R.id.imageView);
        iconImageView.setImageResource(currentOption.getImageSrc());

//        ListView.LayoutParams params =  (ListView.LayoutParams) listItemView.getLayoutParams();
//        params.height = 400;
//        listItemView.setLayoutParams(params);
//        listItemView.requestLayout();

        //set custom height of listItemView
        int customHeight = parent.getHeight() / this.getCount();

        if(pxToDp(customHeight) > 88){
            Log.v("LOG HEIGHT", "HEIGHT: " + parent.getHeight());
            listItemView.getLayoutParams().height = customHeight;
            listItemView.requestLayout();
        }



        return listItemView;
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }


}
