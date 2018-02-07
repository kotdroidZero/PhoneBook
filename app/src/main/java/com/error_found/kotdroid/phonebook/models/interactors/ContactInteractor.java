package com.error_found.kotdroid.phonebook.models.interactors;

import android.content.Context;

import com.error_found.kotdroid.phonebook.models.database.ContactDatabase;
import com.error_found.kotdroid.phonebook.models.pojos.ContactModel;
import com.error_found.kotdroid.phonebook.presenters.DatabaseCallBack;

import java.util.List;

/**
 * Created by user12 on 7/2/18.
 */

public class ContactInteractor {

    public void getContacts(Context context, DatabaseCallBack databaseCallBack) {
        ContactDatabase database = new ContactDatabase(context);
        List<ContactModel> list = database.getAllContacts();

        if (list.isEmpty()) {
            databaseCallBack.onFailure();
        } else {
            databaseCallBack.fetchedAll(list);

        }
    }

    public void getLastContact(Context context,DatabaseCallBack databaseCallBack)
    {
        ContactDatabase database = new ContactDatabase(context);
        ContactModel model = database.getLastUpdatedContact();

        if (model!=null)
        {
            databaseCallBack.fetchedLast(model);
        }

    }
}
