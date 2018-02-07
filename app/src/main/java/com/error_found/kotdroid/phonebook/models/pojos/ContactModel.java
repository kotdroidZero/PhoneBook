package com.error_found.kotdroid.phonebook.models.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user12 on 6/2/18.
 */

public class ContactModel implements Parcelable {
    public String _name, _contact,_profilePath;


    public ContactModel(String _name, String _contact, String _profilePath) {
        this._name = _name;
        this._contact = _contact;
        this._profilePath = _profilePath;
    }

    protected ContactModel(Parcel in) {
        _name = in.readString();
        _contact = in.readString();
        _profilePath = in.readString();
    }

    public static final Creator<ContactModel> CREATOR = new Creator<ContactModel>() {
        @Override
        public ContactModel createFromParcel(Parcel in) {
            return new ContactModel(in);
        }

        @Override
        public ContactModel[] newArray(int size) {
            return new ContactModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_name);
        parcel.writeString(_contact);
        parcel.writeString(_profilePath);
    }
}
