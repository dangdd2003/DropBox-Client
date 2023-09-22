package vn.edu.usth.dropbox.ui.home;

import static android.content.res.Configuration.UI_MODE_NIGHT_MASK;
import static android.content.res.Configuration.UI_MODE_NIGHT_NO;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.usth.dropbox.R;
import vn.edu.usth.dropbox.databinding.FragmentUploadBinding;

public class UploadFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentUploadBinding binding = FragmentUploadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageView main_img = binding.mainImage;

        if ((getResources().getConfiguration().uiMode & UI_MODE_NIGHT_MASK) == UI_MODE_NIGHT_NO) {
            lightImgSwitch(main_img);
        } else {
            darkImgSwitch(main_img);
        }

        return root;
    }
    public void lightImgSwitch(ImageView img) {
        img.setImageResource(R.drawable.img_upload_light);
    }

    public void darkImgSwitch(ImageView img) {
        img.setImageResource(R.drawable.img_upload_dark);
    }
}
