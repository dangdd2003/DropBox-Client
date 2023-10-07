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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.users.FullAccount;

import vn.edu.usth.dropbox.R;
import vn.edu.usth.dropbox.api.DropboxApiWrapper;
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
        binding.signOutRelativeLayout.setOnClickListener(new View.OnClickListener() {
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
        // api
        ImageView userAvatar = binding.avatarImageView;
        TextView userName = binding.usernameTextView;
        TextView userEmail = binding.emailView;

        try {
            FullAccount account = DropboxApiWrapper.getClient().users().getCurrentAccount();
            userName.setText(account.getName().getDisplayName());
            userEmail.setText(account.getEmail());
            Glide.with(this).load(account.getProfilePhotoUrl()).into(userAvatar);
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }
        return root;
    }
}