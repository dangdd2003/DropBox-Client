package vn.edu.usth.dropbox.ui.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.edu.usth.dropbox.R;
import vn.edu.usth.dropbox.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AccountViewModel accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageView ivCamera = binding.accountImageViewCamera;
        ImageView ivOfflineMode = binding.accountImageViewOfflineMode;
        binding.signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), vn.edu.usth.dropbox.Login.class);
                startActivity(intent);
            }
        });

        int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_NO) {
            accountViewModel.changeImageViewCameraDark(ivCamera);
            accountViewModel.changeImageViewOfflineModeDark(ivOfflineMode);
        } else {
            accountViewModel.changeImageViewCameraLight(ivCamera);
            accountViewModel.changeImageViewOfflineModeLight(ivOfflineMode);
        }



        return root;
    }
}