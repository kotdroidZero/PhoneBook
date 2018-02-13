package com.error_found.kotdroid.phonebook.models.interactors;

import com.error_found.kotdroid.phonebook.models.networkrequest.API;
import com.error_found.kotdroid.phonebook.models.networkrequest.NetworkRequestCallbacks;
import com.error_found.kotdroid.phonebook.models.networkrequest.RestClient;
import com.error_found.kotdroid.phonebook.models.pojos.PojoLogin;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user12 on 13/2/18.
 */

public class MsLoginInteractor {

    public void login(HashMap<String, RequestBody> map,
                      final NetworkRequestCallbacks requestCallbacks) {
        API api = RestClient.get();

        Call<PojoLogin> call = api.loginToMyndside(map);

        call.enqueue(new Callback<PojoLogin>() {
            @Override
            public void onResponse(Call<PojoLogin> call, Response<PojoLogin> response) {
                if (response.isSuccessful()) {
                    requestCallbacks.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<PojoLogin> call, Throwable t) {
                requestCallbacks.onError(t);
            }
        });
    }
}
