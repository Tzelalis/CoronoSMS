package com.SuncityDevs.covid19sms.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.SuncityDevs.covid19sms.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SelectionAdapter extends ArrayAdapter<Selection> {
    public SelectionAdapter(@NonNull Context context, @NonNull List<Selection> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null)
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.selection_list_item, parent, false);

        Selection currentMessage = (Selection) getItem(position);

        ImageView imageView = listItemView.findViewById(R.id.imageView);
        TextView textView = listItemView.findViewById(R.id.textView);

        if(currentMessage.hasImage()){
            imageView.setImageResource(currentMessage.getImageID());
        }

        textView.setText(currentMessage.getTextID());

        return listItemView;
    }

}
