package vn.edu.usth.dropbox.ui.files;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

import vn.edu.usth.dropbox.R;
import vn.edu.usth.dropbox.api.DropboxApiWrapper;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.ViewHolder> {
    private final FileInterface fileInterface;
    List<Metadata> files;
    public FilesAdapter(FileInterface fileInterface) {
        this.fileInterface = fileInterface;
    }

    public void setFiles(List<Metadata> files) {
        this.files = files;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fileView = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_items, parent, false);
        return new ViewHolder(fileView, fileInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FilesAdapter.ViewHolder holder, int position) {
        //wait for files to load
        Metadata metadata = files.get(position);
        System.out.println(metadata.getName());
        holder.fileName.setText(metadata.getName());
        if (metadata instanceof FileMetadata fileMetadata) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            String ext = fileMetadata.getName().substring(fileMetadata.getName().lastIndexOf(".") + 1);
            String type = mime.getMimeTypeFromExtension(ext);
            if (type != null && type.contains("image/")) {
                // use temporary image while loading
                Glide.with(holder.itemView.getContext()).load(R.drawable.ic_photo_grey_600_36dp).into(holder.fileImage);
                InputStream in;
                try {
                    in = DropboxApiWrapper.getClient().files().getThumbnailBuilder(fileMetadata.getPathLower())
                            .withFormat(ThumbnailFormat.JPEG)
                            .withSize(ThumbnailSize.W128H128)
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
                Glide.with(holder.itemView.getContext()).load(thumb).into(holder.fileImage);
            } else {
                // just a plain old file
                Glide.with(holder.itemView.getContext()).load(R.drawable.ic_insert_drive_file_blue_36dp).into(holder.fileImage);
            }
        } else {
            // just a plain old folder
            Glide.with(holder.itemView.getContext()).load(R.drawable.ic_folder_blue_36dp).into(holder.fileImage);
        }
    }

    @Override
    public int getItemCount() {
        if (files == null) {
            return 0;
        }
        return files.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView fileName;
        ImageView fileImage;

        public ViewHolder(@NonNull View itemView, FileInterface fileInterface) {
            super(itemView);
            fileName = itemView.findViewById(R.id.file_name);
            fileImage = itemView.findViewById(R.id.file_image);
            itemView.setOnClickListener(v -> {
                if (fileInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        fileInterface.onFileClick(position);
                    }

                }
            });
        }
    }
}
