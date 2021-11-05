package com.example.spasdomuserapp.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spasdomuserapp.R;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    @StringRes
    private static final int[] CHAT_TITLES = new int[]{
            R.string.chat_house,
            R.string.chat_entrance,
            R.string.chat_UK,
            R.string.chat_e_house,
            R.string.chat_e_entrance
    };
    Context context;

    public ChatAdapter(Context ct){
        context = ct;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chat_el_layout, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.chat_title.setText(CHAT_TITLES[position]);
        // holder.chat_icon.setImageResource();
    }

    @Override
    public int getItemCount() {
        return CHAT_TITLES.length;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView chat_title;
        ImageView chat_icon;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            chat_title = itemView.findViewById(R.id.chat_title);
            chat_icon = itemView.findViewById(R.id.chat_icon);
        }
    }
}
