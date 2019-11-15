package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.CatViewHolder>{

    ArrayList<Cat> favouritesCats;
    Context context;

    public FavouritesAdapter(ArrayList<Cat> favouritesCats) {
        this.favouritesCats = favouritesCats;
    }

    @Override
    public CatViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat, parent, false);
        this.context = parent.getContext();
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }


    public static class CatViewHolder extends RecyclerView.ViewHolder{
        public View view;
        LinearLayout catContainer;
        TextView catName;

        public CatViewHolder (View v){
            super (v);
            view=v;
            catName =view.findViewById(R.id.catName);
            catContainer = view.findViewById(R.id.catContainer);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, final int position){
        final Cat catAtPosition = favouritesCats.get(position);
        holder.catName.setText(catAtPosition.getName());
        holder.catContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("catID",catAtPosition.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return favouritesCats.size();
    }


}
