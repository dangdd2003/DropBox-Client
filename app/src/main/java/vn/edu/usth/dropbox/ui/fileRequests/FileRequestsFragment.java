package vn.edu.usth.dropbox.ui.fileRequests;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.dropbox.R;
import vn.edu.usth.dropbox.databinding.FragmentFileRequestsBinding;

public class FileRequestsFragment extends Fragment {

    private FragmentFileRequestsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFileRequestsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}