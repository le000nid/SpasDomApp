package com.example.spasdomworkerapp.ui.home.order.active

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomworkerapp.R
import com.example.spasdomworkerapp.databinding.FragmentActiveOrderBinding
import com.example.spasdomworkerapp.models.Photo
import com.github.dhaval2404.imagepicker.ImagePicker

class ActiveOrderFragment : Fragment() {

    private var completePhotoAdapter: PhotoAdapter? = null
    private var doorPhotoAdapter: PhotoAdapter? = null
    private val viewModel: AddOrderViewModel by viewModels()

    private val getContentComplete = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { intent ->
        if (intent.data?.data != null) {
            // TODO(Add the restriction to number of photos that user can upload)
            val oldList = viewModel.completePhotos.value?.toMutableList()
            oldList?.add(Photo(uri = intent.data?.data))
            viewModel.completePhotos.value = oldList?.toList()
        }
    }

    private val getContentDoor = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { intent ->
        if (intent.data?.data != null) {
            // TODO(Add the restriction to number of photos that user can upload)
            val oldList = viewModel.doorPhotos.value?.toMutableList()
            oldList?.add(Photo(uri = intent.data?.data))
            viewModel.doorPhotos.value = oldList?.toList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentActiveOrderBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_active_order, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        doorPhotoAdapter = PhotoAdapter(PhotoRemoveClick {
            val oldList = viewModel.doorPhotos.value?.toMutableList()
            oldList?.remove(it)
            viewModel.doorPhotos.value = oldList?.toList()
        })

        completePhotoAdapter = PhotoAdapter(PhotoRemoveClick {
            val oldList = viewModel.completePhotos.value?.toMutableList()
            oldList?.remove(it)
            viewModel.completePhotos.value = oldList?.toList()
        })

        binding.root.findViewById<RecyclerView>(R.id.photos_door_rv).apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = doorPhotoAdapter
        }

        binding.root.findViewById<RecyclerView>(R.id.photos_complete_rv).apply {
            layoutManager = GridLayoutManager(activity, 3)
            adapter = completePhotoAdapter
        }

        binding.btnAddPhotoDoor.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent {
                    getContentDoor.launch(it)
                }
        }

        binding.btnAddPhotoComplete.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent {
                    getContentComplete.launch(it)
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

        viewModel.completePhotos.observe(viewLifecycleOwner) {
            completePhotoAdapter?.photos = viewModel.completePhotos.value!!.toList()
        }

        viewModel.doorPhotos.observe(viewLifecycleOwner) {
            doorPhotoAdapter?.photos = viewModel.doorPhotos.value!!.toList()
        }

        Toast.makeText(context, "Чтобы начать работу, загрузите фото двери", Toast.LENGTH_LONG).show()

        ImagePicker.with(this)
            .cropSquare()
            .compress(1024)			//Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent {
                getContentDoor.launch(it)
            }
    }
}

class PhotoRemoveClick(val block: (Photo) -> Unit) {
    fun onClick(photo: Photo) = block(photo)
}
