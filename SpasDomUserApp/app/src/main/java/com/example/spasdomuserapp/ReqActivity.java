package com.example.spasdomuserapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ReqActivity extends AppCompatActivity {
    EditText comment;
    ImageView photo;
    Button back;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req);
        comment = (EditText) findViewById(R.id.comment);
        back = (Button) findViewById(R.id.button);
        photo = (ImageView) findViewById(R.id.imageView);
        //photo.setImageDrawable(getDrawable(R.drawable.ic_menu_add));
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        String [] permitions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permitions, PERMISSION_CODE);
                    }
                    else{
                        pickImageFromGalery();
                    }
                }else{
                    pickImageFromGalery();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    private void pickImageFromGalery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch ((requestCode)) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGalery();
                } else {
                    Toast.makeText(this, "Доступ запрещен!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if (requestCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            photo.setImageURI(data.getData());
        //}
    }

}