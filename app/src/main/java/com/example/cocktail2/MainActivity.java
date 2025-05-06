package com.example.cocktail2;

import static com.example.cocktail2.R.id.navigation_favorites;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.cocktail2.ui.favorite.FavoriteFragment;
import com.example.cocktail2.ui.home.HomeFragment;
import com.example.cocktail2.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cocktail2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);

        bottomNavigationView.setOnItemSelectedListener(
                item -> {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.navigation_home) {
                        selectedFragment = new HomeFragment();
                    } else if (item.getItemId() == R.id.navigation_search) {
                        selectedFragment = new SearchFragment();
                    } else if (item.getItemId() == navigation_favorites)
                        {
                            selectedFragment = new FavoriteFragment();
                        }

                        if (selectedFragment != null) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, selectedFragment).commit();
                            return true;
                        }
                        return false;
                    }
        );
                    if (savedInstanceState == null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, new HomeFragment()).commit();
                    }


                }

    }