package com.error_found.kotdroid.phonebook.views.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.pojos.PojoLogin;
import com.error_found.kotdroid.phonebook.views.fragments.AllPostFragment;
import com.error_found.kotdroid.phonebook.views.interfaces.LoadMoreListener;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user12 on 13/2/18.
 */

public class AllPostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    List<PojoLogin> pojoLoginList;
    LayoutInflater mLayoutInflater;
    boolean hasMorePosts;
    private static final int LOADER = 200;
    private static final int ITEM = 100;
    private AllPostFragment allPostFragment;
    LoadMoreListener loadMoreListener;

    public AllPostAdapter(Context mContext, AllPostFragment allPostFragment) {
        this.mContext = mContext;
        this.allPostFragment = allPostFragment;
        mLayoutInflater = LayoutInflater.from(mContext);
        pojoLoginList = new ArrayList<>();
        loadMoreListener = allPostFragment;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM:
                return new PostHolder(mLayoutInflater.inflate(R.layout.all_guest_row_item,
                        parent, false));
            case LOADER:
                return new FooterHolder(mLayoutInflater.inflate(R.layout.loader_footer,
                        parent, false));
            default:
                return new PostHolder(mLayoutInflater.inflate(R.layout.all_guest_row_item,
                        parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == ITEM) {
            PostHolder postHolder = (PostHolder) holder;

            postHolder.sdvProfile.setImageURI(Uri.parse
                    (pojoLoginList.get(position).user_image));
            postHolder.tvGender.setText(pojoLoginList.get(position)
                    .post_time);
            postHolder.tvGuestId.setText(
                    String.valueOf(pojoLoginList.get(position).user_id));
            postHolder.tvName1.setText(pojoLoginList.get(position)
                    .user_name);


        } else if (type == LOADER) {
            FooterHolder footerHolder = (FooterHolder) holder;
            if (loadMoreListener != null && hasMorePosts) {
                footerHolder.pbLoading.setVisibility(View.VISIBLE);
                footerHolder.tvDone.setVisibility(View.GONE);
                loadMoreListener.loadMore();
            } else {
                footerHolder.pbLoading.setVisibility(View.GONE);
                footerHolder.tvDone.setVisibility(View.VISIBLE);
                footerHolder.tvDone.setText("all post loaded");
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (AllPostFragment.POST_PAGE_LIMIT <= pojoLoginList.size() && pojoLoginList.size() ==
                position) {
            return LOADER;
        } else {
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return AllPostFragment.POST_PAGE_LIMIT <= pojoLoginList.size() ?
                pojoLoginList.size() + 1 : pojoLoginList.size();
    }

    public void updateList(List<PojoLogin> loginList, int page) {

        if (1 == page) {
            pojoLoginList.clear();
        }
        pojoLoginList.addAll(loginList);
        notifyDataSetChanged();
        hasMorePosts = AllPostFragment.POST_PAGE_LIMIT <= loginList.size();
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        TextView tvName1;
        //@BindView(R.id.tv_gender)
        TextView tvGender;
        //@BindView(R.id.tv_guest_id)
        TextView tvGuestId;
        //@BindView(R.id.sdv_profile)
        SimpleDraweeView sdvProfile;

        private PostHolder(View itemView) {
            super(itemView);
            tvName1 = itemView.findViewById(R.id.tv_name1);
            tvGender = itemView.findViewById(R.id.tv_gender);
            tvGuestId = itemView.findViewById(R.id.tv_guest_id);
            sdvProfile = itemView.findViewById(R.id.sdv_profile);
            // ButterKnife.bind(this, itemView);
        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pb_loading)
        ProgressBar pbLoading;
        @BindView(R.id.tv_footer_done)
        TextView tvDone;

        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
