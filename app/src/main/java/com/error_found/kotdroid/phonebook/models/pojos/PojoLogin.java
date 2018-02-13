package com.error_found.kotdroid.phonebook.models.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by user12 on 13/2/18.
 */

public class PojoLogin implements Parcelable{
    public List<Post_topic_type> post_topic_type = null;
    public Integer user_id;
    public String post_title;
    public String post_description;
    public String post_time;
    public String post_image;
    public Integer is_following;
    public Integer post_is_liked;
    public String user_name;
    public String tint_color;
    public String user_image;
    public String session_id;
    public Profile profile;
    public Integer num_likes;
    public Integer post_id;
    public List<Comment> comments = null;

    protected PojoLogin(Parcel in) {
        if (in.readByte() == 0) {
            user_id = null;
        } else {
            user_id = in.readInt();
        }
        post_title = in.readString();
        post_description = in.readString();
        post_time = in.readString();
        post_image = in.readString();
        if (in.readByte() == 0) {
            is_following = null;
        } else {
            is_following = in.readInt();
        }
        if (in.readByte() == 0) {
            post_is_liked = null;
        } else {
            post_is_liked = in.readInt();
        }
        user_name = in.readString();
        tint_color = in.readString();
        user_image = in.readString();
        session_id = in.readString();
        if (in.readByte() == 0) {
            num_likes = null;
        } else {
            num_likes = in.readInt();
        }
        if (in.readByte() == 0) {
            post_id = null;
        } else {
            post_id = in.readInt();
        }
    }

    public static final Creator<PojoLogin> CREATOR = new Creator<PojoLogin>() {
        @Override
        public PojoLogin createFromParcel(Parcel in) {
            return new PojoLogin(in);
        }

        @Override
        public PojoLogin[] newArray(int size) {
            return new PojoLogin[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (user_id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(user_id);
        }
        parcel.writeString(post_title);
        parcel.writeString(post_description);
        parcel.writeString(post_time);
        parcel.writeString(post_image);
        if (is_following == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(is_following);
        }
        if (post_is_liked == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(post_is_liked);
        }
        parcel.writeString(user_name);
        parcel.writeString(tint_color);
        parcel.writeString(user_image);
        parcel.writeString(session_id);
        if (num_likes == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(num_likes);
        }
        if (post_id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(post_id);
        }
    }
}
