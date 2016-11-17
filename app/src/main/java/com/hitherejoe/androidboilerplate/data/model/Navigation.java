package com.hitherejoe.androidboilerplate.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Srihari S Reddy on 17/11/16.
 */

public class Navigation implements Parcelable {
    private String title;

    private String menu_icon;

    private String type;

    private Section[] section;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getMenu_icon ()
    {
        return menu_icon;
    }

    public void setMenu_icon (String menu_icon)
    {
        this.menu_icon = menu_icon;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public Section[] getSection ()
    {
        return section;
    }

    public void setSection (Section[] section)
    {
        this.section = section;
    }

    @Override
    public String toString()
    {
        return "Navigation [title = "+title+", menu_icon = "+menu_icon+", type = "+type+", section = "+section.toString()+"]";
    }

    protected Navigation(Parcel in) {
        title = in.readString();
        menu_icon = in.readString();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(menu_icon);
        dest.writeString(type);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Navigation> CREATOR = new Parcelable.Creator<Navigation>() {
        @Override
        public Navigation createFromParcel(Parcel in) {
            return new Navigation(in);
        }

        @Override
        public Navigation[] newArray(int size) {
            return new Navigation[size];
        }
    };
}

