package com.example.spasdomworkerapp.ui.home.order

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spasdomworkerapp.R
import com.example.spasdomworkerapp.databinding.FragmentOrderBinding
import com.google.android.material.navigation.NavigationBarView

class OrderDetailedFragment : Fragment(R.layout.fragment_order) {

    private val args by navArgs<OrderDetailedFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentOrderBinding.bind(view)

        val itemOrder = args.orderItem

        val status: String

        if(itemOrder.finished){
            status = "Завершён"
            binding.beginButton.isEnabled = false
            binding.beginButton.visibility = View.INVISIBLE
        } else if (itemOrder.active) {
            status = "В процессе"
            binding.beginButton.text = "Продолжить"
        } else {
            status = "Не завершён"
        }

        (requireActivity() as AppCompatActivity).supportActionBar?.title = itemOrder.address

        binding.apply {
            txProblem.text = itemOrder.problem
            txDate.text = args.orderShowFormat
            txAddress.text = itemOrder.address
            txId.text = itemOrder.id.toString()
            txStatus.text = status
            txTime.text = itemOrder.time
        }

        binding.beginButton.setOnClickListener {
            val action = OrderDetailedFragmentDirections.actionOrderDetailedFragmentToActiveOrderFragment(itemOrder)
            findNavController().navigate(action)
        }
    }
}