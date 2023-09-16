package vn.edu.usth.dropbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import vn.edu.usth.dropbox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbarHome;
        DrawerLayout drawerLayout = binding.drawerLayout;
        NavigationView navigationView = binding.navigationView;
        BottomNavigationView bottomNavigationView = binding.bottomNavigationMenu;

        setSupportActionBar(toolbar);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.settingsFragment, R.id.fileRequestsFragment, R.id.upgradeAccountFragment, R.id.homeFragment, R.id.filesFragment, R.id.photosFragment, R.id.accountFragment)
                .setOpenableLayout(drawerLayout)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(toolbar, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = NavHostFragment.findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.navigation_host_fragment)));
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        binding.toolbarHome.inflateMenu(R.menu.home_toolbar_menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = NavHostFragment.findNavController(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.navigation_host_fragment)));
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);

    }
}