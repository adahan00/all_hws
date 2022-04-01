package kg.geektech.newsapplite;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.io.IOException;

import kg.geektech.newsapplite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {




    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.profileFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Prefs prefs = new Prefs(this);
        if (!prefs.isBoardingShown())
            navController.navigate(R.id.numberFragment);
        //prefs.saveBoardState();


          navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
              @Override
              public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                  if( navDestination.getId() == R.id.boardFragment) {
                      navView.setVisibility(View.GONE);
                      getSupportActionBar().hide();
                  }else {
                      navView.setVisibility(View.VISIBLE);
                      getSupportActionBar().show();
                  }
              }
          });
          File file = new File(getCacheDir(),"note.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File folder = new File(getCacheDir(),"Images");
          folder.mkdir();
          folder.listFiles();

    }


}