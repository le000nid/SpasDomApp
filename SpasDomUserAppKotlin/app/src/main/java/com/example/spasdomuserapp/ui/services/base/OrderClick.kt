package com.example.spasdomuserapp.ui.services.base

import com.example.spasdomuserapp.models.Order

class OrderClick(val block: (Order) -> Unit) {
    fun onClick(order: Order) = block(order)
}