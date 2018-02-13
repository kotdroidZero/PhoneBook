package com.error_found.kotdroid.phonebook.presenters;

import android.util.Log;

import com.error_found.kotdroid.phonebook.models.interactors.MsLoginInteractor;
import com.error_found.kotdroid.phonebook.models.networkrequest.NetworkRequestCallbacks;
import com.error_found.kotdroid.phonebook.models.networkrequest.RetrofitUtils;
import com.error_found.kotdroid.phonebook.views.interfaces.MsLoginView;

import java.util.HashMap;

import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Created by user12 on 13/2/18.
 */

public class MsLoginPresenter {

    MsLoginView msLoginView;
    MsLoginInteractor msLoginInteractor;

    public MsLoginPresenter(MsLoginView msLoginView) {
        this.msLoginView = msLoginView;
        msLoginInteractor = new MsLoginInteractor();
    }

    public void loginViaInteractor(String email, String password, String deviceType, String deviceId,
                                   String deviceToken, String locale) {
        HashMap<String, RequestBody> hashMap = new HashMap<>();
        hashMap.put("email", RetrofitUtils.stringToRequestBody(email));
        hashMap.put("password", RetrofitUtils.stringToRequestBody(password));
        hashMap.put("device_type", RetrofitUtils.stringToRequestBody(deviceType));
        hashMap.put("device_id", RetrofitUtils.stringToRequestBody(deviceId));
        hashMap.put("device_token", RetrofitUtils.stringToRequestBody(deviceToken));
        hashMap.put("locale", RetrofitUtils.stringToRequestBody(locale));

        Log.d("HashMap",hashMap.toString());

        msLoginInteractor.login(hashMap, new NetworkRequestCallbacks() {
            @Override
            public void onSuccess(Response<?> response) {
                msLoginView.loginSuccess(response);
            }

            @Override
            public void onError(Throwable t) {
                msLoginView.loginFailed(t);
            }
        });
    }
}
