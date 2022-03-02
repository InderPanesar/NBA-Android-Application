package com.aston.basketballapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.aston.basketballapp.pages.home.settings.favouriteTeam.TeamsRepo;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.DrawerLayoutControl;
import com.aston.basketballapp.utils.motionsensors.Gyroscope;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import dagger.hilt.android.AndroidEntryPoint;

//Initial Point of Entry for the Application
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements DrawerLayoutControl {

    private Gyroscope gyroscope;
    private boolean enabledDrawerMenu = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);
        //Set Toolbar

        Toolbar toolbar = findViewById(R.id.toolbar);


        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_view);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.landingPage,
                R.id.teams,
                R.id.schedule,
                R.id.stadiums,
                R.id.settings
        ).setOpenableLayout(drawerLayout).build();
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

        MaterialCardView cardView = header.findViewById(R.id.user_favourite_team_card);

        setUpSharedPreferences();
        updateNavigationMenuHeader();

        gyroscope = new Gyroscope(this);



        gyroscope.setGyroscopeListener(new Gyroscope.GyroscopeListener() {
            @Override
            public void onRotation(float dx, float dy, float dz) {
                if(dz > 0.5f) {
                    if(enabledDrawerMenu) {
                        drawerLayout.open();
                    }
                } else if(dz < -0.5f) {
                    if(drawerLayout.isOpen()) {
                        drawerLayout.close();
                    }
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        gyroscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gyroscope.unregister();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.fragment_container_view);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

    //Setup the shared preferences for the App and check if the values exist and if not set default values.
    public void setUpSharedPreferences() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();

        boolean correctValue = prefs.getBoolean(AppConsts.VALUES_SET, false);
        if(!correctValue) {
            editor.putBoolean(AppConsts.VALUES_SET, true);
            editor.putInt(AppConsts.RECENT_GAMES_ONE, 1);
            editor.putInt(AppConsts.RECENT_GAMES_TWO, 3);
            editor.putInt(AppConsts.RECENT_GAMES_THREE, 4);
            editor.putInt(AppConsts.RECENT_GAMES_FOUR, 5).apply();
        }
    }

    //Get The players favourite team.
    public int readHeaderPreferences() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        return prefs.getInt(AppConsts.TEAM_FAVOURITE_KEY, -1);
    }

    //Method to update the Navigation Menu Header when user changes their favourite team.
    public void updateNavigationMenuHeader() {
        int headerValue = readHeaderPreferences();
        NavigationView navView = findViewById(R.id.nav_view);
        View header = navView.getHeaderView(0);
        TextView view = header.findViewById(R.id.player_team_name);
        ImageView imageView = header.findViewById(R.id.player_team_logo);
        MaterialCardView cardView = header.findViewById(R.id.user_favourite_team_card);

        if(headerValue != -1) {
            TeamsRepo repo = new TeamsRepo();
            for(TeamsRepo.LocalTeam team : repo.getTeamList()) {
                if(headerValue == team.getId()) {
                    view.setText(team.getName());
                    Picasso.get().load(team.getLogoURL()).fit().centerCrop().into(imageView);
                }
            }
        }
        else {
            view.setText(getBaseContext().getString(R.string.basic_title));
            Picasso.get().load("https://logoeps.com/wp-content/uploads/2011/05/nba-logo-vector-01.png").fit().into(imageView);

        }


    }

    @Override
    public void setDrawerEnabled(boolean enabled) {
        if(enabled) {
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
        else {
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        this.enabledDrawerMenu = enabled;
    }
}