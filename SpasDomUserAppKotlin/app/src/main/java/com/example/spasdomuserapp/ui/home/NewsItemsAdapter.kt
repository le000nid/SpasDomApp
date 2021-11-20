package com.example.spasdomuserapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.ItemNewsBinding
import com.example.spasdomuserapp.domain.NewsItem

/**
 * RecyclerView Adapter for setting up data binding on the items in the list.
 */
class NewsItemsAdapter(val callback: NewsItemClick) : RecyclerView.Adapter<NewsItemsAdapter.NewsItemsViewHolder>() {

    /**
     * The NewsItem that our Adapter will show
     */
    var newsItems: List<NewsItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemsViewHolder {
        val withDataBinding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            NewsItemsViewHolder.LAYOUT,
            parent,
            false)
        return NewsItemsViewHolder(withDataBinding)
    }

    override fun getItemCount() = newsItems.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: NewsItemsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.newsItem = newsItems[position]
            it.newsItemCallback = callback
        }
    }

     /**
     * ViewHolder for news items. All work is done by data binding.
     */
    class NewsItemsViewHolder(val viewDataBinding: ItemNewsBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_news
        }
    }
}