package com.example.cocktail2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.cocktail2.cocktails.Cocktail;

public class DetailsCocktail  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        ImageView image=findViewById(R.id.imageCocktail);
        TextView txtCocktail=findViewById(R.id.txtCocktailNameDetail);
        TextView txtInstruction=findViewById(R.id.txtInstruction);
        TextView txtIngredient=findViewById(R.id.txtIngredient);

        //take the cocktail
        Cocktail cocktail=(Cocktail) getIntent().getSerializableExtra("cocktail");

        assert cocktail!=null;
        txtCocktail.setText(cocktail.strDrink);
        txtIngredient.setText(cocktail.strInstructions);
        Glide.with(this)//this page
                .load(cocktail.strDrinkThumb)
                .into(image);

        //Set Text Ingredients
        StringBuilder ingredients=new StringBuilder();
        if(cocktail.strIngredient1!=null) ingredients.append(cocktail.strIngredient1).append("/n");
        if(cocktail.strIngredient2!=null) ingredients.append(cocktail.strIngredient2).append("/n");
        if(cocktail.strIngredient3!=null) ingredients.append(cocktail.strIngredient3).append("/n");
        if(cocktail.strIngredient4!=null) ingredients.append(cocktail.strIngredient4).append("/n");
        if(cocktail.strIngredient5!=null) ingredients.append(cocktail.strIngredient5).append("/n");
        if(cocktail.strIngredient6!=null) ingredients.append(cocktail.strIngredient6).append("/n");
        if(cocktail.strIngredient7!=null) ingredients.append(cocktail.strIngredient7).append("/n");
        if(cocktail.strIngredient8!=null) ingredients.append(cocktail.strIngredient8).append("/n");
        if(cocktail.strIngredient9!=null) ingredients.append(cocktail.strIngredient9).append("/n");
        if(cocktail.strIngredient10!=null) ingredients.append(cocktail.strIngredient10).append("/n");
        if(cocktail.strIngredient11!=null) ingredients.append(cocktail.strIngredient11).append("/n");
        if(cocktail.strIngredient12!=null) ingredients.append(cocktail.strIngredient12).append("/n");
        if(cocktail.strIngredient13!=null) ingredients.append(cocktail.strIngredient13).append("/n");
        if(cocktail.strIngredient14!=null) ingredients.append(cocktail.strIngredient14).append("/n");
        if(cocktail.strIngredient15!=null) ingredients.append(cocktail.strIngredient15).append("/n");


        //in the application
        txtIngredient.setText(ingredients.toString().trim());






        }

}
