package com.error_found.kotdroid.phonebook.presenters;

import com.error_found.kotdroid.phonebook.models.interactors.AllGuestsInteractor;
import com.error_found.kotdroid.phonebook.models.networkrequest.NetworkRequestCallbacks;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;
import com.error_found.kotdroid.phonebook.views.interfaces.AllGuestView;

import java.util.List;

import retrofit2.Response;

/**
 * Created by user12 on 12/2/18.
 */

public class AllGuestsPresenter {
    AllGuestView allGuestView;
    AllGuestsInteractor allGuestsInteractor;

    public AllGuestsPresenter(AllGuestView allGuestView) {
        this.allGuestView = allGuestView;
        allGuestsInteractor=new AllGuestsInteractor();
    }

    public void getAllGuests(String sessionId,String timeZone,String status)
    {
       if (sessionId.isEmpty())
       {
           allGuestView.sessionIdErr();
       }
       else
       {
            allGuestsInteractor.getAllGuests(sessionId, timeZone, status,
                    new NetworkRequestCallbacks() {
                @Override
                public void onSuccess(Response<?> response) {
                    List<PojoUserLogin> list= (List<PojoUserLogin>) response.body();
                    allGuestView.fetchedAllGuests(list);
                }

                @Override
                public void onError(Throwable t) {
                    allGuestView.errorInFetching(t);
                }
            });
       }
    }
}
