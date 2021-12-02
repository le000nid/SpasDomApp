package com.example.spasdomuserapp.models

import android.net.Uri

data class Photo (
    val title: String,
    val uri: Uri? = null
        )