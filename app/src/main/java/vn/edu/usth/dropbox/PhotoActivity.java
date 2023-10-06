package vn.edu.usth.dropbox;

import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.ThumbnailFormat;
import com.dropbox.core.v2.files.ThumbnailSize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import vn.edu.usth.dropbox.api.DropboxApiWrapper;
import vn.edu.usth.dropbox.databinding.ActivityPhotoBinding;

public class PhotoActivity extends AppCompatActivity {

    ActivityPhotoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ImageView photoImage = binding.photoImageFullscreen;
        int position = getIntent().getIntExtra("position", 0);

        new Thread(() -> {
            List<Metadata> files = DropboxApiWrapper.getFiles();
            Metadata metadata = files.get(position);
            if (metadata instanceof FileMetadata fileMetadata) {
                MimeTypeMap mime = MimeTypeMap.getSingleton();
                String ext = fileMetadata.getName().substring(fileMetadata.getName().lastIndexOf(".") + 1);
                String type = mime.getMimeTypeFromExtension(ext);
                System.out.println(type);
                if ( type != null && type.contains("image/")) {
                    // use temporary image while loading
                    runOnUiThread(() -> {
                        Glide.with(this).load(R.drawable.ic_photo_grey_600_36dp).into(photoImage);
                    });
                    InputStream in;
                    try {
                        in = DropboxApiWrapper.getClient().files().getThumbnailBuilder(fileMetadata.getPathLower())
                                .withFormat(ThumbnailFormat.JPEG)
                                .withSize(ThumbnailSize.W2048H1536)
                                .start()
                                .getInputStream();
                        System.out.println("got input stream");
                    } catch (DbxException e) {
                        throw new RuntimeException(e);
                    }
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int bytesRead;
                    while (true) {
                        try {
                            if (!((bytesRead = in.read(buf)) > 0)) break;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        out.write(buf, 0, bytesRead);
                    }
                    byte[] thumb = out.toByteArray();
                    System.out.println("got thumb");
                    runOnUiThread(() -> {
                        Glide.with(this).load(thumb).into(photoImage);
                    });
                }
            }
        }).start();
    }
}
