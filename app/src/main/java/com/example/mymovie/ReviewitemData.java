package com.example.mymovie;

import android.os.Parcel;
import android.os.Parcelable;

public class ReviewitemData implements Parcelable {
    private int resId;
    private float rating;
    private String id, time, review, up;

    public ReviewitemData(int resId, float rating, String id, String time, String review, String up) {
        this.resId = resId;
        this.rating = rating;
        this.id = id;
        this.time = time;
        this.review = review;
        this.up = up;
    }

    public ReviewitemData(Parcel src){
        this.resId = src.readInt();
        this.rating = src.readFloat();
        this.id = src.readString();
        this.time = src.readString();
        this.review = src.readString();
        this.up = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public ReviewitemData createFromParcel(Parcel src){
            return new ReviewitemData(src);
        }

        public ReviewitemData[] newArray(int size){
            return new ReviewitemData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(resId);
        parcel.writeFloat(rating);
        parcel.writeString(id);
        parcel.writeString(time);
        parcel.writeString(review);
        parcel.writeString(up);
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String text) {
        this.review = review;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }
}
