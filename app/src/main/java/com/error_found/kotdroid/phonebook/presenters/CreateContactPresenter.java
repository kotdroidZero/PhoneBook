package com.error_found.kotdroid.phonebook.presenters;

import com.error_found.kotdroid.phonebook.models.interactors.CreateContactInteractor;
import com.error_found.kotdroid.phonebook.models.pojos.ContactModel;
import com.error_found.kotdroid.phonebook.views.interfaces.CreateContactView;

import java.util.List;

/**
 * Created by user12 on 7/2/18.
 */

public class CreateContactPresenter {
    CreateContactView createContactView;
    CreateContactInteractor interactor;

    public CreateContactPresenter(CreateContactView createContactView) {
        this.createContactView = createContactView;
        interactor=new CreateContactInteractor();
    }

    public void saveContact(String name, String profilePath, String contactNumber) {
        if (name.isEmpty()) {
            createContactView.nameError("name can't be empty");
        } else if (contactNumber.isEmpty() || contactNumber.length() < 10) {
            createContactView.contactError("invalid number");
        } else {
            ContactModel model=new ContactModel(name,contactNumber,profilePath);
            interactor.saveContactInDB(model, createContactView.getActivityContext(),
                    new DatabaseCallBack() {
                        @Override
                        public void fetchedAll(List<ContactModel> list) {

                        }

                        @Override
                        public void fetchedLast(ContactModel model) {

                        }

                        @Override
                        public void onSaved() {
                            createContactView.showMessage(0,"contact saved",
                                    false);
                            createContactView.contactSaved();

                        }

                        @Override
                        public void onFailure() {

                        }
                    });
        }
    }
}
