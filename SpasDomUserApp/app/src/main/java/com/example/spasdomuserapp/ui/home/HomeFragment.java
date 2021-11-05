package com.example.spasdomuserapp.ui.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spasdomuserapp.R;
import com.example.spasdomuserapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private RecyclerView newsView;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        newsView = binding.newsView;
        NewsAdapter newsAdapter = new NewsAdapter(root.getContext());
        newsView.setAdapter(newsAdapter);

        LinearLayoutManager newsManager = new LinearLayoutManager(root.getContext());
        newsView.setLayoutManager(newsManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(newsView.getContext(),
                newsManager.getOrientation());
        newsView.addItemDecoration(dividerItemDecoration);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}