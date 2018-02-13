package com.error_found.kotdroid.phonebook.views.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.models.pojos.PojoLogin;
import com.error_found.kotdroid.phonebook.views.interfaces.BaseView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;

/**
 * Created by user12 on 13/2/18.
 */

public class MsHomeFragment extends BaseFragment implements BaseView {
    @BindViews({R.id.tv_name,R.id.tv_nick_name,R.id.tv_user_name,R.id.tv_type})
    List<TextView> textViewList=new ArrayList<>();
    @BindViews({R.id.sdv_profile,R.id.sdv_qr_code})
    List<SimpleDraweeView> sdvList=new ArrayList<>();



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
        return R.layout.fragment_ms_home;
    }

    @Override
    public void init() {
        Bundle bundle=getArguments();
        PojoLogin pojoLogin=bundle.getParcelable("pojoLogin");
        textViewList.get(0).setText(pojoLogin.profile.owner_name);
        textViewList.get(1).setText(pojoLogin.profile.profile_info.nick_name);
        textViewList.get(2).setText(pojoLogin.user_name);
        textViewList.get(3).setText(pojoLogin.profile.profile_mynd.myndside_type_3);
//        sdvList.get(0).setImageURI(Uri.parse(pojoLogin.profile.owner_image));
        sdvList.get(1).setImageURI(Uri.parse(pojoLogin.profile.qr_code));

    }
}
