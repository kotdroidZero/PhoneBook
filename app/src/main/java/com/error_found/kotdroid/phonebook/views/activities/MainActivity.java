package com.error_found.kotdroid.phonebook.views.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.networkrequest.API;
import com.error_found.kotdroid.phonebook.models.networkrequest.RestClient;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;
import com.error_found.kotdroid.phonebook.views.fragments.UnitOwnerLoginFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @Override
    protected void init() {
        getFragmentManager().beginTransaction().add(R.id.fl,new UnitOwnerLoginFragment())
                .commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }



    @Override
    public void nameError(String err) {

    }

    @Override
    public void contactError(String err) {

    }

    @Override
    public void sessionIdErr() {

    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}
