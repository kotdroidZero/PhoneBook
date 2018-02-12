package com.error_found.kotdroid.phonebook.views.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;
import com.error_found.kotdroid.phonebook.presenters.AddGuestPresenter;
import com.error_found.kotdroid.phonebook.utils.Constants;
import com.error_found.kotdroid.phonebook.views.interfaces.AddGuestView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by user12 on 12/2/18.
 */

public class AddGuestFragment extends BasePictureOptionsFragment implements AddGuestView{
    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_gender)
    EditText etGender;

    @BindView(R.id.sdv_choose_photo)
    SimpleDraweeView sdvChoosePhoto;
    File imageFile;
    AddGuestPresenter presenter;
    private String sessionId;




    @Override
    public void nameError(String err) {

    }

    @OnClick({R.id.sdv_choose_photo,R.id.btn_add_guest,R.id.btn_all_guests})
    public void onClickEVents(View view)
    {
        switch (view.getId())
        {
            case R.id.sdv_choose_photo:
                showPictureOptionsDialog(Constants.LOCAL_STORAGE_BASE_PATH_FOR_USER_PHOTOS);
                break;
            case R.id.btn_add_guest:
                presenter.addGuest(sessionId,etName.getText().toString().trim(),
                        etGender.getText().toString().trim(),
                        "2","Asia/Kolkata",imageFile);
                Log.d("session_id",sessionId);
                break;
            case R.id.btn_all_guests:
                Bundle bundle=new Bundle();
                bundle.putString("session_id",sessionId);
                AllGuestsFragment fragment=new AllGuestsFragment();
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fl,fragment).commit();
                break;
        }
    }

    @Override
    public void contactError(String err) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_add_guest;
    }

    @Override
    public void init() {
        presenter=new AddGuestPresenter(this);
        Bundle bundle=getArguments();
        sessionId=bundle.getString("session_id");
    }

    @Override
    public void setData() {

    }

    @Override
    public void onGettingImageFile(File file) {
        if (null!=file)
        {
            imageFile=file;
            sdvChoosePhoto.setImageURI(Uri.fromFile(imageFile));
        }


    }

    @Override
    public void guestAdded(PojoUserLogin pojoUserLogin) {
        Toast.makeText(getActivityContext(), "guestAddedSuccessfully", Toast.LENGTH_SHORT)
                .show();


    }

    @Override
    public void sessionIdErr() {

    }

    @Override
    public void genderErr() {
        Toast.makeText(getActivityContext(), "female/male", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void timeZoneErr(String err) {

    }

    @Override
    public void imageFileErr(String err) {

    }

    @Override
    public void statusErr(String err) {

    }

    @Override
    public void errorInAddind(Throwable t) {
        Toast.makeText(getActivityContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
