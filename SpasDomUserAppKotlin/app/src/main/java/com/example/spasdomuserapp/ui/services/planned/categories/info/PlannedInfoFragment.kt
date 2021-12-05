package com.example.spasdomuserapp.ui.services.planned.categories.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentPlannedInfoBinding
import com.example.spasdomuserapp.models.Photo
import com.example.spasdomuserapp.models.PlannedOrderPost
import com.github.dhaval2404.imagepicker.ImagePicker

class PlannedInfoFragment : Fragment() {

    private var photoAdapter: PhotoAdapter? = null
    private val viewModel: InfoPlannedOrderViewModel by viewModels()
    private val args by navArgs<PlannedInfoFragmentArgs>()

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { intent ->
        if (intent.data?.data != null) {
            // TODO(Add the restriction to number of photos that user can upload)
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
        })

        binding.root.findViewById<RecyclerView>(R.id.photos_rv).apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = photoAdapter
            setHasFixedSize(true)
        }

        binding.btnAddPhoto.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent {
                    getContent.launch(it)
                }
        }

        binding.editTextOrder.addTextChangedListener {
            viewModel.comment = it.toString()
        }

        binding.btnNext.setOnClickListener {

            val categoryLvl1 = args.categoryLvl1
            val categoryLvl2 = args.categoryName
            val plannedOrderPost = PlannedOrderPost(categoryLvl1, categoryLvl2, viewModel.comment)

            val action = PlannedInfoFragmentDirections.actionPlannedInfoFragmentToPlannedDateFragment(categoryLvl2, plannedOrderPost)
            findNavController().navigate(action)
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
