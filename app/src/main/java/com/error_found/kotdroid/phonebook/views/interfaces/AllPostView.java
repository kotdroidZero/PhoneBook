package com.error_found.kotdroid.phonebook.views.interfaces;

import com.error_found.kotdroid.phonebook.models.pojos.PojoLogin;

import java.util.List;

/**
 * Created by user12 on 13/2/18.
 */

public interface AllPostView extends BaseView {
    void success(List<PojoLogin> loginList);
    void error();
}
