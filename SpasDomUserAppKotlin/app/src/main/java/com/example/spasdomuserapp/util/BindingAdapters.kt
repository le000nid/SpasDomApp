package com.example.spasdomuserapp.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.domain.PlannedOrder

/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("goneIfNull")
fun goneIfNull(view: View, it: Any?) {
    view.visibility = if (it == null) View.GONE else View.VISIBLE
}

@BindingAdapter("goneIfTrue")
fun goneIfTrue(view: View, it: Any?) {
    view.visibility = if (it == true) View.GONE else View.VISIBLE
}

@BindingAdapter("goneIfRate0")
fun goneIfRate0(view: View, it: Int) {
    view.visibility = if (it == 0) View.GONE else View.VISIBLE
}

@BindingAdapter("goneIfRateNot0", "state")
fun goneIfRateNot0(view: View, rate: Int, state: Boolean) {
    view.visibility = if (rate != 0 || !state) View.GONE else View.VISIBLE
}

@BindingAdapter("setRating")
fun setRating(view: ImageView, it: PlannedOrder) {
    when(it.userRate) {
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
