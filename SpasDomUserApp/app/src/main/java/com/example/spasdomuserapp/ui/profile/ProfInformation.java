package com.example.spasdomuserapp.ui.profile;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.MutableLiveData;

import com.example.spasdomuserapp.R;

import java.util.ArrayList;
import java.util.Set;

public class ProfInformation {

    private static ProfInformation instance;
    private Profile profile = new Profile();
    public static ProfInformation getInstance(){
        if(instance == null){
            instance = new ProfInformation();
        }
        return instance;
    }
    public MutableLiveData<Profile> getInformation(Context context){
        setInformation(context);
        MutableLiveData<Profile> profile_information = new MutableLiveData<>();
        profile_information.setValue(profile);
        return profile_information;
    }

    private void setInformation(Context context){
        profile.setInf(context);
        }
}
