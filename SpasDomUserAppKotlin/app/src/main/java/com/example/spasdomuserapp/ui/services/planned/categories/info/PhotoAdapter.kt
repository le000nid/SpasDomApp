package com.example.spasdomuserapp.ui.services.planned.categories.info

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemPhotoBinding
import com.example.spasdomuserapp.models.Photo


class PhotoAdapter(/*val callback: PlannedCategoriesClick*/) : ListAdapter<Photo,
        PhotoAdapter.PhotosViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val withDataBinding: ItemPhotoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PhotosViewHolder.LAYOUT,
            parent,
            false)
        return PhotosViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.photo = getItem(position)
            /*it.click = callback*/
        }
    }

    class PhotosViewHolder(val viewDataBinding: ItemPhotoBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_photo
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
            oldItem == newItem
    }
}
