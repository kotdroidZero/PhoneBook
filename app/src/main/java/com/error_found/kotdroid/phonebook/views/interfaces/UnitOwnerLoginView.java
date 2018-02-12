package com.error_found.kotdroid.phonebook.views.interfaces;

import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;

/**
 * Created by user12 on 12/2/18.
 */

public interface UnitOwnerLoginView extends BaseView {
    void emailErr();

    void deviceTypeErr();

    void passwordErr();

    void loginSuccess(PojoUserLogin pojoUserLogin);


}
