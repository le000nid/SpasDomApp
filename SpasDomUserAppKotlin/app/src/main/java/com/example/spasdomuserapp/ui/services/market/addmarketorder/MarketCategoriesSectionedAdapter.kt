package com.example.spasdomuserapp.ui.services.market.addmarketorder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemSectionedCategoryCardBinding
import com.example.spasdomuserapp.models.CategoriesList


class MarketCategoriesSectionedAdapter(val callback: MarketCategoryItemClick) :
    RecyclerView.Adapter<MarketCategoriesSectionedAdapter.ViewHolder>() {

    var categoriesLists: List<CategoriesList> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding: ItemSectionedCategoryCardBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ViewHolder.LAYOUT,
            parent,
            false)
        return ViewHolder(withDataBinding)
    }

    override fun getItemCount() = categoriesLists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.category = categoriesLists[position]
            it.click = callback
        }
    }

    class ViewHolder(val viewDataBinding: ItemSectionedCategoryCardBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_sectioned_category_card
        }
    }
}