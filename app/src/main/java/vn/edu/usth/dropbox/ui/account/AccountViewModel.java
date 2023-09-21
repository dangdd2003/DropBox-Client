package vn.edu.usth.dropbox.ui.account;

import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import vn.edu.usth.dropbox.R;

public class AccountViewModel extends ViewModel {
    public void changeImageViewCameraLight(ImageView iv){
        iv.setImageResource(R.drawable.ic_camera_light);
    }
    public void changeImageViewCameraDark(ImageView iv){
        iv.setImageResource(R.drawable.ic_camera);
    }

    public void changeImageViewOfflineModeLight(ImageView iv){
        iv.setImageResource(R.drawable.ic_download_offline_light);
    }
    public void changeImageViewOfflineModeDark(ImageView iv){
        iv.setImageResource(R.drawable.ic_download_offline);
    }

}
