package com.example.spasdomuserapp.ui.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.spasdomuserapp.R;
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<Profile> my_profile;
    private ProfInformation profile;
    public void init(){
        if(my_profile!=null){
            return;
        }
        profile = ProfInformation.getInstance();
    }
}