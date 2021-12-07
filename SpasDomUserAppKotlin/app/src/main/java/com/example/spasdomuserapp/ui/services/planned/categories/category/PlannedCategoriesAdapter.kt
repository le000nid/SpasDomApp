package com.example.spasdomuserapp.ui.services.planned.categories.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemCategoryCardBinding
import com.example.spasdomuserapp.models.PlannedCategory


class PlannedCategoriesAdapter(val callback: PlannedCategoriesClick) : RecyclerView.Adapter<PlannedCategoriesAdapter.CategoriesViewHolder>() {

    var plannedCategories: List<PlannedCategory> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val withDataBinding: ItemCategoryCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CategoriesViewHolder.LAYOUT,
            parent,
            false)
        return CategoriesViewHolder(withDataBinding)
    }

    override fun getItemCount() = plannedCategories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.category = plannedCategories[position]
            it.click = callback
        }
    }

    class CategoriesViewHolder(val viewDataBinding: ItemCategoryCardBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_category_card
        }
    }
}
