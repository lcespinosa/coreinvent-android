package com.coresolutions.coreinvent.ui.alta;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.coresolutions.coreinvent.data.pojos.FamilyPojo;

import java.io.File;
import java.util.List;

public class SharedViewModel extends AndroidViewModel {
    private MutableLiveData<File> image = new MutableLiveData<>();

    public SharedViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<File> getImage() {
        return image;
    }

    public void setImage(File file) {
        image.setValue(file);
    }

}
