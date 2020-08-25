package com.coresolutions.coreinvent.ui.alta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.ui.login.LoginViewModel;
import com.coresolutions.coreinvent.ui.login.LoginViewModelFactory;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import pl.aprilapps.easyphotopicker.ChooserType;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;

public class AltaActivity extends AppCompatActivity {

    private EasyImage easyImage;
    private SharedViewModel model;

    private static final String PHOTOS_KEY = "easy_image_photos_list";
    private static final int CHOOSER_PERMISSIONS_REQUEST_CODE = 7459;
    private static final int CAMERA_REQUEST_CODE = 7500;
    private static final int CAMERA_VIDEO_REQUEST_CODE = 7501;
    private static final int GALLERY_REQUEST_CODE = 7502;
    private static final int DOCUMENTS_REQUEST_CODE = 7503;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        model = ViewModelProviders.of(this).get(SharedViewModel.class);

        easyImage = new EasyImage.Builder(this)
                .setChooserTitle("Seleccione la imagen del bien")
                .setCopyImagesToPublicGalleryFolder(false)
//                .setChooserType(ChooserType.CAMERA_AND_DOCUMENTS)
                .setChooserType(ChooserType.CAMERA_AND_GALLERY)
                .setFolderName("coreinventImages")
                .allowMultiple(false)
                .build();


    }

    private DescriptionFragment getCurrentVisibleFragment() {
        NavController navController = Navigation.findNavController(AltaActivity.this, R.id.nav_host_fragment);
        Fragment loginFragment = getSupportFragmentManager().findFragmentById(navController.getCurrentDestination().getId());
        if (loginFragment instanceof DescriptionFragment) {
            return (DescriptionFragment) loginFragment;
        }
        return null;
    }

    public void ChooseImage() {
        String[] necessaryPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (arePermissionsGranted(necessaryPermissions)) {
            easyImage.openChooser(AltaActivity.this);
        } else {
            requestPermissionsCompat(necessaryPermissions, CHOOSER_PERMISSIONS_REQUEST_CODE);
        }

    }

    public SharedViewModel getModel() {
        return model;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


//        Fragment fragment = getSupportFragmentManager().findFragmentById("yourFragment");
//        fragment.onActivityResult(requestCode, resultCode, data);
//        if (getCurrentVisibleFragment() != null)
//            getCurrentVisibleFragment().ActivityResult(requestCode, resultCode, data);

//        NavController navController = Navigation.findNavController(AltaActivity.this, R.id.nav_host_fragment);
//        ExampleFragment fragment = (ExampleFragment) getSupportFragmentManager().findFragmentById(R.id.example_fragment);
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().getPrimaryNavigationFragment();
//        FragmentManager fragmentManager = navController.getChildFragmentManager();
//
//        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
//            @Override
//            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
//                Log.e(TAG, "onDestinationChanged: " + destination.getLabel());
//            }
//        });
//
//
        easyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onMediaFilesPicked(MediaFile[] imageFiles, MediaSource source) {
                model.setImage(imageFiles[0].getFile());

            }

            @Override
            public void onImagePickerError(@NonNull Throwable error, @NonNull MediaSource source) {
                //Some error handling
                error.printStackTrace();
            }

            @Override
            public void onCanceled(@NonNull MediaSource source) {
                //Not necessary to remove any files manually anymore
            }
        });
    }

    private void checkGalleryAppAvailability() {
        if (!easyImage.canDeviceHandleGallery()) {
            //Device has no app that handles gallery intent
//            gallery.setVisibility(View.GONE);
        }
    }

    private boolean arePermissionsGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                return false;

        }
        return true;
    }

    private void requestPermissionsCompat(String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CHOOSER_PERMISSIONS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openChooser(this);
        } else if (requestCode == CAMERA_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openCameraForImage(this);
        } else if (requestCode == CAMERA_VIDEO_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openCameraForVideo(this);
        } else if (requestCode == GALLERY_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openGallery(this);
        } else if (requestCode == DOCUMENTS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openDocuments(this);
        }
    }


    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(AltaActivity.this)
                .setTitle("Confirmación")
                .setMessage("¿Realmente desea descartar los cambios?")
                .setBackground(getDrawable(R.color.overBackground))
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
                .setPositiveButton("Descartar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AltaActivity.super.onBackPressed();
                    }
                }).show();
    }
}
