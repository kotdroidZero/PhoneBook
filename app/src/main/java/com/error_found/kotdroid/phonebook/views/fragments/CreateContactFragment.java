package com.error_found.kotdroid.phonebook.views.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.error_found.kotdroid.phonebook.BuildConfig;
import com.error_found.kotdroid.phonebook.R;
import com.error_found.kotdroid.phonebook.presenters.CreateContactPresenter;
import com.error_found.kotdroid.phonebook.utils.GeneralFunctions;
import com.error_found.kotdroid.phonebook.utils.GetSampledImage;
import com.error_found.kotdroid.phonebook.utils.MarshMallowPemisssions;
import com.error_found.kotdroid.phonebook.views.activities.ContactActivity;
import com.error_found.kotdroid.phonebook.views.interfaces.CreateContactView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by user12 on 7/2/18.
 */

public class CreateContactFragment extends BaseFragment implements CreateContactView ,GetSampledImage.SampledImageAsyncResp{

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
    @BindView(R.id.iv_choose_photo)
    ImageView ivChoosePhoto;
    CreateContactPresenter createContactPresenter;
    MarshMallowPemisssions marshMallowPemisssions;

    @Override
    public int getLayoutId() {

        return R.layout.fragment_create_contact;
    }

    @Override
    protected void init() {
        createContactPresenter = new CreateContactPresenter
                (this);
        marshMallowPemisssions = new MarshMallowPemisssions(getActivity());
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
        openPhotosOption();
    }

    private void openPhotosOption() {
        final CharSequence[] items = {
                "Take photo", "Open gallery", "Close"
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivityContext());
        builder.setTitle("Choose from");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        openCamera();
                        break;
                    case 1:
                        openGallery();
                        break;
                    case 2:
                        dialog.dismiss();

                }

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void openGallery() {
        if (marshMallowPemisssions.isPermissionAllowed(Manifest.permission
                .CAMERA, REQUEST_WRITE_STORAGE_PERMISSSION)) {
            //open gallery here
            Intent imagePick = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imagePick, GAL);
        } else {
            marshMallowPemisssions.requestPermission
                    (new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSSION);
        }
    }

    private void openCamera() {
        if (marshMallowPemisssions.isPermissionAllowed(Manifest.permission
                .CAMERA, REQUEST_CAMERA_PERMISSSION)) {
            //open camera here
            startCameraIntent();

        } else {
            marshMallowPemisssions.requestPermission
                    (new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSSION);
        }

    }

    private void startCameraIntent() {
        Intent takePictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = null;
        try {
            file = GeneralFunctions.setUpImageFile(imagesDirectory);
            picturePath = file.getAbsolutePath();

            Uri outputUri = FileProvider.getUriForFile(getActivityContext(),
                    BuildConfig.APPLICATION_ID + ".provider", file);

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ClipData clipData = ClipData.newUri(getActivity().getContentResolver(),
                        "A Photo", outputUri);
                takePictureIntent.setClipData(clipData);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                List<ResolveInfo> resolveInfoList = getActivity().getPackageManager()
                        .queryIntentActivities(takePictureIntent,
                                PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resolveInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    getActivity().grantUriPermission(packageName, outputUri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
            picturePath = null;
        }
        startActivityForResult(takePictureIntent, CAM);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSSION) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && (requestCode == GAL ||
                requestCode == CAM)) {
            boolean isGalleryImage = false;
            if (requestCode == GAL) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
                isGalleryImage = true;
            }

            new GetSampledImage(this).execute(picturePath, imagesDirectory,
                    String.valueOf(isGalleryImage),
                    String.valueOf((int) getResources()
                            .getDimension(R.dimen.image_downsample_size)));
        }

    }

    @Override
    public void onSampledImageAsyncPostExecute(File file) {
        Glide.with(getActivity()).load(file).into(ivChoosePhoto);
    }
}
