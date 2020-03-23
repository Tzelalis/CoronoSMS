package com.example.covid19sms;

import android.widget.ImageView;
import android.widget.TextView;

public class Option {
    private String primaryText;
    private int imageSrc;


    public Option(String primaryText, int imageSrc) {
        this.primaryText = primaryText;
        this.imageSrc = imageSrc;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    @Override
    public String toString() {
        return "Option{" +
                "primaryText='" + primaryText + '\'' +
                ", imageSrc=" + imageSrc +
                '}';
    }
}
