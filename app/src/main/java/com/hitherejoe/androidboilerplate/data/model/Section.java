package com.hitherejoe.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Srihari S Reddy on 17/11/16.
 */

public class Section implements Parcelable {
    private String title;

    private String type;

    private String url;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "Section [title = "+title+", type = "+type+", url = "+url+"]";
    }

    protected Section(Parcel in) {
        title = in.readString();
        type = in.readString();
        url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(type);
        dest.writeString(url);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {
        @Override
        public Section createFromParcel(Parcel in) {
            return new Section(in);
        }

        @Override
        public Section[] newArray(int size) {
            return new Section[size];
        }
    };
}
