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
import com.error_found.kotdroid.phonebook.models.pojos.PojoUserLogin;
import com.error_found.kotdroid.phonebook.views.fragments.AllGuestsFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user12 on 12/2/18.
 */

public class AllGuestsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PojoUserLogin> guestsList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean toBeMoreLoad;
    private static final int LOADER = 200;
    private static final int ITEM = 100;
    private AllGuestsFragment mAllGuestFragment;

    public AllGuestsAdapter(Context mContext, AllGuestsFragment fragment) {
        this.mContext = mContext;
        mAllGuestFragment = fragment;
        mLayoutInflater = LayoutInflater.from(mContext);
        guestsList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM:
                new GuestsHolder(mLayoutInflater.inflate(R.layout.all_guest_row_item,
                        parent, false));
            case LOADER:
                new GuestsHolder(mLayoutInflater.inflate(R.layout.loader_footer,
                        parent, false));
            default:
                return null;
        }
    }

    public void updateList(PojoUserLogin userLogin) {
        guestsList.add(userLogin);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int type = getItemViewType(position);
        if (type == ITEM) {

            ((GuestsHolder) holder).sdvProfile.setImageURI(Uri.parse
                    (guestsList.get(position).image));
            ((GuestsHolder) holder).tvGender.setText(guestsList.get(position).gender);
            ((GuestsHolder) holder).tvGuestId.setText(String.valueOf(guestsList.get(position).guest_id));
            ((GuestsHolder) holder).tvName1.setText(guestsList.get(position).name);

        } else if (type == LOADER) {

            if (toBeMoreLoad) {
                ((FooterHolder) holder).pbLoading.setVisibility(View.VISIBLE);
                mAllGuestFragment.loadMore();
                setLoadMore(true);
            } else {
                ((FooterHolder) holder).pbLoading.setVisibility(View.GONE);
                ((FooterHolder) holder).tvDone.setVisibility(View.VISIBLE);
            }

        }


    }

    public void setLoadMore(boolean isMoreToLoad) {
        toBeMoreLoad = isMoreToLoad;
    }

    @Override
    public int getItemCount() {
        return guestsList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (guestsList.size() == position) {
            return LOADER;
        }
        return ITEM;
    }

    public class GuestsHolder extends RecyclerView.ViewHolder {
        TextView tvName1;
        @BindView(R.id.tv_gender)
        TextView tvGender;
        @BindView(R.id.tv_guest_id)
        TextView tvGuestId;
        @BindView(R.id.sdv_profile)
        SimpleDraweeView sdvProfile;

        private GuestsHolder(View itemView) {
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
