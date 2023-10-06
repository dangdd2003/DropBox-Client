package vn.edu.usth.dropbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.users.FullAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import vn.edu.usth.dropbox.api.DropboxApiWrapper;
import vn.edu.usth.dropbox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private NavController mNavController;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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

        // init api
        DropboxApiWrapper apiWrapper = new DropboxApiWrapper();
        apiWrapper.getListFiles();

        // Binding user info to navigation_header

        ImageView userAvatar = binding.navigationView.getHeaderView(0).findViewById(R.id.avatar_drawer);
        TextView userName = binding.navigationView.getHeaderView(0).findViewById(R.id.username_drawer);
        TextView userEmail = binding.navigationView.getHeaderView(0).findViewById(R.id.email_drawer);
        try {
            FullAccount account = DropboxApiWrapper.getClient().users().getCurrentAccount();
            userName.setText(account.getName().getDisplayName());
            userEmail.setText(account.getEmail());
            runOnUiThread(() -> {
                Glide.with(this).load(account.getProfilePhotoUrl()).into(userAvatar);
            });
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }

        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        }

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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.adjustmentBottomSheetDialog) {
            final BottomSheetDialog adjustmentBottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.SheetDialog);
            adjustmentBottomSheetDialog.setContentView(R.layout.bottom_sheet_dialoge_photos);
            adjustmentBottomSheetDialog.show();
            adjustmentBottomSheetDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        }
        return NavigationUI.onNavDestinationSelected(item, mNavController)
                || super.onOptionsItemSelected(item);
    }

    public NavController getNavController() {
        return mNavController;
    }

    @Override
    public void recreate() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= 34) {
            clearOverrideActivityTransition(OVERRIDE_TRANSITION_OPEN);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= 34) {
            clearOverrideActivityTransition(OVERRIDE_TRANSITION_CLOSE);
        }
    }
}