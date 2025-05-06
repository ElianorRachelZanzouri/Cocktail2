package com.example.cocktail2.ui.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cocktail2.cocktails.Cocktail;

import java.util.ArrayList;
import java.util.List;

public class FavoriteViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<List<Cocktail>> favoriteCocktails= 
            new MutableLiveData<>(new ArrayList<>());
    
    /*public FavoriteViewModel{
        //database Firebase
    }*/

    public void clearFavorites(){
        List<Cocktail> myFavorites=favoriteCocktails.getValue();

        if(myFavorites!=null){
            for(Cocktail c:myFavorites){
                //remove Firebase
            }
            myFavorites.clear();
            favoriteCocktails.setValue(myFavorites);
        }
    }
    public LiveData<List<Cocktail>> getFavoriteCocktails(){
        return favoriteCocktails;
    }

    public boolean isFavorite(Cocktail cocktail) {
        List<Cocktail> myFavorites=favoriteCocktails.getValue();

        if(myFavorites!=null){
            for(Cocktail fav: myFavorites){
                if(fav.idDrink.equals(cocktail.idDrink)){
                    return true;
                }
            }
        }
        return false;
    }

    public void removeFavorite(Cocktail cocktail) {
        List<Cocktail> myFavorites=favoriteCocktails.getValue();

        if(myFavorites!=null && isFavorite(cocktail)){
            myFavorites.remove(cocktail);
            favoriteCocktails.setValue(myFavorites);
            //add in the Firebase
        }
    }

    public void addFavorite(Cocktail cocktail) {

        List<Cocktail> myFavorites=favoriteCocktails.getValue();

        if(myFavorites!=null && !isFavorite(cocktail)){
            myFavorites.add(cocktail);
            favoriteCocktails.setValue(myFavorites);
            //add in the Firebase
        }
    }

    /*public void loadFavoriteDB() {
        //Firebase
    }*/
}