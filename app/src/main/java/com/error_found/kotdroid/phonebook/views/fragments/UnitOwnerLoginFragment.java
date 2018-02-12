package com.error_found.kotdroid.phonebook.views.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;
import com.error_found.kotdroid.phonebook.presenters.UnitOwnerLoginPresenter;
import com.error_found.kotdroid.phonebook.views.interfaces.UnitOwnerLoginView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by user12 on 12/2/18.
 */

public class UnitOwnerLoginFragment extends BaseFragment implements UnitOwnerLoginView{
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_device_type)
    EditText etDeviceType;
    @BindView(R.id.btn_login)
    Button btrnLogin;
    @BindView(R.id.pb_progress_bar)
    public ProgressBar pbProgressBar;
    UnitOwnerLoginPresenter ownerLoginPresenter;
    private ProgressDialog progressDialog;


    @Override
    public void nameError(String err) {

    }

    @OnClick(R.id.btn_login)
    public void loginClick()
    {

        ownerLoginPresenter.login(etEmail.getText().toString().trim(),
                etPassword.getText().toString().trim(),
                etDeviceType.getText().toString().trim());

        progressDialog=ProgressDialog.show(getActivityContext(),null,
                "logging in",false);

    }
    @Override
    public void contactError(String err) {

    }

    @Override
    public void sessionIdErr() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_unit_owner_login;
    }

    @Override
    public void init() {
        ownerLoginPresenter=new UnitOwnerLoginPresenter(this);
    }

    @Override
    public void emailErr() {
        progressDialog.dismiss();
        showMessage(0,"Email is not valid",false);
    }

    @Override
    public void deviceTypeErr() {
        progressDialog.dismiss();
        showMessage(0,"device type must be android/iphone",false);
    }

    @Override
    public void passwordErr() {
        progressDialog.dismiss();
    }

    @Override
    public void loginSuccess(PojoUserLogin pojoUserLogin) {

        progressDialog.dismiss();
        //Toast.makeText(getActivityContext(), pojoUserLogin.session_id, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivityContext(), pojoUserLogin.profile.owner_name, Toast.LENGTH_SHORT).show();

        Bundle bundle=new Bundle();
        bundle.putString("session_id",pojoUserLogin.session_id);
        AddGuestFragment fragment=new AddGuestFragment();
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(R.id.fl,fragment).commit();

    }


}
