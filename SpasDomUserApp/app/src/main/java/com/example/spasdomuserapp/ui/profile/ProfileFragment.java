package com.example.spasdomuserapp.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spasdomuserapp.R;
import com.example.spasdomuserapp.databinding.FragmentHomeBinding;
import com.example.spasdomuserapp.databinding.FragmentProfileBinding;
import com.example.spasdomuserapp.ui.chat.ChatViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    private ProfInformation profInformation;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        profInformation = new ProfInformation();

        MutableLiveData<Profile> data = new MutableLiveData<>();
        data = profInformation.getInformation(getActivity());
        final ImageView imageView = binding.photoImageView;
        final TextView name = binding.userName;
        final TextView account_number = binding.userAccountNumber;
        final TextView phone = binding.userPhoneNumber;
        final TextView adres = binding.userAdress;
        final TextView email = binding.userEmail;

        imageView.setImageBitmap(data.getValue().photo);
        name.setText(data.getValue().name);
        account_number.setText(data.getValue().account_number);
        phone.setText(data.getValue().phone);
        adres.setText(data.getValue().adres);
        email.setText(data.getValue().email);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}