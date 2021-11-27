package com.example.spasdomuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spasdomuserapp.ui.auth.AuthActivity

class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        finish()
        startActivity(Intent(this, AuthActivity::class.java))
    }
}