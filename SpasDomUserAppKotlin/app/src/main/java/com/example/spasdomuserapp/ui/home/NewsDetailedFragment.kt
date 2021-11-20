package com.example.spasdomuserapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.databinding.FragmentNewsDetailedBinding
import com.example.spasdomuserapp.util.bindImage


class NewsDetailedFragment : Fragment(R.layout.fragment_news_detailed) {

    private val args by navArgs<NewsDetailedFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNewsDetailedBinding.bind(view)

        val itemNews = args.newsItem

        binding.apply {
            bindImage(imgNewsItem, itemNews.thumbnail)
            txTitleNewsItem.text = itemNews.title
            txDescNewsItem.text = itemNews.description
        }

    }
}