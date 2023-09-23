package vn.edu.usth.dropbox.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import vn.edu.usth.dropbox.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        EditText etSearch = binding.honeEditTextSearch;
        ImageView ivStarred = binding.homeImageViewStarred;
        ImageView ivViewedLinks = binding.homeImageViewViewedLinks;
        ImageView ivShared = binding.homeImageViewShared;
        ImageView ivOffline = binding.homeImageViewOffline;
        ImageView ivStarredImage = binding.imgStar;
        ImageView ivLinksImage = binding.imgLinks;
        ImageView ivSharedImage = binding.imgShared;
        ImageView ivOfflineImage = binding.imgOffline;


        int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_NO) {
            homeViewModel.changeEditTextSearchDark(etSearch);
            homeViewModel.changeImageViewStarredDark(ivStarred);
            homeViewModel.changeImageViewViewedLinksDark(ivViewedLinks);
            homeViewModel.changeImageViewSharedDark(ivShared);
            homeViewModel.changeImageViewOfflineDark(ivOffline);
            homeViewModel.changeImageStarredDark(ivStarredImage);
            homeViewModel.changeImageLinksDark(ivLinksImage);
            homeViewModel.changeImageSharedDark(ivSharedImage);
            homeViewModel.changeImageOfflineDark(ivOfflineImage);
        } else {
            homeViewModel.changeEditTextSearchLight(etSearch);
            homeViewModel.changeImageViewStarredLight(ivStarred);
            homeViewModel.changeImageViewViewedLinksLight(ivViewedLinks);
            homeViewModel.changeImageViewSharedLight(ivShared);
            homeViewModel.changeImageViewOfflineLight(ivOffline);
            homeViewModel.changeImageStarredLight(ivStarredImage);
            homeViewModel.changeImageLinksLight(ivLinksImage);
            homeViewModel.changeImageSharedLight(ivSharedImage);
            homeViewModel.changeImageOfflineLight(ivOfflineImage);
        }


        return root;
    }
}