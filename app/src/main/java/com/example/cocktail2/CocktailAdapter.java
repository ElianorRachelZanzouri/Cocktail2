package com.example.cocktail2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cocktail2.cocktails.Cocktail;
import com.example.cocktail2.cocktails.Cocktails;

import java.util.List;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder> {

    private List<Cocktail> cocktails;
    private final OnCocktailClickListener onCocktailClickListener;
    private final OnFavoriteClickListener onFavoriteClickListener;
    private List<Cocktail> favoritesCocktails;


    @SuppressLint("NotifyDataSetChanged")
    public CocktailAdapter(List<Cocktail> cocktails,
                           OnCocktailClickListener onCocktailClickListener,
                           OnFavoriteClickListener onFavoriteClickListener
                           ){
        this.cocktails=cocktails;
        this.onCocktailClickListener=onCocktailClickListener;
        this.onFavoriteClickListener=onFavoriteClickListener;

    }
    @SuppressLint("NotifyDataSetChanged")
    public void setCocktails(List<Cocktail> cocktailList){
        this.cocktails=cocktailList;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFavoritesCocktails(List<Cocktail> favoritesCocktails){
        this.favoritesCocktails=favoritesCocktails;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cocktail,parent,false);
        return new CocktailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder holder, int position){
        Cocktail cocktail=cocktails.get(position);//in the cocktails (list), search with the position
        holder.bind(cocktail,onCocktailClickListener,onFavoriteClickListener);
    }

    @Override
    public int getItemCount() {
        return cocktails!=null ? cocktails.size(): 0;
    }

    public boolean isFavorite(Cocktail cocktail){
        if(favoritesCocktails==null){
            return false;
        }
        for(Cocktail fav: favoritesCocktails){
            if (fav.idDrink.equals(cocktail.idDrink)){
                return true;
            }
        }
        return false;
    }


    //OnCocktailClickListener
    public interface OnCocktailClickListener{
        void onCocktailClick(Cocktail cocktail) ;
    }
    //OnFavoriteClickListener
    public interface OnFavoriteClickListener{
        void onFavoriteClick(Cocktail cocktail);
    }

    //View Holder class
    class CocktailViewHolder extends RecyclerView.ViewHolder{

        ImageView imageCocktail, imageInfo, imageFavorite;
        TextView txtCocktailName;

        long lastClickTime=0;

        CocktailViewHolder(@NonNull View itemView){
            super(itemView);
            imageCocktail=itemView.findViewById(R.id.imageCocktail);
            imageInfo=itemView.findViewById(R.id.imageBtnInfo);
            imageFavorite=itemView.findViewById(R.id.imageFavorite);

            txtCocktailName=itemView.findViewById(R.id.txtCocktailName);
        }
        void bind(Cocktail cocktail, OnCocktailClickListener onCocktailClickListener, OnFavoriteClickListener onFavoriteClickListener){
            txtCocktailName.setText(cocktail.strDrink);
            String image=cocktail.strDrinkThumb;
            assert image!=null;
            Glide.with(itemView.getContext())
                    .load(image)
                    .into(imageCocktail);

            updateFavoriteIcon(cocktail);

            imageInfo.setOnClickListener(v->{
                onCocktailClickListener.onCocktailClick(cocktail);});


            //variable
            View.OnClickListener favoriteClickListener=v->{
                long clickTime= System.currentTimeMillis();
                if (clickTime - lastClickTime <400){
                    onFavoriteClickListener.onFavoriteClick(cocktail);
                    updateFavoriteIcon(cocktail);
                    lastClickTime=0;
                }
                else{
                    lastClickTime=clickTime;
                }
            };
        }

        private void updateFavoriteIcon(Cocktail cocktail) {
            if(isFavorite(cocktail)){//like
                imageFavorite.setImageResource(R.drawable.ic_favorite_redd);
            }
            else{//unlike
                imageFavorite.setImageResource(R.drawable.ic_favorite);
            }
        }
    }
}
