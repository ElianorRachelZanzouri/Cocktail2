package com.example.cocktail2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cocktail2.CocktailAdapter;
import com.example.cocktail2.DetailsCocktail;
import com.example.cocktail2.R;
import com.example.cocktail2.cocktailAPI.CocktailAPIClient;
import com.example.cocktail2.cocktailAPI.CocktailService;
import com.example.cocktail2.cocktails.Cocktail;
import com.example.cocktail2.cocktails.Cocktails;
import com.example.cocktail2.databinding.FragmentHomeBinding;
import com.example.cocktail2.ui.favorite.FavoriteViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private Retrofit retrofit= CocktailAPIClient.getClient();
    private RecyclerView recyclerView;
    private CocktailAdapter adapterCocktail;
    private FavoriteViewModel favoriteViewModel;
    private List<Cocktail> cocktailList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       //Start View
        //this code => fragment Home
        View view=inflater.inflate(R.layout.fragment_home,container,false);


        FavoriteViewModel favoriteViewModel=new ViewModelProvider(requireActivity()).get(FavoriteViewModel.class);
        //recycler view in Fragment Home
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        //parameters in the CocktailAdapter
        adapterCocktail= new CocktailAdapter(new ArrayList<>(),
                this::onCocktailClick,
                this::onFavoriteClick);
        //
        recyclerView.setAdapter(adapterCocktail);

        //load all cocktail
        fetchCocktails();
        //End : view

        //fragment Favorite : get Favorite cocktails
        favoriteViewModel.getFavoriteCocktails().observe(
                getViewLifecycleOwner(),favoriteCocktail->{
                    adapterCocktail.setFavoritesCocktails(favoriteCocktail);
                    adapterCocktail.notifyDataSetChanged();
                }
        );

        return view;

    }

    private void onCocktailClick(Cocktail cocktail){

        //click on the cocktail-> see details cocktails
        Intent intent=new Intent(getContext(), DetailsCocktail.class);
        intent.putExtra("cocktail", (CharSequence) cocktail);
        startActivity(intent);

    }
    private void onFavoriteClick(Cocktail cocktail){
        if(favoriteViewModel.isFavorite(cocktail)){//cocktail like click (again)-> cocktail unlike
            favoriteViewModel.removeFavorite(cocktail);
        }
        else{//cocktail not like click-> cocktail
            favoriteViewModel.addFavorite(cocktail);
        }
    }

    private void fetchCocktails() {
        CocktailService cocktailService= retrofit.create(CocktailService.class);
        Call<Cocktails> callAsyn=cocktailService.getCocktailsByLetter("s");

        callAsyn.enqueue(
                new Callback<Cocktails>() {
                    @Override
                    public void onResponse(Call<Cocktails> call, Response<Cocktails> response) {
                        //it's ok
                        cocktailList=response.body().cocktails;
                        adapterCocktail.setCocktails(cocktailList);
                    }

                    @Override
                    public void onFailure(Call<Cocktails> call, Throwable throwable) {

                    }
                }
        );
    }

   public void onResume(){
        super.onResume();
        if(adapterCocktail!=null){
            //favoriteViewModel.loadFavoriteDB();
            adapterCocktail.notifyDataSetChanged();
        }
   }
}