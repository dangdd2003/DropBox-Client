package vn.edu.usth.dropbox.api;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.Metadata;

import java.util.List;

public class DropboxApiWrapper {
    static DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    static DbxClientV2 client = new DbxClientV2(config, BuildConfig.ACCESS_TOKEN);

    static List<Metadata> files;

    public static DbxClientV2 getClient() {
        return client;
    }

    public void getFiles() {
        try {
            files = client.files().listFolder("").getEntries();
            for (Metadata file : files) {
                System.out.println(file.getPathLower());
            }
        } catch (DbxException e) {
            e.printStackTrace();
        }
    }

}
