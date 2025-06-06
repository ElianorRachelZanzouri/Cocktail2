package com.example.cocktail2.cocktailAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailAPIClient {
    final static String BASE_URL="https://www.thecocktaildb.com/api/json/v1/1/";

    public static Retrofit getClient(){
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


    }
}
