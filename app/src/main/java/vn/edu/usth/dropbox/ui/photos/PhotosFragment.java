package vn.edu.usth.dropbox.ui.photos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.dropbox.R;
import vn.edu.usth.dropbox.databinding.FragmentPhotosBinding;

public class PhotosFragment extends Fragment {

    private FragmentPhotosBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhotosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
