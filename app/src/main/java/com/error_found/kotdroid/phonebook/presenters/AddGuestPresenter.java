package com.error_found.kotdroid.phonebook.presenters;

import com.error_found.kotdroid.phonebook.models.interactors.AddGuestInteractor;
import com.error_found.kotdroid.phonebook.models.networkrequest.NetworkRequestCallbacks;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;
import com.error_found.kotdroid.phonebook.views.interfaces.AddGuestView;

import java.io.File;

import retrofit2.Response;

/**
 * Created by user12 on 12/2/18.
 */

public class AddGuestPresenter {
    private AddGuestView addGuestView;
    private AddGuestInteractor guestInteractor;


    public AddGuestPresenter(AddGuestView addGuestView) {
        this.addGuestView = addGuestView;
        guestInteractor = new AddGuestInteractor();
    }

    public void addGuest(String sessionId, String name, String gender, String status,
                         String timeZone,
                         File imageFile) {
        if (sessionId.isEmpty()) {
            addGuestView.sessionIdErr();
        } else if (name.isEmpty()) {
            addGuestView.nameError("can't be empty");
        } else if (!status.equalsIgnoreCase("2")) {
            addGuestView.statusErr("must be 2 for permanent adding");
        } else if (!timeZone.equalsIgnoreCase("Asia/Kolkata")) {
            addGuestView.timeZoneErr("must be Asia/Kolkata");
        } else {
            guestInteractor.addGuest(sessionId, name, gender, status, timeZone, imageFile,
                    new NetworkRequestCallbacks() {
                        @Override
                        public void onSuccess(Response<?> response) {
                            addGuestView.guestAdded((PojoUserLogin) response.body());
                        }

                        @Override
                        public void onError(Throwable t) {
                            addGuestView.errorInAddind(t);
                        }
                    });
        }

    }

}
