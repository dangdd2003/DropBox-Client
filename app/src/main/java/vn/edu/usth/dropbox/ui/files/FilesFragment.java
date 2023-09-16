package vn.edu.usth.dropbox.ui.files;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.dropbox.databinding.FragmentFilesBinding;


public class FilesFragment extends Fragment {

    private FragmentFilesBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}