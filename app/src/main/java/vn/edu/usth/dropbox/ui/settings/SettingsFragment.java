package vn.edu.usth.dropbox.ui.settings;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import vn.edu.usth.dropbox.MainActivity;
import vn.edu.usth.dropbox.R;
import vn.edu.usth.dropbox.databinding.FragmentSettingsBinding;
import vn.edu.usth.dropbox.ui.upgradeAccount.UpgradeAccountFragment;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.upgradeButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_in, R.anim.slide_out, R.anim.slide_out);
            Fragment upgradeAccountFragment = new UpgradeAccountFragment();
            fragmentTransaction
                    .replace(R.id.navigation_host_fragment, upgradeAccountFragment)
                    .addToBackStack(null)
                    .commit();

        });

        binding.spaceUsed.setOnClickListener(v -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_in, R.anim.slide_out, R.anim.slide_out);
            Fragment upgradeAccountFragment = new UpgradeAccountFragment();
            fragmentTransaction
                    .replace(R.id.navigation_host_fragment, upgradeAccountFragment)
                    .addToBackStack("h")
                    .commit();
        });

        binding.themeSwitch.setOnClickListener(v -> {
            SettingsFragmentViewModel settingsFragmentViewModel = new ViewModelProvider(this).get(SettingsFragmentViewModel.class);
            PopupMenu popupMenu = new PopupMenu(getContext(), binding.themeSwitch);
            popupMenu.getMenuInflater().inflate(R.menu.theme_switcher, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.follow_system) {
                    settingsFragmentViewModel.system_theme_switch();
                }
                else if (item.getItemId() == R.id.always_dark) {
                    settingsFragmentViewModel.dark_theme_switch();
                }
                else if (item.getItemId() == R.id.always_light) {
                    settingsFragmentViewModel.light_theme_switch();
                }
            return true;
        });
            popupMenu.show();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}