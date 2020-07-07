package com.coresolutions.coreinvent.ui.alta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.coresolutions.coreinvent.R;
import com.coresolutions.coreinvent.ui.login.LoginViewModel;
import com.coresolutions.coreinvent.ui.login.LoginViewModelFactory;

import pl.aprilapps.easyphotopicker.ChooserType;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;

public class AltaActivity extends AppCompatActivity {

    private EasyImage easyImage;
    private SharedViewModel model;


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
        easyImage.openChooser(this);
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
                Bitmap bitmap = BitmapFactory.decodeFile(imageFiles[0].getFile().getAbsolutePath());
                model.setImage(bitmap);

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

}
