package vn.edu.usth.dropbox.ui.files;

import android.widget.Button;
import android.widget.ImageView;

import androidx.lifecycle.ViewModel;

import vn.edu.usth.dropbox.R;

public class FilesViewModel extends ViewModel {
    public void changeButtonUploadLight(Button btn) {
        // change drawable left
        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_images_light, 0, 0, 0);
    }

    public void changeButtonUploadDark(Button btn) {
        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_images, 0, 0, 0);
    }

    public void changeButtonFolderLight(Button btn) {
        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_folder_light, 0, 0, 0);
    }

public void changeButtonFolderDark(Button btn) {
        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_folder, 0, 0, 0);
    }

    public void changeButtonScanLight(Button btn) {
        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_document_scanner_light, 0, 0, 0);
    }

    public void changeButtonScanDark(Button btn) {
        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_document_scanner, 0, 0, 0);
    }

    public void changeButtonFilesSortLight(Button btn) {
        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_down_light, 0, 0, 0);
    }

    public void changeButtonFilesSortDark(Button btn) {
        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_down, 0, 0, 0);
    }

    public void changeButtonFilesFilterLight(Button btn) {
        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_grid_view_light, 0, 0, 0);
    }

    public void changeButtonFilesFilterDark(Button btn) {
        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_grid_view, 0, 0, 0);
    }

    public void changeImageViewDotsVerticalLight(ImageView[] iv) {
        for (ImageView imageView : iv) {
            imageView.setImageResource(R.drawable.ic_dots_vertical_light);
        }
    }

    public void changeImageViewDotsVerticalDark(ImageView[] iv) {
        for (ImageView imageView : iv) {
            imageView.setImageResource(R.drawable.ic_dots_vertical);
        }
    }

}
