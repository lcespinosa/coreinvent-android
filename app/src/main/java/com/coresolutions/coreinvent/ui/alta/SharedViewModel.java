package com.coresolutions.coreinvent.ui.alta;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import pl.aprilapps.easyphotopicker.EasyImage;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Bitmap> image = new MutableLiveData<Bitmap>();
    private final MutableLiveData<EasyImage> easy = new MutableLiveData<EasyImage>();

    public MutableLiveData<Bitmap> getImage() {
        return image;
    }

    public MutableLiveData<EasyImage> getEasy() {
        return easy;
    }

    public void setImage(Bitmap bitmap) {
        image.setValue(bitmap);
    }

    public void setEasy(EasyImage easyimage) {
        easy.setValue(easyimage);
    }
}
