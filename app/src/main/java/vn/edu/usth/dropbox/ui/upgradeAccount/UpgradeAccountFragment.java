package vn.edu.usth.dropbox.ui.upgradeAccount;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.edu.usth.dropbox.R;
import vn.edu.usth.dropbox.databinding.FragmentAccountBinding;
import vn.edu.usth.dropbox.databinding.FragmentUpgradeAccountBinding;

public class UpgradeAccountFragment extends Fragment {

    private FragmentUpgradeAccountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UpgradeAccountViewModel upgradeAccountViewModel = new ViewModelProvider(this).get(UpgradeAccountViewModel.class);
        binding = FragmentUpgradeAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Check if the system mode is dark or light
        int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        ImageView iv = binding.upgradeImageView;
        if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_NO) {
            upgradeAccountViewModel.changeImageLight(iv);
        } else {
            upgradeAccountViewModel.changeImageDark(iv);
        }

        return root;
    }
}