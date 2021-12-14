package com.example.spasdomuserapp.ui.services.market.addmarketorder

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemCategorySectionBinding
import com.example.spasdomuserapp.models.SectionCategory
import com.example.spasdomuserapp.responses.SectionCategories

class MarketCategoriesAdapter : RecyclerView.Adapter<MarketCategoriesAdapter.ViewHolder>() {

    var sectionedCategories: List<SectionCategories> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding: ItemCategorySectionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ViewHolder.LAYOUT,
            parent,
            false)
        return ViewHolder(withDataBinding)
    }

    override fun getItemCount() = sectionedCategories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.sectionTitle.text = sectionedCategories[position].title

            val sectionAdapter = MarketCategoriesSectionedAdapter(MarketCategoryItemClick { item ->
                Log.i("itemClick", item.title)
            })

            it.root.findViewById<RecyclerView>(R.id.sectionRV).apply {
                layoutManager = GridLayoutManager(context, 4)
                adapter = sectionAdapter
            }

            sectionAdapter.categoriesLists = sectionedCategories[position].categories
        }
    }

    class ViewHolder(val viewDataBinding: ItemCategorySectionBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_category_section
        }
    }
}

class MarketCategoryItemClick(val block: (SectionCategory) -> Unit) {
    fun onClick(sectionCategory: SectionCategory) = block(sectionCategory)
}
