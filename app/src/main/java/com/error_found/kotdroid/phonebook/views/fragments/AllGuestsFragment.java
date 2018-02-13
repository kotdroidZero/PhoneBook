package com.error_found.kotdroid.phonebook.views.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;
import com.error_found.kotdroid.phonebook.presenters.AllGuestsPresenter;
import com.error_found.kotdroid.phonebook.views.adapters.AllGuestsAdapter;
import com.error_found.kotdroid.phonebook.views.interfaces.AllGuestView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by user12 on 12/2/18.
 */

public class AllGuestsFragment extends BaseFragment implements AllGuestView {
    String sessionId;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    AllGuestsAdapter guestsAdapter;
    AllGuestsPresenter presenter;
    private ProgressDialog progressDialog;

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
        return R.layout.fragment_all_guests;
    }

    @Override
    public void init() {

        progressDialog = ProgressDialog.show(getActivityContext(), null,
                "loading guests", false);
        presenter = new AllGuestsPresenter(this);
        Bundle bundle = getArguments();
        sessionId = bundle.getString("session_id");
        guestsAdapter = new AllGuestsAdapter(getContext(), this);
        LinearLayoutManager llm = new LinearLayoutManager(getActivityContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(guestsAdapter);
        loadMore();


    }

    public void loadMore() {
        presenter.getAllGuests(sessionId, "Asia/Kolkata", "2");
    }


    @Override
    public void fetchedAllGuests(List<PojoUserLogin> loginList) {
        if (loginList.isEmpty()) {
            guestsAdapter.setLoadMore(false);
        }
        for (PojoUserLogin userLogin : loginList) {
            guestsAdapter.updateList(userLogin);
            guestsAdapter.notifyDataSetChanged();
        }
        progressDialog.dismiss();
    }

    @Override
    public void errorInFetching(Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(getActivityContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
