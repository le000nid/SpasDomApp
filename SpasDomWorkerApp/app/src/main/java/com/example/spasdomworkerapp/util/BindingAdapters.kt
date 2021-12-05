package com.example.spasdomworkerapp.util

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Binding adapter used to display images from URL using Glide
 */

@BindingAdapter("goneIfTrue")
fun goneIfTrue(view: View, it: Any?) {
    view.visibility = if (it == true) View.GONE else View.VISIBLE
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("imageUri")
fun setImageUri(imageView: ImageView, uri: Uri?) {
    if (uri != null) {
        imageView.setImageURI(null)
        imageView.setImageURI(uri)
    }
}

@BindingAdapter("goneIfUriNull")
fun goneIfUriNull(imageView: ImageView, uri: Uri?) {
    imageView.visibility = if (uri == null) View.GONE else View.VISIBLE
}