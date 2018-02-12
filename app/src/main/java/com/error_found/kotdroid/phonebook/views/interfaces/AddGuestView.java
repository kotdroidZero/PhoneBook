package com.error_found.kotdroid.phonebook.views.interfaces;

import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;

/**
 * Created by user12 on 12/2/18.
 */

public interface AddGuestView extends BaseView {
    void guestAdded(PojoUserLogin pojoUserLogin);

    void genderErr();
    void timeZoneErr(String err);
    void imageFileErr(String err);
    void statusErr(String err);
    void errorInAddind(Throwable t);
}
