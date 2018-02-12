package com.error_found.kotdroid.phonebook.views.interfaces;

import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;

import java.util.List;

/**
 * Created by user12 on 12/2/18.
 */

public interface AllGuestView extends BaseView{
    void fetchedAllGuests(List<PojoUserLogin> loginList);
    void errorInFetching(Throwable t);
}
