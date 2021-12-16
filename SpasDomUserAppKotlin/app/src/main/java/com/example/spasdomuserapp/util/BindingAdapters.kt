package com.example.spasdomuserapp.util

import android.graphics.Color
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.models.CategoriesList

/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("goneRv1","goneRv2")
fun goneIfRVNotNull(view: View, it1: Any?, it2: Any?) {
    view.visibility = if (it1 != null || it2 != null) View.GONE else View.VISIBLE
}

@BindingAdapter("visibleRv1","visibleRv2")
fun visibleIfRVNotNull(view: View, it1: Any?, it2: Any?) {
    view.visibility = if (it1 != null || it2 != null) View.VISIBLE else View.GONE
}

@BindingAdapter("goneIfNull")
fun goneIfNull(view: View, it: Any?) {
    view.visibility = if (it == null) View.GONE else View.VISIBLE
}


@BindingAdapter("visibleIfFinished")
fun visibleIfFinished(view: View, it: Int) {
    view.visibility = if (it == 1) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleIfReviewed")
fun visibleIfReviewed(view: View, rate: Int) {
    view.visibility = if (rate != 0) View.VISIBLE else View.GONE
}

@BindingAdapter("goneIfRate0")
fun goneIfRate0(view: View, it: Int) {
    view.visibility = if (it == 0) View.GONE else View.VISIBLE
}

@BindingAdapter("goneIfRateNot0", "state")
fun goneIfRateNot0(view: View, rate: Int, state: Int) {
    view.visibility = if (rate != 0 || state == 0) View.GONE else View.VISIBLE
}

@BindingAdapter("setRating")
fun setRating(view: ImageView, rate: Double) {
    when(rate.toInt()) {
        0 -> view.setBackgroundResource(R.drawable.ic_rate_0)
        1 -> view.setBackgroundResource(R.drawable.ic_rate_1)
        2 -> view.setBackgroundResource(R.drawable.ic_rate_2)
        3 -> view.setBackgroundResource(R.drawable.ic_rate_3)
        4 -> view.setBackgroundResource(R.drawable.ic_rate_4)
        5 -> view.setBackgroundResource(R.drawable.ic_rate_5)
    }
}

/**
 * Binding adapter used to display images from URL using Glide
 */
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

@BindingAdapter("dateType")
fun dateType(cardView: CardView, type: Int) {
    when(type) {
        1 -> cardView.setCardBackgroundColor(Color.parseColor("#A5D6A7"))
        2 -> cardView.setCardBackgroundColor(Color.parseColor("#EF9A9A"))
    }
}

//TODO (Add all drawables for categories)
@BindingAdapter("categoryImage")
fun ImageView.setCategoryImage(item: Int) {
    setImageResource(when (item) {
        1 -> R.drawable.ic_water_drop
        2 -> R.drawable.ic_home
        else -> R.drawable.ic_broken_image
    })
}