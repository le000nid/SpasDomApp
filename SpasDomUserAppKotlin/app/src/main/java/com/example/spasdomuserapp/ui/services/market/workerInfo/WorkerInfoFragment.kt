package com.example.spasdomuserapp.ui.services.market.workerInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentWorkerInfoBinding
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.util.handleApiError
import com.example.spasdomuserapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkerInfoFragment : Fragment() {

    private val viewModel: WorkerInfoViewModel by viewModels()
    private var reviewsAdapter: WorkerInfoReviewsAdapter? = null
    private var servicesAdapter: WorkerInfoServicesAdapter? = null
    private val args by navArgs<WorkerInfoFragmentArgs>()
    private lateinit var binding: FragmentWorkerInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_worker_info, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.progressBar.visible(false)

        // TODO(Uncomment when you will receive preview workers from server)
        /*viewModel.getMarketWorker()

        viewModel.marketWorker.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visible(false)
                    binding.worker = it.value

                    reviewsAdapter?.reviews = it.value.reviews
                    servicesAdapter?.services = it.value.services
                }
                is Resource.Loading -> {
                    binding.progressBar.visible(true)
                }
                is Resource.Failure -> handleApiError(it) { }
            }
        }*/

        // TODO(HARDCODE remove when REST is ready)
        binding.worker = viewModel.worker

        reviewsAdapter = WorkerInfoReviewsAdapter()
        binding.workerReviewsRV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = reviewsAdapter
        }
        // TODO(HARDCODE remove when REST is ready)
        reviewsAdapter?.reviews = viewModel.worker.reviews

        servicesAdapter = WorkerInfoServicesAdapter()
        binding.workerServicesRV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = servicesAdapter
        }
        // TODO(HARDCODE remove when REST is ready)
        servicesAdapter?.services = viewModel.worker.services


        binding.btnOffer.setOnClickListener {
            // TODO(Specify type which I have to pass for REST post)
            val action = WorkerInfoFragmentDirections.actionWorkerInfoFragmentToOrderInfoFragment(appBarTitle = args.appBarTitle)
            findNavController().navigate(action)
        }

        return binding.root
    }
}