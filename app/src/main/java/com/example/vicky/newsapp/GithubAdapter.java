package com.example.vicky.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vicky.newsapp.model.Repository;

import java.util.ArrayList;

/**
 * Created by Vicky on 6/29/17.
 */

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.ItemHolder>{

    private ArrayList<Repository> data;
    ItemClickListener listener;

    public GithubAdapter() {
    }

    public GithubAdapter(ArrayList<Repository> data, ItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(int clickedItemIndex);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.item, parent, shouldAttachToParentImmediately);
        ItemHolder holder = new ItemHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(data == null)
            return 0;

        return data.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView description;
        TextView publishedAt;

        ItemHolder(View view){
            super(view);
            title = (TextView)view.findViewById(R.id.title);
            description = (TextView)view.findViewById(R.id.description);
            publishedAt = (TextView)view.findViewById(R.id.publishedAt);
            view.setOnClickListener(this);
        }

        public void bind(int pos){
            Repository repo = data.get(pos);
            title.setText(repo.getTitle());
            description.setText(repo.getDescription());
            publishedAt.setText(repo.getPublishedAt());
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos);
        }
    }



}
