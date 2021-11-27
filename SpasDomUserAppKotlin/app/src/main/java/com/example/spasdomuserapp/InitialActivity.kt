package com.example.spasdomuserapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import com.example.spasdomuserapp.database.UserPreferences
import com.example.spasdomuserapp.ui.MainActivity
import com.example.spasdomuserapp.ui.auth.AuthActivity
import com.example.spasdomuserapp.util.startNewActivity

@AndroidEntryPoint
class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userPreferences = UserPreferences(this)

        userPreferences.accessToken.asLiveData().observe(this, {
            val activity = if (it == null) AuthActivity::class.java else MainActivity::class.java
            startNewActivity(activity)
        })
    }
}