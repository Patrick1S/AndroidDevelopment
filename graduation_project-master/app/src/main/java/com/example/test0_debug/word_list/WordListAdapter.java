package com.example.test0_debug.word_list;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.test0_debug.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {
    private List<WordListData> wordListData;

    public WordListAdapter(List<WordListData> mHistoryList) {
        this.wordListData = mHistoryList;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.recordView.setOnClickListener(v -> {

        });
        holder.content.setOnClickListener(v -> {
           Toast.makeText(v.getContext(), wordListData.get(holder.getAdapterPosition()).getDesc(), Toast.LENGTH_SHORT).show();
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WordListData WordListData = wordListData.get(position);
        String temp = WordListData.getName() + "  " + WordListData.getSymbol();
        holder.content.setText(temp);
    }

    @Override
    public int getItemCount() {
        return wordListData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        View recordView;

        public ViewHolder(View itemView) {
            super(itemView);
            recordView = itemView;
            content = itemView.findViewById(R.id.record_content);
        }
    }

}
