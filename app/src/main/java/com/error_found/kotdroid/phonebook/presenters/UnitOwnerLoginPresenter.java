package com.error_found.kotdroid.phonebook.presenters;

import com.error_found.kotdroid.phonebook.models.interactors.UnitOwnerLoginInteractor;
import com.error_found.kotdroid.phonebook.models.networkrequest.NetworkRequestCallbacks;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;
import com.error_found.kotdroid.phonebook.views.interfaces.UnitOwnerLoginView;

import retrofit2.Response;

/**
 * Created by user12 on 12/2/18.
 */

public class UnitOwnerLoginPresenter {
    private UnitOwnerLoginView unitOwnerLoginView;
    private UnitOwnerLoginInteractor unitOwnerLoginInteractor;

    public UnitOwnerLoginPresenter(UnitOwnerLoginView unitOwnerLoginView) {
        this.unitOwnerLoginView = unitOwnerLoginView;
        unitOwnerLoginInteractor = new UnitOwnerLoginInteractor();
    }

    public void login(String email, String password, String deviceType) {
        if (email.isEmpty()) {
            unitOwnerLoginView.emailErr();
        } else if (password.isEmpty()) {
            unitOwnerLoginView.passwordErr();
        } else if (!deviceType.equalsIgnoreCase("android") ||
                deviceType.equalsIgnoreCase("iphone")) {
            unitOwnerLoginView.deviceTypeErr();
        } else {
            unitOwnerLoginInteractor.loginToNetwork(email, password, deviceType,
                    new NetworkRequestCallbacks() {
                        @Override
                        public void onSuccess(Response<?> response) {
                            PojoUserLogin userLogin = (PojoUserLogin) response.body();

                            unitOwnerLoginView.loginSuccess(userLogin);

                        }

                        @Override
                        public void onError(Throwable t) {

                        }
                    });
        }
    }
}
