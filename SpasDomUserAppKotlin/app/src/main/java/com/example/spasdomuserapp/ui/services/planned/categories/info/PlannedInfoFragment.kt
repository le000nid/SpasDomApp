package com.example.spasdomuserapp.ui.services.planned.categories.info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedInfoBinding
import com.example.spasdomuserapp.models.Photo
import com.example.spasdomuserapp.models.PlannedCategory
import com.example.spasdomuserapp.ui.services.planned.categories.AddOrderViewModel

class PlannedInfoFragment : Fragment() {

    private var photoAdapter: PhotoAdapter? = null
    private val viewModel: AddOrderViewModel by viewModels()

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