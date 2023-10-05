package vn.edu.usth.dropbox;

import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
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
import vn.edu.usth.dropbox.databinding.ActivityFileBinding;

public class FileActivity extends AppCompatActivity {
    ActivityFileBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ImageView fileImage = binding.fileImageFullscreen;
        TextView fileName = binding.fileDataFullscreen;
        int position = getIntent().getIntExtra("position", 0);

        new Thread(() -> {
            DropboxApiWrapper apiWrapper = new DropboxApiWrapper();
            List<Metadata> files = apiWrapper.getFiles();
            Metadata metadata = files.get(position);
            System.out.println(metadata.getName());
            if (metadata instanceof FileMetadata) {
                FileMetadata fileMetadata = (FileMetadata) metadata;
                MimeTypeMap mime = MimeTypeMap.getSingleton();
                String ext = fileMetadata.getName().substring(fileMetadata.getName().lastIndexOf(".") + 1);
                String type = mime.getMimeTypeFromExtension(ext);
                if (type != null && type.contains("image/")) {
                    // use temporary image while loading
                    runOnUiThread(() -> {
                        Glide.with(this).load(R.drawable.ic_photo_grey_600_36dp).into(fileImage);
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
                        Glide.with(this).load(thumb).into(fileImage);
                    });
                } else {
                    // get the contents of the file
                    InputStream in;
                    try {
                        in = DropboxApiWrapper.getClient().files().download(fileMetadata.getPathLower()).getInputStream();
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
                    System.out.println("got file");
                    // set the text view to the contents of the file
                    runOnUiThread(() -> {
                        fileName.setText(out.toString());
                    });
                }
            } else {
                // Get all the files in the folder
                StringBuilder stringBuilder = new StringBuilder();
                try {
                    List<Metadata> folderFiles = DropboxApiWrapper.getClient().files().listFolder(metadata.getPathLower()).getEntries();
                    for (Metadata file : folderFiles) {
                        stringBuilder.append(file.getName()).append("\n");
                    }
                } catch (DbxException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("got folder");
                runOnUiThread(() -> {
                    fileName.setText(stringBuilder.toString());
                });
            }
        }).start();
    }
}
