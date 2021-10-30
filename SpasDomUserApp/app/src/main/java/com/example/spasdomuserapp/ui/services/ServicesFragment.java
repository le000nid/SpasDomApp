package com.example.spasdomuserapp.ui.services;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.spasdomuserapp.MainActivity;
import com.example.spasdomuserapp.R;
import com.example.spasdomuserapp.databinding.ActivityMainBinding;
import com.example.spasdomuserapp.databinding.FragmentHomeBinding;
import com.example.spasdomuserapp.databinding.FragmentServicesBinding;
import com.example.spasdomuserapp.ui.payment.PaymentViewModel;
import com.google.android.material.tabs.TabLayout;

public class ServicesFragment extends Fragment {

    private FragmentServicesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentServicesBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        ViewPager viewPager = binding.viewPager;
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(root.getContext(), getChildFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}