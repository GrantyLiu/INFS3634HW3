package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CatViewHolder> implements Filterable {

    Context context;
    ArrayList<Cat> catsToAdapt;
    ArrayList<Cat> mFilteredList;



    public SearchAdapter(ArrayList<Cat> catsToAdapt) {
        this.catsToAdapt = catsToAdapt;
        this.mFilteredList = new ArrayList<>();
    }

    public void setData(ArrayList<Cat> cats) {
        this.catsToAdapt = cats;
    }

    @Override
    public CatViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat, parent, false);
        this.context = parent.getContext();
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, final int position){
        if (mFilteredList.isEmpty()){
            mFilteredList = catsToAdapt;
        }
        final Cat catAtPosition = mFilteredList.get(position);

        holder.catName.setText(catAtPosition.getName());
        holder.catContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, DetailActivity.class);
                intent1.putExtra("catID",catAtPosition.getId());
                context.startActivity(intent1);
            }
        });
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
    public int getItemCount(){
        if (mFilteredList.isEmpty()){
            return catsToAdapt.size();
        } else {
            return mFilteredList.size();
        }
    }



    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty()) {
                    mFilteredList = catsToAdapt;
                } else {
                    ArrayList<Cat> filteredList = new ArrayList<>();
                    for (Cat filteredCat : catsToAdapt) {
                        if (filteredCat.getName().toLowerCase().contains(searchString)) {
                            filteredList.add(filteredCat);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Cat>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
