package com.example.spasdomworkerapp.ui.home.order.active

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomworkerapp.R
import com.example.spasdomworkerapp.databinding.FragmentActiveOrderBinding
import com.example.spasdomworkerapp.domain.Photo
import com.github.dhaval2404.imagepicker.ImagePicker

class ActiveOrderFragment : Fragment() {

    private var photoAdapter: PhotoAdapter? = null
    private val viewModel: AddOrderViewModel by viewModels()

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
        val binding: FragmentActiveOrderBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_active_order, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        photoAdapter = PhotoAdapter(PhotoRemoveClick {
            val oldList = viewModel.photos.value?.toMutableList()
            oldList?.remove(it)
            viewModel.photos.value = oldList?.toList()
        })

        binding.root.findViewById<RecyclerView>(R.id.photos_complete_rv).apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = photoAdapter
            setHasFixedSize(true)
        }

        binding.btnAddPhotoComplete.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent {
                    getContent.launch(it)
                }
        }

        binding.btnFinish.setOnClickListener {
            /*val action = PlannedInfoFragmentDirections.actionPlannedInfoFragmentToPlannedDateFragment()
            findNavController().navigate(action)*/
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
