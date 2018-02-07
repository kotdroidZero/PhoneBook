package com.error_found.kotdroid.phonebook.presenters;

import com.error_found.kotdroid.phonebook.models.interactors.ContactInteractor;
import com.error_found.kotdroid.phonebook.models.pojos.ContactModel;
import com.error_found.kotdroid.phonebook.views.interfaces.ContactView;

import java.util.List;

/**
 * Created by user12 on 7/2/18.
 */

public class ContactPresenter {
    ContactView contactView;
    ContactInteractor contactInteractor;

    public ContactPresenter(ContactView contactView) {

        this.contactView = contactView;
        contactInteractor = new ContactInteractor();
    }

    public void getLastContact() {
        contactInteractor.getLastContact(contactView.getActivityContext(), new DatabaseCallBack() {
            @Override
            public void fetchedAll(List<ContactModel> list) {

            }

            @Override
            public void fetchedLast(ContactModel model) {
                contactView.getLastContact(model);
            }

            @Override
            public void onSaved() {

            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void getAllContacts() {
        contactInteractor.getContacts(contactView.getActivityContext(),
                new DatabaseCallBack() {
                    @Override
                    public void fetchedAll(List<ContactModel> list) {
                        contactView.contactFetched(list);
                    }

                    @Override
                    public void fetchedLast(ContactModel model) {

                    }

                    @Override
                    public void onSaved() {

                    }

                    @Override
                    public void onFailure() {
                        contactView.showMessage(0, "No contact added",
                                true);
                    }
                });
    }
}
