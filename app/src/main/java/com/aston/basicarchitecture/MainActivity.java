package com.aston.basicarchitecture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import com.aston.basicarchitecture.pages.home.settings.favouriteTeam.TeamsRepo;
import com.aston.basicarchitecture.utils.AppConsts;
import com.aston.basicarchitecture.utils.motionsensors.Gyroscope;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private Gyroscope gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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
        updateNavBar();

        gyroscope = new Gyroscope(this);



        gyroscope.setGyroscopeListener(new Gyroscope.GyroscopeListener() {
            @Override
            public void onRotation(float dx, float dy, float dz) {
                Log.d("Rotations", "dx: " + dx + " dy: " + dy + " dz: " + dz);

                if(dy > 0.15) {
                    drawerLayout.open();
                }
                if(dy < -0.15) {
                    drawerLayout.close();
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
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

    public void setUpSharedPreferences() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();

        int numberOfGames = prefs.getInt("numberOfGames", -1);
        if(numberOfGames == -1) {
            editor.putInt("numberOfGames", 5).apply();
        }

        int recentGamesPlayerProfileOne = prefs.getInt("recentGamesPlayerProfileOne",-1);
        if(recentGamesPlayerProfileOne == -1) {
            editor.putInt(AppConsts.RECENT_GAMES_ONE, 1);
            editor.putInt(AppConsts.RECENT_GAMES_TWO, 3);
            editor.putInt(AppConsts.RECENT_GAMES_THREE, 4);
            editor.putInt(AppConsts.RECENT_GAMES_FOUR, 5).apply();
        }
    }

    public int readHeaderPreferences() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        return prefs.getInt(AppConsts.TEAM_FAVOURITE_KEY, -1);
    }

    public void updateNavBar() {
        int headerValue = readHeaderPreferences();
        NavigationView navView = findViewById(R.id.nav_view);
        View header = navView.getHeaderView(0);
        TextView view = header.findViewById(R.id.playerTeamName);
        ImageView imageView = header.findViewById(R.id.playerTeamLogo);
        MaterialCardView cardView = header.findViewById(R.id.userFavouriteTeamCard);

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
            view.setText("Basketball Bonaza");
            Picasso.get().load("https://logoeps.com/wp-content/uploads/2011/05/nba-logo-vector-01.png").fit().into(imageView);

        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo: Look into showing a new fragment

                /* Look into how to implement this.
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    Fragment fragmentDemo = new fragmentFavouriteTeam();
                    ft.add(R.id.fragmentContainerView, fragmentDemo, fragmentDemo.getClass().getSimpleName());
                    ft.addToBackStack(fragmentDemo.getClass().getSimpleName());
                    ft.commit();
                */
            }
        });

    }


}