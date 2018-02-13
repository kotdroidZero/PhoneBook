package com.error_found.kotdroid.phonebook.views.interfaces;

import retrofit2.Response;

/**
 * Created by user12 on 13/2/18.
 */

public interface MsLoginView extends BaseView {
    void loginSuccess(Response<?> response);

    void loginFailed(Throwable t);
}
