package com.example.spasdomuserapp.ui.services.planned.categories.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemPhotoBinding
import com.example.spasdomuserapp.models.Photo

class PhotoDiffCallback(
    private val oldList: List<Photo>,
    private val newList: List<Photo>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPhoto = oldList[oldItemPosition]
        val newPhoto = newList[newItemPosition]
        return oldPhoto.uri == newPhoto.uri
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPhoto = oldList[oldItemPosition]
        val newPhoto = newList[newItemPosition]
        return oldPhoto == newPhoto
    }

}

class PhotoAdapter(val callback: PhotoRemoveClick) : RecyclerView.Adapter<PhotoAdapter.PhotosViewHolder>() {

    var photos: List<Photo> = emptyList()
        set(newValue) {
            val diffCallback = PhotoDiffCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

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
            it.photo = photos[position]
            it.removeClick = callback
        }
    }

    class PhotosViewHolder(val viewDataBinding: ItemPhotoBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_photo
        }
    }

    override fun getItemCount(): Int = photos.size
}
