package com.example.spasdomuserapp.ui.services.market.workers

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
import com.example.spasdomuserapp.databinding.FragmentMarketWorkersBinding
import com.example.spasdomuserapp.models.WorkerPreview
import com.example.spasdomuserapp.ui.services.planned.addplannedorder.info.PlannedInfoFragmentArgs
import com.example.spasdomuserapp.util.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketWorkersFragment : Fragment() {

    private val viewModel: MarketWorkersViewModel by viewModels()
    private var workersAdapter: MarketWorkersAdapter? = null
    private val args by navArgs<MarketWorkersFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMarketWorkersBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_market_workers, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.progressBar.visible(false)

        // TODO(Uncomment when you will receive preview workers from server)
        /*viewModel.getPreviewWorkers()

        viewModel.marketPreviewWorkers.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visible(false)
                    workersAdapter?.workers = it.value
                }
                is Resource.Loading -> {
                    binding.progressBar.visible(true)
                }
                is Resource.Failure -> handleApiError(it) { }
            }
        }*/

        workersAdapter = MarketWorkersAdapter(WorkerClick {
            val action = MarketWorkersFragmentDirections.actionMarketWorkersFragmentToWorkerInfoFragment(appBarTitle = args.appBarTitle, workerPreview = it)
            findNavController().navigate(action)
        })

        binding.workersRV.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = workersAdapter
        }

        // TODO(HARDCODE remove when REST is ready)
        workersAdapter?.workers = viewModel.previewWorkers


        return binding.root
    }
}

class WorkerClick(val block: (WorkerPreview) -> Unit) {
    fun onClick(worker: WorkerPreview) = block(worker)
}