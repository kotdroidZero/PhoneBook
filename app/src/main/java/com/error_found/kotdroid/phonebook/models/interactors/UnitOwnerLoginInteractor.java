package com.error_found.kotdroid.phonebook.models.interactors;

import android.support.annotation.NonNull;

import com.error_found.kotdroid.phonebook.models.networkrequest.API;
import com.error_found.kotdroid.phonebook.models.networkrequest.NetworkRequestCallbacks;
import com.error_found.kotdroid.phonebook.models.networkrequest.RestClient;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user12 on 12/2/18.
 */

public class UnitOwnerLoginInteractor {

    public void loginToNetwork(String email, String password, String deviceType,
                               final NetworkRequestCallbacks networkRequestCallbacks)
    {
        API api= RestClient.get();
        Call<PojoUserLogin> call=api.loginUser(email,password,deviceType);

        call.enqueue(new Callback<PojoUserLogin>() {
            @Override
            public void onResponse(@NonNull Call<PojoUserLogin> call, @NonNull Response<PojoUserLogin> response) {
                if (response.isSuccessful())
                {
                    networkRequestCallbacks.onSuccess(response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PojoUserLogin> call, Throwable t) {
                    networkRequestCallbacks.onError(t);
            }
        });
    }
}
