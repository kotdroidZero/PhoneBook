package com.error_found.kotdroid.phonebook.presenters;

import com.error_found.kotdroid.phonebook.models.interactors.AllPostInteractors;
import com.error_found.kotdroid.phonebook.models.networkrequest.NetworkRequestCallbacks;
import com.error_found.kotdroid.phonebook.models.pojos.PojoLogin;
import com.error_found.kotdroid.phonebook.views.interfaces.AllPostView;

import java.util.List;

import retrofit2.Response;

/**
 * Created by user12 on 13/2/18.
 */

public class AllPostPresenter {
    AllPostView allPostView;
    AllPostInteractors allPostInteractors;


    public AllPostPresenter(AllPostView allPostView) {
        this.allPostView = allPostView;
        allPostInteractors=new AllPostInteractors();
    }

    public void getAllPost(String sessionId, String postType, String filter, String locale,
                           String limit, String page)
    {
        allPostInteractors.getAllPost(sessionId, postType, filter, locale, limit, page,
                new NetworkRequestCallbacks() {
            @Override
            public void onSuccess(Response<?> response) {
                allPostView.success((List<PojoLogin>) response.body());
            }

            @Override
            public void onError(Throwable t) {
                allPostView.error();
            }
        });
    }
}
