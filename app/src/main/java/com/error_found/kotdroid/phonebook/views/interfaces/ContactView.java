package com.error_found.kotdroid.phonebook.views.interfaces;

import com.error_found.kotdroid.phonebook.models.pojos.ContactModel;

import java.util.List;

/**
 * Created by user12 on 7/2/18.
 */

public interface ContactView extends BaseView{
    void contactFetched(List<ContactModel> list);
    void getLastContact(ContactModel model);

}
