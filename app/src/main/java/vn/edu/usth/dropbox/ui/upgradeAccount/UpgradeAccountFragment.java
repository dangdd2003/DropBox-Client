package vn.edu.usth.dropbox.ui.upgradeAccount;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.dropbox.R;
import vn.edu.usth.dropbox.databinding.FragmentAccountBinding;
import vn.edu.usth.dropbox.databinding.FragmentUpgradeAccountBinding;

public class UpgradeAccountFragment extends Fragment {

    private FragmentUpgradeAccountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpgradeAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}