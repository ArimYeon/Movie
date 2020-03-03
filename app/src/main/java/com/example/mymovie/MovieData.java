package com.example.mymovie;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieData implements Parcelable {
    private int image;
    private String name, rate;

    public MovieData(int image, String name, String rate) {
        this.image = image;
        this.name = name;
        this.rate = rate;
    }

    public MovieData(Parcel src){
        this.image = src.readInt();
        this.name = src.readString();
        this.rate = src.readString();
    }

    public static final Creator CREATOR = new Creator(){
        public MovieData createFromParcel(Parcel src){
            return new MovieData(src);
        }

        public MovieData[] newArray(int size){
            return new MovieData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(image);
        parcel.writeString(name);
        parcel.writeString(rate);
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
