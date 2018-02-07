package com.error_found.kotdroid.phonebook.presenters;

import com.error_found.kotdroid.phonebook.models.pojos.ContactModel;

import java.util.List;

/**
 * Created by user12 on 7/2/18.
 */

public interface DatabaseCallBack {
    void fetchedAll(List<ContactModel> list);
    void fetchedLast(ContactModel model);
    void onSaved();
    void onFailure();
}
