package com.error_found.kotdroid.phonebook.views.fragments;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.presenters.CreateContactPresenter;
import com.error_found.kotdroid.phonebook.utils.Constants;
import com.error_found.kotdroid.phonebook.utils.MarshMallowPermissionsMine;
import com.error_found.kotdroid.phonebook.views.activities.ContactActivity;
import com.error_found.kotdroid.phonebook.views.interfaces.CreateContactView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by user12 on 7/2/18.
 */

public class CreateContactFragment extends BasePictureOptionsFragment implements CreateContactView {

    private static final int CAM = 2500;
    private static final int GAL = 2000;
    private String picturePath, imagesDirectory = "PhoneBook";


    public static final int REQUEST_READ_STORAGE_PERMISSSION = 100;
    public static final int REQUEST_WRITE_STORAGE_PERMISSSION = 200;
    public static final int REQUEST_CAMERA_PERMISSSION = 300;

    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.et_name)
    TextView etName;
    @BindView(R.id.et_contact)
    TextView etContact;
    @BindView(R.id.sdv_choose_photo)
    SimpleDraweeView sdvChoosePhoto;
    CreateContactPresenter createContactPresenter;
    private MarshMallowPermissionsMine marshMallowPermissions;

    private File userImageFile = null;

    @Override
    public int getLayoutId() {

        return R.layout.fragment_create_contact;
    }

    @OnClick(R.id.tv_save)
    public void onSaveContact() {
        createContactPresenter.saveContact(etName.getText().toString().trim(),
                userImageFile.getAbsolutePath(),
                etContact.getText().toString().trim());
    }

    @Override
    public void nameError(String err) {
        etName.setError(err);
    }

    @Override
    public void contactError(String err) {
        etContact.setError(err);
    }

    @Override
    public void sessionIdErr() {

    }

    @OnClick(R.id.iv_cancel)
    public void onCancleClick() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }


    @Override
    public void contactSaved() {

        //need to check doubt hai. can we access one View's presenter to another one
        ((ContactActivity) getActivityContext()).contactPresenter.getLastContact();

        getFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void errorInSaving() {

    }

    //onclick methods
    @OnClick(R.id.sdv_choose_photo)
    public void choosePhoto() {
        openPhotosOption();
    }

    private void openPhotosOption() {
        showPictureOptionsDialog(Constants.LOCAL_STORAGE_BASE_PATH_FOR_USER_PHOTOS);
    }


    @Override
    public void setData() {
        createContactPresenter = new CreateContactPresenter
                (this);
        marshMallowPermissions = new MarshMallowPermissionsMine(getActivity());
    }

    @Override
    public void onGettingImageFile(File file) {
        if (null != userImageFile) {
            userImageFile.delete();
        }
        sdvChoosePhoto.setImageURI(Uri.fromFile(file));
        userImageFile = file;
        Log.e("file", file.getAbsolutePath());
    }
}
