package vn.edu.usth.dropbox.ui.photos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
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

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private final PhotoInterface photoInterface;
    List<Metadata> files;

    public PhotosAdapter(PhotoInterface photoInterface) {
        this.photoInterface = photoInterface;
    }

    public void setFiles(List<Metadata> files){
        this.files = files;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fileView = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_items, parent, false);
        return new ViewHolder(fileView, photoInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter.ViewHolder holder, int position) {

        //wait for files to load
        Metadata metadata = files.get(position);
        if (metadata instanceof FileMetadata fileMetadata) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            String ext = fileMetadata.getName().substring(fileMetadata.getName().lastIndexOf(".") + 1);
            String type = mime.getMimeTypeFromExtension(ext);
            System.out.println(type);
            assert type != null;
            if (type.contains("text/")){
                holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(0,0));
            }
            if (type.contains("image/")) {
                // use temporary image while loading
                InputStream in;
                try {
                    in = DropboxApiWrapper.getClient().files().getThumbnailBuilder(fileMetadata.getPathLower())
                            .withFormat(ThumbnailFormat.JPEG)
                            .withSize(ThumbnailSize.W2048H1536)
                            .start()
                            .getInputStream();
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
                Glide.with(holder.itemView.getContext())
                        .load(thumb)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(holder.photoImage);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (files == null) {
            return 0;
        }
        return files.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photoImage;
        public ViewHolder(@NonNull View itemView, PhotoInterface photoInterface) {
            super(itemView);
            photoImage = itemView.findViewById(R.id.photo_image);
            itemView.setOnClickListener(v -> {
                if (photoInterface != null) {
                    int position = getAdapterPosition();
                    if ( position != RecyclerView.NO_POSITION){
                        photoInterface.onPhotoClick(position);
                    }
                }
            });
        }
    }
}
