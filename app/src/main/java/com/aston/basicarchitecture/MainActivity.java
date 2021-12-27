package com.aston.basicarchitecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set Toolbar

        Toolbar toolbar = findViewById(R.id.toolbar);


        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.landingPage,
                R.id.teams,
                R.id.schedule,
                R.id.players,
                R.id.stadiums,
                R.id.settings
        ).setDrawerLayout(drawerLayout).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationView navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navView, navController);


        View header = navView.getHeaderView(0);
        ImageButton b = header.findViewById(R.id.drawer_close_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        MaterialCardView cardView = header.findViewById(R.id.userFavouriteTeamCard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("String Help!", "String Help!");
                //ToDo: Link to change teams.
            }
        });


        setUpSharedPreferences();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

    public void setUpSharedPreferences() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();

        int numberOfGames = prefs.getInt("numberOfGames", -1);
        if(numberOfGames == -1) {
            editor.putInt("numberOfGames", 5);
        }

        int recentGamesPlayerProfileOne = prefs.getInt("recentGamesPlayerProfileOne",-1);
        if(recentGamesPlayerProfileOne == -1) {
            editor.putInt("recentGamesPlayerProfileOne", 1);
            editor.putInt("recentGamesPlayerProfileTwo", 3);
            editor.putInt("recentGamesPlayerProfileThree", 4);
            editor.putInt("recentGamesPlayerProfileFour", 5);
        }
    }


}