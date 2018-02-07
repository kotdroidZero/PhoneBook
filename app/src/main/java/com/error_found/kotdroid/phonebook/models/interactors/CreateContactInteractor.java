package com.error_found.kotdroid.phonebook.models.interactors;

import android.content.Context;

import com.error_found.kotdroid.phonebook.models.database.ContactDatabase;
import com.error_found.kotdroid.phonebook.models.pojos.ContactModel;
import com.error_found.kotdroid.phonebook.presenters.DatabaseCallBack;

/**
 * Created by user12 on 7/2/18.
 */

public class CreateContactInteractor {


    public void saveContactInDB(ContactModel model, Context context, DatabaseCallBack
            databaseCallBack) {
        ContactDatabase database=new ContactDatabase(context);
        long id=database.insertContact(model);
        if (id>0)
        {
            databaseCallBack.onSaved();
        }

    }
}
