package vn.edu.usth.dropbox.ui.home;

import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import vn.edu.usth.dropbox.R;

public class NotificationsViewModel extends ViewModel {
    public void changeImageViewInboxLight(ImageView[] ivInbox) {
        for (ImageView iv : ivInbox) {
            iv.setImageResource(R.drawable.ic_inbox_light);
        }
    }

    public void changeImageViewInboxDark(ImageView[] ivInbox) {
        for (ImageView iv : ivInbox) {
            iv.setImageResource(R.drawable.ic_inbox);
        }
    }
}
