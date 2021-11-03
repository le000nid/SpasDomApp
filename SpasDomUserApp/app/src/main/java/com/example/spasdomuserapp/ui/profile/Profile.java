package com.example.spasdomuserapp.ui.profile;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.activity.contextaware.ContextAwareHelper;

import com.example.spasdomuserapp.R;

import java.util.ArrayList;

public class Profile {
    String email, phone, name, account_number, adres;
    Bitmap photo;

    public Profile() {}
    public void setInf(Context context){
        this.account_number="ExampleAcountNumber";
        this.name="ExampleName";
        this.email ="Example@email.com";
        this.phone = "ExamplePhoneNumber";
        this.adres="ExapleAdress";
        photo = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.prof_photo);

    }
}
