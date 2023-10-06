package vn.edu.usth.dropbox.ui.photos;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.dropbox.PhotoActivity;
import vn.edu.usth.dropbox.api.DropboxApiWrapper;
import vn.edu.usth.dropbox.databinding.FragmentPhotosBinding;

public class PhotosFragment extends Fragment implements PhotoInterface {

    private FragmentPhotosBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhotosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Recycler view
        PhotosAdapter photosAdapter = new PhotosAdapter(this);
        RecyclerView recyclerView = binding.photosRecyclerView;
        recyclerView.setAdapter(photosAdapter);
        recyclerView.setItemViewCacheSize(100);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        photosAdapter.setFiles(DropboxApiWrapper.getFiles());
        return root;
    }

    @Override
    public void onPhotoClick(int position) {
        Intent intent = new Intent(getContext(), PhotoActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);

    }
}
