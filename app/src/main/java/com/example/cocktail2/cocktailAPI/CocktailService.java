package com.example.cocktail2.cocktailAPI;

import com.example.cocktail2.cocktails.Cocktails;
import com.example.cocktail2.cocktails.Cocktails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CocktailService {
    @GET("/search.php")
    Call<Cocktails> getCocktailsByLetter(@Query("f") String letter);

    @GET("/search.php")
    Call<Cocktails> searchCocktail(@Query("s") String Query);
}
