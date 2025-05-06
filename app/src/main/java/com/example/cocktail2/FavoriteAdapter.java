package com.example.cocktail2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cocktail2.cocktails.Cocktail;
import com.example.cocktail2.ui.favorite.FavoriteViewModel;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    FavoriteViewModel favoriteViewModel;
    List<Cocktail> favoriteCocktails;
    Context context;

    public FavoriteAdapter(List<Cocktail> list, FavoriteViewModel favoriteViewModel, Context context){
        this.favoriteCocktails=list;
        this.favoriteViewModel=favoriteViewModel;
        this.context=context;
    }

    public void setFavoriteCocktails(List<Cocktail> favCocktail){
        this.favoriteCocktails=favCocktail;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite,parent,false);
        return new FavoriteViewHolder(view);
    }

    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position){
        Cocktail cocktail=favoriteCocktails.get(position);
        holder.cocktailName.setText(cocktail.strDrink);
        Glide.with(holder.itemView.getContext())
                .load(cocktail.strDrinkThumb)
                .into(holder.cocktailImage);

        holder.removeIcon.setOnClickListener(v->{
            favoriteViewModel.removeFavorite(cocktail);
            favoriteCocktails.remove(cocktail);
            notifyDataSetChanged();
            if(context!=null){
                Toast.makeText(context, "Remove from Favorites", Toast.LENGTH_LONG).show();
            }
        });
        holder.updateFavoriteIcon(cocktail);

        holder.cocktailImage.setOnClickListener(v->{
            long clickTime=System.currentTimeMillis();
            if(clickTime-holder.lastClickTime<400){
                if(favoriteViewModel.isFavorite(cocktail)){
                    favoriteViewModel.removeFavorite(cocktail);
                }
                else{
                    favoriteViewModel.addFavorite(cocktail);
                }
                notifyDataSetChanged();
            }
            holder.lastClickTime=clickTime;
        });
    }

    @Override
    public int getItemCount() {
        return favoriteCocktails.size();
    }


    //class FavoriteViewHolder
    class FavoriteViewHolder extends RecyclerView.ViewHolder{
        TextView cocktailName;
        ImageView cocktailImage,favoriteIcon,removeIcon;
        long lastClickTime=0;

        FavoriteViewHolder(View itemView){
            super(itemView);
            cocktailName=itemView.findViewById(R.id.txtCocktail);
            cocktailImage=itemView.findViewById(R.id.imageCocktail);
            favoriteIcon=itemView.findViewById(R.id.imageFavorite);
            removeIcon=itemView.findViewById(R.id.imageRemove);
        }
        //update status
        void updateFavoriteIcon(Cocktail cocktail){
            if (favoriteViewModel.isFavorite(cocktail)) {
                favoriteIcon.setImageResource(R.drawable.ic_favorite_redd);
            }
            else{
                favoriteIcon.setImageResource(R.drawable.ic_favorite);
            }
        }

    }
}
