package com.error_found.kotdroid.phonebook.models.interactors;

import android.support.annotation.NonNull;

import com.error_found.kotdroid.phonebook.models.networkrequest.API;
import com.error_found.kotdroid.phonebook.models.networkrequest.NetworkRequestCallbacks;
import com.error_found.kotdroid.phonebook.models.networkrequest.RestClient;
import com.error_found.kotdroid.phonebook.models.networkrequest.RetrofitUtils;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user12 on 12/2/18.
 */

public class AddGuestInteractor {


    public void addGuest(String sessionId, String name, String gender, String status,
                         String timeZone,
                         File imageFile, final NetworkRequestCallbacks requestCallbacks)
    {
        API api= RestClient.get();
        RequestBody sessionId1= RetrofitUtils.stringToRequestBody(sessionId);
        RequestBody name1=RetrofitUtils.stringToRequestBody(name);
        RequestBody gender1=RetrofitUtils.stringToRequestBody(gender);
        RequestBody status1=RetrofitUtils.stringToRequestBody(status);
        RequestBody timeZone1=RetrofitUtils.stringToRequestBody(timeZone);
        MultipartBody.Part image=RetrofitUtils.createPartFromFile("image",imageFile);

       retrofit2.Call<PojoUserLogin> call=api.addGuest(sessionId1,image,name1,gender1,status1,
               timeZone1);
       call.enqueue(new Callback<PojoUserLogin>() {
           @Override
           public void onResponse(@NonNull retrofit2.Call<PojoUserLogin> call,@NonNull Response<PojoUserLogin> response) {
               if (response.isSuccessful())
               {
                   requestCallbacks.onSuccess(response);
               }
           }

           @Override
           public void onFailure(@NonNull retrofit2.Call<PojoUserLogin> call, @NonNull Throwable t) {
               requestCallbacks.onError(t);
           }
       });
    }
}
