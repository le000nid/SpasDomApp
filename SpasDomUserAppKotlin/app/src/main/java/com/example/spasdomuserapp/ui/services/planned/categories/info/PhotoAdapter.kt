package com.example.spasdomuserapp.ui.services.planned.categories.info

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemPhotoBinding
import com.example.spasdomuserapp.models.Photo


class PhotoAdapter(/*val callback: PlannedCategoriesClick*/) : RecyclerView.Adapter<PhotoAdapter.PhotosViewHolder>() {

    var photos: List<Photo> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val withDataBinding: ItemPhotoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PhotosViewHolder.LAYOUT,
            parent,
            false)
        return PhotosViewHolder(withDataBinding)
    }

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.photo = photos[position]
            /*it.click = callback*/
        }
    }

    class PhotosViewHolder(val viewDataBinding: ItemPhotoBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_photo
        }
    }
}
