package vn.edu.usth.dropbox.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.edu.usth.dropbox.databinding.FragmentUploadBinding;


public class UploadFragment extends Fragment {

    private FragmentUploadBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUploadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}