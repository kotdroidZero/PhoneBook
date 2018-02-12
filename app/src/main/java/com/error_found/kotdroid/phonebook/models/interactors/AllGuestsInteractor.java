package com.error_found.kotdroid.phonebook.models.interactors;

import com.error_found.kotdroid.phonebook.models.networkrequest.API;
import com.error_found.kotdroid.phonebook.models.networkrequest.NetworkRequestCallbacks;
import com.error_found.kotdroid.phonebook.models.networkrequest.RestClient;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user12 on 12/2/18.
 */

public class AllGuestsInteractor {

    public void getAllGuests(final String sessionId, String timeZone, String status,
                             final NetworkRequestCallbacks requestCallbacks)
    {
        API api= RestClient.get();
        Call<List<PojoUserLogin>> call=api.getAllGuests(sessionId,timeZone,status);
        call.enqueue(new Callback<List<PojoUserLogin>>() {
            @Override
            public void onResponse(Call<List<PojoUserLogin>> call, Response<List<PojoUserLogin>> response) {
                if (response.isSuccessful())
                {
                    requestCallbacks.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<List<PojoUserLogin>> call, Throwable t) {
                requestCallbacks.onError(t);
            }
        });


    }
}
