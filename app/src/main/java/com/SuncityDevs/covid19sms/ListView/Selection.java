package com.SuncityDevs.covid19sms.ListView;

public class Selection {
    private int imageID;
    private String textID;

    public Selection(int imageID, String textID) {
        this.imageID = imageID;
        this.textID = textID;
    }

    public int getImageID() {
        return imageID;
    }

    public String getTextID() {
        return textID;
    }

    public boolean hasImage(){
        if(imageID != -1)
            return true;

        return false;
    }

    @Override
    public String toString() {
        return "Selection{" +
                "imageID=" + imageID +
                ", textID='" + textID + '\'' +
                '}';
    }
}
