package com.example.cocktail2.ui.favorite;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cocktail2.FavoriteAdapter;
import com.example.cocktail2.R;

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel mViewModel;
    RecyclerView recyclerView;
    FavoriteAdapter adapter;
    Button removeAll;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView=view.findViewById(R.id.recyclerViewFavorites);
        mViewModel=new ViewModelProvider(requireActivity()).get(FavoriteViewModel.class);
        adapter=new FavoriteAdapter(mViewModel.getFavoriteCocktails().getValue(),mViewModel,getContext());
        removeAll=view.findViewById(R.id.btnRemoveAll);

        //recycler View
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        mViewModel.getFavoriteCocktails().observe(getViewLifecycleOwner(),adapter::setFavoriteCocktails);

        //btn
        removeAll.setOnClickListener(v->{
            mViewModel.clearFavorites();
            Toast.makeText(getContext(),"Remove All", Toast.LENGTH_LONG).show();
        });

        return view;

    }



}