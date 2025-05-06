package com.example.cocktail2.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cocktail2.cocktailAPI.CocktailAPIClient;
import com.example.cocktail2.cocktailAPI.CocktailService;
import com.example.cocktail2.cocktails.Cocktail;
import com.example.cocktail2.cocktails.Cocktails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchViewModel extends ViewModel {

    private final MutableLiveData<List<Cocktail>> cocktails;

    public SearchViewModel(){
        cocktails=new MutableLiveData<>();
    }

    public LiveData<List<Cocktail>> getCocktails(){ return cocktails;}

    public void searchCocktail (String str){

        Retrofit retrofit= CocktailAPIClient.getClient();

        CocktailService cocktailService= retrofit.create(CocktailService.class);

        cocktailService.searchCocktail(str).enqueue(new Callback<Cocktails>() {
            @Override
            public void onResponse(Call<Cocktails> call, Response<Cocktails> response) {
                if(response.isSuccessful() && response.body()!=null){
                    cocktails.setValue((List<Cocktail>) response.body());
                }
            }

            @Override
            public void onFailure(Call<Cocktails> call, Throwable throwable) {

            }
        });
    }

}