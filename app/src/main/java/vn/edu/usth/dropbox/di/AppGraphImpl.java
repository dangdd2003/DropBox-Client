package vn.edu.usth.dropbox.di;

import android.content.Context;

import vn.edu.usth.dropbox.api.DropboxApiWrapper;
import vn.edu.usth.dropbox.api.DropboxAppConfig;
import vn.edu.usth.dropbox.api.DropboxCredentialUtils;
import vn.edu.usth.dropbox.api.DropboxOAuth2Utils;

public class AppGraphImpl implements AppGraph {

    DropboxAppConfig dropboxAppConfig;

    private final Context context;

    public AppGraphImpl(Context context) {
        this.context = context;
    }

    @Override
    public DropboxCredentialUtils dropboxCredentialUtils() {
        return new DropboxCredentialUtils(context.getApplicationContext());
    }

    @Override
    public DropboxOAuth2Utils dropboxOAuth2Utils() {
        return new DropboxOAuth2Utils(dropboxCredentialUtils(), dropboxAppConfig);
    }

    @Override
    public DropboxApiWrapper dropboxApiWrapper() {
        return new DropboxApiWrapper(dropboxCredentialUtils().readCredentialLocally(),dropboxAppConfig.getClientIdentifier());
    }
}
