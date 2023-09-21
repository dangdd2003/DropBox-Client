package vn.edu.usth.dropbox.ui.upgradeAccount;

import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import vn.edu.usth.dropbox.R;

public class UpgradeAccountViewModel extends ViewModel {
    public void changeImageLight(ImageView iv) {
        iv.setImageResource(R.drawable.img_upgrade);
    }
    public void changeImageDark(ImageView iv) {
        iv.setImageResource(R.drawable.img_upgrade_dark);
    }
}
