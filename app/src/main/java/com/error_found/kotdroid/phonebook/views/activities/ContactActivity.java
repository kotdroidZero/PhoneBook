package com.error_found.kotdroid.phonebook.views.activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.pojos.ContactModel;
import com.error_found.kotdroid.phonebook.presenters.ContactPresenter;
import com.error_found.kotdroid.phonebook.views.adapters.ContactAdapter;
import com.error_found.kotdroid.phonebook.views.fragments.CreateContactFragment;
import com.error_found.kotdroid.phonebook.views.interfaces.ContactView;

import java.util.List;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.OnClick;

public class ContactActivity extends BaseActivity implements ContactView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    public ContactPresenter contactPresenter;
    ContactAdapter adapter;
    CreateContactFragment contactFragment;

   /* @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_contact);
    }*/

    @Override
    public int getLayoutId() {
        return R.layout.activity_contact;
    }

    @Override
    public void nameError(String err) {

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @OnClick(R.id.tv_add_contact)
    public void onAddContact() {
        getFragmentManager().beginTransaction().add(R.id.fl, contactFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

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


    @Override
    protected void init() {
        contactPresenter = new ContactPresenter(this);
        adapter = new ContactAdapter(this);
        LinearLayoutManager llm = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        contactFragment = new CreateContactFragment();
        contactPresenter.getAllContacts();
    }

    @Override
    public void contactFetched(List<ContactModel> list) {
        for (ContactModel model : list) {
            adapter.addContact(model);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getLastContact(ContactModel model) {
        adapter.addContact(model);
        adapter.notifyDataSetChanged();
    }
}
