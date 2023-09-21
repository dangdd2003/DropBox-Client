package vn.edu.usth.dropbox.ui.files;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import vn.edu.usth.dropbox.R;
import vn.edu.usth.dropbox.databinding.FragmentFilesBinding;
import vn.edu.usth.dropbox.ui.home.HomeViewModel;


public class FilesFragment extends Fragment {

    private FragmentFilesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FilesViewModel filesViewModel = new ViewModelProvider(this).get(FilesViewModel.class);
        HomeViewModel homeViewModel = new HomeViewModel();
        binding = FragmentFilesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        EditText etSearch = binding.filesSearchBar;
        Button btnUpload = binding.filesUploadButton;
        Button btnFolder = binding.filesFolderButton;
        Button btnScan = binding.filesScanButton;
        Button btnSort = binding.filesSortButton;
        Button btnFilter = binding.filesFilterButton;

        // List of image views
        int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        ImageView[] imageViews = {binding.img1Dots, binding.img2Dots, binding.img3Dots, binding.img4Dots, binding.img5Dots, binding.img6Dots};
        if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_NO) {
            homeViewModel.changeEditTextSearchDark(etSearch);
            filesViewModel.changeButtonUploadDark(btnUpload);
            filesViewModel.changeButtonFolderDark(btnFolder);
            filesViewModel.changeButtonScanDark(btnScan);
            filesViewModel.changeButtonFilesSortDark(btnSort);
            filesViewModel.changeButtonFilesFilterDark(btnFilter);
            filesViewModel.changeImageViewDotsVerticalDark(imageViews);
        } else {
            homeViewModel.changeEditTextSearchLight(etSearch);
            filesViewModel.changeButtonUploadLight(btnUpload);
            filesViewModel.changeButtonFolderLight(btnFolder);
            filesViewModel.changeButtonScanLight(btnScan);
            filesViewModel.changeButtonFilesSortLight(btnSort);
            filesViewModel.changeButtonFilesFilterLight(btnFilter);
            filesViewModel.changeImageViewDotsVerticalLight(imageViews);
        }
        return root;
    }
}