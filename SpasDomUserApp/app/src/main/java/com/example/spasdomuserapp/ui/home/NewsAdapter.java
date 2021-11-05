package com.example.spasdomuserapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spasdomuserapp.R;
import com.example.spasdomuserapp.ui.chat.ChatAdapter;


public class NewsAdapter extends  RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private static final String [][] NEWS = new String[][]{
            {"Заголовок 1", "Краткое описание новости 1"},
            {"Заголовок 2", "Краткое описание новости 2"},
            {"Заголовок 3", "Краткое описание новости 3"},
            {"Заголовок 4", "Краткое описание новости 4"},
            {"Заголовок 5", "Краткое описание новости 5"},
            {"Заголовок 6", "Краткое описание новости 6"},
            {"Заголовок 7", "Краткое описание новости 7"},
            {"Заголовок 8", "Краткое описание новости 8"},
            {"Заголовок 9", "Краткое описание новости 9"}
    };
    Context context;

    public NewsAdapter(Context ct){
        context = ct;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_el_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.news_title.setText(NEWS[position][0]);
        holder.news_description.setText(NEWS[position][1]);
    }

    @Override
    public int getItemCount() {
        return NEWS.length;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView news_title, news_description;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            news_title = itemView.findViewById(R.id.news_title);
            news_description = itemView.findViewById(R.id.news_description);
        }
    }
}
