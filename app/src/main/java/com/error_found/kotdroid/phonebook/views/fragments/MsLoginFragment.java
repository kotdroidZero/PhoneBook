package com.error_found.kotdroid.phonebook.views.fragments;

import android.os.Bundle;
import android.widget.EditText;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.pojos.PojoLogin;
import com.error_found.kotdroid.phonebook.presenters.MsLoginPresenter;
import com.error_found.kotdroid.phonebook.views.interfaces.MsLoginView;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Response;

/**
 * Created by user12 on 13/2/18.
 */

public class MsLoginFragment extends BaseFragment implements MsLoginView{

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    MsLoginPresenter msLoginPresenter;


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
    public int getLayoutId() {
        return R.layout.fragment_ms_login;
    }

    @Override
    public void init() {
        msLoginPresenter=new MsLoginPresenter(this);
    }

    @OnClick(R.id.btn_login)
    public void loginToNetwork()
    {
        //presenter codes
        msLoginPresenter.loginViaInteractor(etEmail.getText().toString().trim(),
                etPassword.getText().toString().trim(),
                "android","457861","12234541","en");

    }

    @Override
    public void loginSuccess(Response<?> response) {
        //open home fragment here
        MsHomeFragment msHomeFragment=new MsHomeFragment();
        PojoLogin pojoLogin= (PojoLogin) response.body();
        Bundle bundle=new Bundle();
        bundle.putParcelable("pojoLogin",pojoLogin);
        msHomeFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fl,msHomeFragment).commit();
    }

    @Override
    public void loginFailed(Throwable t) {
        showMessage(0,t.getMessage(),false);
    }
}
