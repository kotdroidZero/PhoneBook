package com.error_found.kotdroid.phonebook.views.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.pojos.PojoLogin;
import com.error_found.kotdroid.phonebook.presenters.AllPostPresenter;
import com.error_found.kotdroid.phonebook.views.adapters.AllPostAdapter;
import com.error_found.kotdroid.phonebook.views.interfaces.AllPostView;
import com.error_found.kotdroid.phonebook.views.interfaces.LoadMoreListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created by user12 on 13/2/18.
 */

public class AllPostFragment extends BaseFragment implements AllPostView, LoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    AllPostAdapter allPostAdapter;
    AllPostPresenter allPostPresenter;
    public static int POST_PAGE_LIMIT = 40;
    int page=1;
    public static final String sessionId = "U9nbWKzRp7jTu2NTIWtLk1VsaGRP2JI1";

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
        return R.layout.fragment_all_post;
    }


    @Override
    public void init() {
        allPostAdapter = new AllPostAdapter(getActivityContext(), this);
        allPostPresenter = new AllPostPresenter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(allPostAdapter);
        allPostPresenter.getAllPost(sessionId, "0", "[1]", "en", POST_PAGE_LIMIT + "",
                page + "");

    }

    @Override
    public void success(List<PojoLogin> loginList) {
        allPostAdapter.updateList(loginList, page);
    }

    @Override
    public void error() {

    }

    @Override
    public void loadMore() {
        page++;
        allPostPresenter.getAllPost(sessionId, "0", "[1]", "en", POST_PAGE_LIMIT + "",
                page + "");

    }
}
