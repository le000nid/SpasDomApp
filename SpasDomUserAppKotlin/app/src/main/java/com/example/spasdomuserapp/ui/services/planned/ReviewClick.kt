package com.example.spasdomuserapp.ui.services.planned

import com.example.spasdomuserapp.models.Order

class ReviewClick(val block: (Order) -> Unit) {
    fun onReviewClick(order: Order) = block(order)
}