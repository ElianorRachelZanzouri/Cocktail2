package com.example.cocktail2.ui.search;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.cocktail2.CocktailAdapter;
import com.example.cocktail2.DetailsCocktail;
import com.example.cocktail2.R;
import com.example.cocktail2.cocktails.Cocktail;
import com.example.cocktail2.ui.favorite.FavoriteViewModel;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;
    CocktailAdapter cocktailAdapter;
    FavoriteViewModel favoriteViewModel;

    RecyclerView recyclerView;
    EditText edtSearch;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_search, container, false);

        mViewModel=new ViewModelProvider(this).get(SearchViewModel.class);
        favoriteViewModel=new ViewModelProvider(requireActivity()).get(FavoriteViewModel.class);

        recyclerView=root.findViewById(R.id.recyclerViewSearch);
        edtSearch=root.findViewById(R.id.editSearchText);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cocktailAdapter=new CocktailAdapter(new ArrayList<>(),this::onCocktailClick, this::onFavoriteClick);
        recyclerView.setAdapter(cocktailAdapter);

        edtSearch.setOnEditorActionListener((v,actioID, event)->{
            String str=edtSearch.getText().toString().trim();
            if(!str.isEmpty()){
                mViewModel.searchCocktail(str);
            }
            return true;
        });

        mViewModel.getCocktails().observe(getViewLifecycleOwner(),cocktails->{
            if(cocktails!=null){
                cocktailAdapter.setCocktails(cocktails);
            }
        });

        favoriteViewModel.getFavoriteCocktails().observe(getViewLifecycleOwner(),favoriteCocktails->{
            cocktailAdapter.setFavoritesCocktails(favoriteCocktails);
            cocktailAdapter.notifyDataSetChanged();
        });
        return root;


    }

    private void onFavoriteClick(Cocktail cocktail) {
        if(favoriteViewModel.isFavorite(cocktail)){
            favoriteViewModel.removeFavorite(cocktail);
        }
        else{
            favoriteViewModel.addFavorite(cocktail);
        }
    }

    private void onCocktailClick(Cocktail cocktail) {
        Intent intent=new Intent(getContext(), DetailsCocktail.class);
        intent.putExtra("cocktail", (CharSequence) cocktail);
        startActivity(intent);
    }

    //on Resume: refresh
    @Override
    public void onResume(){
        super.onResume();
        if(cocktailAdapter!=null){
            //favoriteViewModel.loadFavoriteDB();
            cocktailAdapter.notifyDataSetChanged();
        }
    }

}