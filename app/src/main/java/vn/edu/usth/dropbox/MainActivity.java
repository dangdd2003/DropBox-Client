package vn.edu.usth.dropbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import vn.edu.usth.dropbox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {



    private NavController mNavController;
    private AppBarConfiguration mAppBarConfiguration;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Toolbar toolbar = binding.toolbar;
        DrawerLayout drawerLayout = binding.drawerLayout;
        NavigationView navigationView = binding.navigationView;
        BottomNavigationView bottomNavigationView = binding.bottomNavigationMenu;



        setSupportActionBar(toolbar);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.filesFragment, R.id.photosFragment, R.id.accountFragment)
                .setOpenableLayout(drawerLayout)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_host_fragment);
        assert navHostFragment != null;
        mNavController = navHostFragment.getNavController();
        // Disable bottom navigation view for some fragments inside drawer_menu and toolbar_menu
        mNavController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.notificationsFragment || destination.getId() == R.id.uploadFragment || destination.getId() == R.id.settingsFragment || destination.getId() == R.id.fileRequestsFragment || destination.getId() == R.id.upgradeAccountFragment) {
                bottomNavigationView.setVisibility(View.GONE);
            } else {
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });
        NavigationUI.setupWithNavController(navigationView, mNavController);
        NavigationUI.setupWithNavController(toolbar, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, mNavController);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar toolbar = binding.toolbar;

        // Check current theme mode
        int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        mNavController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.homeFragment) {
                toolbar.getMenu().clear();
                if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_NO) {
                    toolbar.inflateMenu(R.menu.home_menu_dark);
                } else {
                    toolbar.inflateMenu(R.menu.home_menu_light);
                }
            } else if (destination.getId() == R.id.filesFragment) {
                toolbar.getMenu().clear();
                if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_NO) {
                    toolbar.inflateMenu(R.menu.files_menu_dark);
                } else {
                    toolbar.inflateMenu(R.menu.files_menu_light);
                }
            } else if (destination.getId() == R.id.photosFragment) {
                toolbar.getMenu().clear();
                if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_NO) {
                    toolbar.inflateMenu(R.menu.photos_menu_dark);
                } else {
                    toolbar.inflateMenu(R.menu.photos_menu_light);
                }
            } else {
                toolbar.getMenu().clear();
            }
        });
//        mNavController.addOnDestinationChangedListener((controller, destination, arguments) -> {
//            if (destination.getId() == R.id.homeFragment) {
//                toolbar.getMenu().clear();
//                toolbar.inflateMenu(R.menu.home_menu_dark);
//            } else if (destination.getId() == R.id.filesFragment) {
//                toolbar.getMenu().clear();
//                toolbar.inflateMenu(R.menu.files_menu_dark);
//            } else if (destination.getId() == R.id.photosFragment) {
//                toolbar.getMenu().clear();
//                toolbar.inflateMenu(R.menu.photos_menu_dark);
//            } else {
//                toolbar.getMenu().clear();
//            }
//        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, mNavController)
                || super.onOptionsItemSelected(item);
    }

    public NavController getNavController() {
        return mNavController;
    }


}