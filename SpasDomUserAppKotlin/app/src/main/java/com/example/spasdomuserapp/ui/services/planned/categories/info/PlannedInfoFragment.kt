package com.example.spasdomuserapp.ui.services.planned.categories.info

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedInfoBinding
import com.example.spasdomuserapp.models.Photo
import com.example.spasdomuserapp.models.PlannedCategory
import com.example.spasdomuserapp.ui.services.planned.categories.AddOrderViewModel
import com.github.dhaval2404.imagepicker.ImagePicker

class PlannedInfoFragment : Fragment() {

    private var photoAdapter: PhotoAdapter? = null
    private val viewModel: AddOrderViewModel by viewModels()

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { intent ->
        if (intent.data?.data != null) {
            val oldList = viewModel.photos.value?.toMutableList()
            oldList?.add(Photo(uri = intent.data?.data))
            viewModel.photos.value = oldList?.toList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlannedInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_planned_info, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        photoAdapter = PhotoAdapter(PhotoRemoveClick {
            val oldList = viewModel.photos.value?.toMutableList()
            oldList?.remove(it)
            viewModel.photos.value = oldList?.toList()
        }, PhotoUploadClick { _ ->

            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent {
                    getContent.launch(it)
                }
        })



        binding.root.findViewById<RecyclerView>(R.id.photos_rv).apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = photoAdapter
            setHasFixedSize(true)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.photos.observe(viewLifecycleOwner) {
            photoAdapter?.photos = viewModel.photos.value!!.toList()
        }
    }
}

class PhotoRemoveClick(val block: (Photo) -> Unit) {
    fun onClick(photo: Photo) = block(photo)
}

class PhotoUploadClick(val block: (Photo) -> Unit) {
    fun onClick(photo: Photo) = block(photo)
}
