package com.hitherejoe.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Srihari S Reddy on 17/11/16.
 */


public class Config implements Parcelable {
    private Navigation[] navigation;

    private String cv;

    private String av;

    public Config() {

    }

    public Navigation[] getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation[] navigation) {
        this.navigation = navigation;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getAv() {
        return av;
    }

    public void setAv(String av) {
        this.av = av;
    }

    @Override
    public String toString() {
        return "Config [navigation = " + navigation.toString() + ", cv = " + cv + ", av = " + av + "]";
    }

    public Config(Parcel in) {
        cv = in.readString();
        av = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cv);
        dest.writeString(av);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Config> CREATOR = new Parcelable.Creator<Config>() {
        @Override
        public Config createFromParcel(Parcel in) {
            return new Config(in);
        }

        @Override
        public Config[] newArray(int size) {
            return new Config[size];
        }
    };
}
