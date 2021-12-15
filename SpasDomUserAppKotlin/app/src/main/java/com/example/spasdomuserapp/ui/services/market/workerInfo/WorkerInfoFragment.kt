package com.example.spasdomuserapp.ui.services.market.workerInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentWorkerInfoBinding

class WorkerInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentWorkerInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_worker_info, container, false)



        return binding.root
    }
}