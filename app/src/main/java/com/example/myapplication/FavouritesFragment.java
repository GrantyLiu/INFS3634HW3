package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavouritesFragment extends Fragment {

    FavouritesAdapter favouritesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        final RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        recyclerView = view.findViewById(R.id.rv_favourites);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        favouritesAdapter = new FavouritesAdapter(FavouriteCatDatabase.getFavouriteCats());
        recyclerView.setAdapter(favouritesAdapter);

        return view;
    }
}
