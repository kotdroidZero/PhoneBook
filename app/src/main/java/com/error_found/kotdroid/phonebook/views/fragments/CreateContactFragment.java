package com.error_found.kotdroid.phonebook.views.fragments;

import android.widget.ImageView;
import android.widget.TextView;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.presenters.CreateContactPresenter;
import com.error_found.kotdroid.phonebook.views.activities.ContactActivity;
import com.error_found.kotdroid.phonebook.views.interfaces.CreateContactView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by user12 on 7/2/18.
 */

public class CreateContactFragment extends BaseFragment implements CreateContactView {

    @BindView(R.id.iv_cancel)
    ImageView ivCancel;
    @BindView(R.id.et_name)
    TextView etName;
    @BindView(R.id.et_contact)
    TextView etContact;
    @BindView(R.id.iv_choose_photo)
    ImageView ivChoosePhoto;
    CreateContactPresenter createContactPresenter;

    @Override
    public int getLayoutId() {

        return R.layout.fragment_create_contact;
    }

    @Override
    protected void init() {
        createContactPresenter = new CreateContactPresenter
                (this);
    }

    @OnClick(R.id.tv_save)
    public void onSaveContact() {
        createContactPresenter.saveContact(etName.getText().toString().trim(), " ",

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
    @OnClick(R.id.iv_choose_photo)
    public void choosePhoto() {

    }

}
