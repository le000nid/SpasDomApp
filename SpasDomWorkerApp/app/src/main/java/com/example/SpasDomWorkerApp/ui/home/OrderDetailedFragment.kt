package com.example.spasdomworkerapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.spasdomworkerapp.R
import com.example.spasdomworkerapp.databinding.FragmentOrderBinding

class OrderDetailedFragment : Fragment(R.layout.fragment_order) {

    private val args by navArgs<OrderDetailedFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentOrderBinding.bind(view)

        val itemOrder = args.orderItem

        val status: String

        if(itemOrder.finished){
            status = "Завершён"
        } else {
            status = "Не завершён"
        }

        binding.apply {
            txProblem.text = itemOrder.problem
            txDate.text = itemOrder.date
            txAddress.text = itemOrder.address
            txId.text = itemOrder.id.toString()
            txStatus.text = status
            txTime.text = itemOrder.time
        }

    }
}