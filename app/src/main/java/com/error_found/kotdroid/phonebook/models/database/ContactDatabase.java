package com.error_found.kotdroid.phonebook.models.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.error_found.kotdroid.phonebook.models.pojos.ContactModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by user12 on 6/2/18.
 */

public class ContactDatabase extends SQLiteOpenHelper {

    //fields
    public static final String DATABASE_NAME = "CONTACTS_DB";
    public static final String TABLE_NAME = "Contact_table";
    public static final String NAME = "Name";
    public static final String IMAGE_PATH = "Image_Path";
    public static final String UID = "Uid";
    public static final int DATABASE_VERSION = 1;
    public static final String CONTACT = "Contact";

    //queries
    public static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "( "
            + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + NAME + " TEXT, "
            + IMAGE_PATH + " TEXT, "
            + CONTACT + " TEXT );";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public ContactDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //custom methods according to need
    public long insertContact(ContactModel contactModel) {
        SQLiteDatabase dbWrite = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, contactModel._name);
        contentValues.put(IMAGE_PATH, contactModel._profilePath);
        contentValues.put(CONTACT, contactModel._contact);
        long id = dbWrite.insert(TABLE_NAME, null, contentValues);
        return id;
    }

    public List<ContactModel> getAllContacts() {
        String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase dbRead = this.getReadableDatabase();
        Cursor cursor = dbRead.rawQuery(SELECT_QUERY, null);
        cursor.moveToFirst();
        List<ContactModel> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            String contact = cursor.getString(cursor.getColumnIndex(CONTACT));
            String profilePath = cursor.getString(cursor.getColumnIndex(IMAGE_PATH));
            ContactModel contactModel = new ContactModel(name, contact, profilePath);
            list.add(contactModel);
        }
        Collections.sort(list, new Comparator<ContactModel>() {
            @Override
            public int compare(ContactModel model, ContactModel t1) {
                return model._name.compareTo(t1._name);
            }
        });
        return list;
    }

    public ContactModel getLastUpdatedContact() {
        String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase dbRead = this.getReadableDatabase();
        Cursor cursor = dbRead.rawQuery(SELECT_QUERY, null);
        cursor.moveToLast();
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        String contact = cursor.getString(cursor.getColumnIndex(CONTACT));
        String profilePath = cursor.getString(cursor.getColumnIndex(IMAGE_PATH));
        ContactModel contactModel = new ContactModel(name, contact, profilePath);
        return contactModel;
    }
}
