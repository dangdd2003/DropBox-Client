package vn.edu.usth.dropbox.api;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.Metadata;

import java.util.List;

public class DropboxApiWrapper {
    static DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    static DbxClientV2 client = new DbxClientV2(config, BuildConfig.ACCESS_TOKEN);

    static List<Metadata> files;
    static List<Metadata> photos;

    public static DbxClientV2 getClient() {
        return client;
    }

    public static List<Metadata> getFiles() {
        return files;
    }

    public static List<Metadata> getPhotos() {
        return photos;
    }

    public void getListFiles() {
        try {
            files = client.files().listFolder("").getEntries();
            for (Metadata file : files) {
                System.out.println(file.getPathLower());
            }
        } catch (DbxException e) {
            e.printStackTrace();
        }
    }

    public void getListPhotos () {
        try {
            photos = client.files().listFolder("").getEntries();
            // filter out non-image files
            photos.removeIf(file -> !file.getName().endsWith(".jpg"));
            for (Metadata file : photos) {
                System.out.println(file.getPathLower());
            }
        } catch (DbxException e) {
            throw new RuntimeException(e);
        }
    }
}
