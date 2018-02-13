package com.error_found.kotdroid.phonebook.models.interactors;

import com.error_found.kotdroid.phonebook.models.networkrequest.API;
import com.error_found.kotdroid.phonebook.models.networkrequest.NetworkRequestCallbacks;
import com.error_found.kotdroid.phonebook.models.networkrequest.RestClient;
import com.error_found.kotdroid.phonebook.models.pojos.PojoLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user12 on 13/2/18.
 */

public class AllPostInteractors {

    public void getAllPost(String sessionId, String postType, String filter, String locale,
                           String limit, String page, final NetworkRequestCallbacks callbacks)
    {
        API api= RestClient.get();
        Call<List<PojoLogin>> call=api.getPost(sessionId,postType,filter,locale,limit,page);

        call.enqueue(new Callback<List<PojoLogin>>() {
            @Override
            public void onResponse(Call<List<PojoLogin>> call, Response<List<PojoLogin>> response) {
                if (response.isSuccessful())
                {
                    callbacks.onSuccess(response);
                }

            }

            @Override
            public void onFailure(Call<List<PojoLogin>> call, Throwable t) {
                callbacks.onError(t);
            }
        });
    }
}
