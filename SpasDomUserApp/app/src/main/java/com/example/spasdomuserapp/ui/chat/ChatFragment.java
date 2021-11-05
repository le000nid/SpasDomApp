package com.example.spasdomuserapp.ui.chat;

import androidx.annotation.StringRes;
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
import com.example.spasdomuserapp.databinding.FragmentChatBinding;
import com.example.spasdomuserapp.databinding.FragmentHomeBinding;
import com.example.spasdomuserapp.ui.home.HomeViewModel;

public class ChatFragment extends Fragment {

    private RecyclerView chatsView;
    private ChatViewModel chatViewModel;
    private FragmentChatBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chatViewModel =
                new ViewModelProvider(this).get(ChatViewModel.class);

        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        chatsView = binding.chatsView;
        ChatAdapter chatAdapter = new ChatAdapter(root.getContext());
        chatsView.setAdapter(chatAdapter);

        LinearLayoutManager chatsManager = new LinearLayoutManager(root.getContext());
        chatsView.setLayoutManager(chatsManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(chatsView.getContext(),
                chatsManager.getOrientation());
        chatsView.addItemDecoration(dividerItemDecoration);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}