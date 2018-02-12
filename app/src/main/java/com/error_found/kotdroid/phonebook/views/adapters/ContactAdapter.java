package com.error_found.kotdroid.phonebook.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.pojos.ContactModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user12 on 6/2/18.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ContactModel> contactModelList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
        contactModelList = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void addContact(ContactModel model) {
        contactModelList.add(model);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactHolder(mLayoutInflater.inflate(R.layout.contact_row_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContactHolder) {
            ((ContactHolder) holder).tvContact.setText(contactModelList.get(position)._contact);
            ((ContactHolder) holder).tvName.setText(contactModelList.get(position)._name);
            String filePath = contactModelList.get(position)._profilePath;
            ((ContactHolder) holder).sdvProfile.setImageURI(Uri.fromFile(new File(filePath)));
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256));
            ((ContactHolder) holder).cardView.setCardBackgroundColor(color);


        }
    }

    @Override
    public int getItemCount() {
        return contactModelList.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_contact)
        TextView tvContact;
        @BindView(R.id.cardview)
        CardView cardView;
        @BindView(R.id.tv_name1)
        TextView tvName;
        @BindView(R.id.sdv_profile)
        SimpleDraweeView sdvProfile;


        public ContactHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
