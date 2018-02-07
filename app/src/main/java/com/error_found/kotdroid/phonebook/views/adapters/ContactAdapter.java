package com.error_found.kotdroid.phonebook.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.pojos.ContactModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user12 on 6/2/18.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ContactModel> list;
    Context context;
    LayoutInflater inflater;

    public ContactAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void addContact(ContactModel model) {
        list.add(model);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactHolder(inflater.inflate(R.layout.contact_row_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContactHolder) {
            ((ContactHolder) holder).tvContact.setText(list.get(position)._contact);
            ((ContactHolder) holder).tvName.setText(list.get(position)._name);
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256));
            ((ContactHolder) holder).cardView.setCardBackgroundColor(color);
            ((ContactHolder) holder).ivProfile.setImageResource(R.mipmap.ic_launcher_round);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_contact)
        TextView tvContact;
        @BindView(R.id.cardview)
        CardView cardView;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_profile)
        ImageView ivProfile;


        public ContactHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
