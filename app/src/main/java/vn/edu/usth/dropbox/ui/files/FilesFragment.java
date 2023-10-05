package vn.edu.usth.dropbox.ui.files;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import vn.edu.usth.dropbox.FileActivity;
import vn.edu.usth.dropbox.api.DropboxApiWrapper;
import vn.edu.usth.dropbox.databinding.FragmentFilesBinding;
import vn.edu.usth.dropbox.ui.home.HomeViewModel;


public class FilesFragment extends Fragment implements FileInterface{

    private FragmentFilesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FilesViewModel filesViewModel = new ViewModelProvider(this).get(FilesViewModel.class);
        HomeViewModel homeViewModel = new HomeViewModel();
        binding = FragmentFilesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // List of buttons and edit text
        EditText etSearch = binding.filesSearchBar;
        Button btnUpload = binding.filesUploadButton;
        Button btnFolder = binding.filesFolderButton;
        Button btnScan = binding.filesScanButton;
        Button btnSort = binding.filesSortButton;
        Button btnFilter = binding.filesFilterButton;

        // List of image views
        int currentNightMode = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_NO) {
            homeViewModel.changeEditTextSearchDark(etSearch);
            filesViewModel.changeButtonUploadDark(btnUpload);
            filesViewModel.changeButtonFolderDark(btnFolder);
            filesViewModel.changeButtonScanDark(btnScan);
            filesViewModel.changeButtonFilesSortDark(btnSort);
            filesViewModel.changeButtonFilesFilterDark(btnFilter);
        } else {
            homeViewModel.changeEditTextSearchLight(etSearch);
            filesViewModel.changeButtonUploadLight(btnUpload);
            filesViewModel.changeButtonFolderLight(btnFolder);
            filesViewModel.changeButtonScanLight(btnScan);
            filesViewModel.changeButtonFilesSortLight(btnSort);
            filesViewModel.changeButtonFilesFilterLight(btnFilter);
        }

        // Recycler view
        DropboxApiWrapper apiWrapper = new DropboxApiWrapper();
        apiWrapper.getListFiles();
        FilesAdapter filesAdapter = new FilesAdapter(this);
        RecyclerView recyclerView = binding.filesRecyclerView;
        recyclerView.setAdapter(filesAdapter);
        recyclerView.setItemViewCacheSize(100);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        filesAdapter.setFiles(apiWrapper.getFiles());

        return root;
    }

    @Override
    public void onFileClick(int position) {
        Intent intent = new Intent(getContext(), FileActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}