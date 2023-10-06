package vn.edu.usth.dropbox.api;

import android.content.Context;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.android.Auth;

import java.util.List;

import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

public class DropboxOAuth2Utils {
    private final DropboxCredentialUtils dropboxCredentialUtils;
    private final DropboxAppConfig dropboxAppConfig;

    public DropboxOAuth2Utils(DropboxCredentialUtils dropboxCredentialUtils, DropboxAppConfig dropboxAppConfig) {
        this.dropboxCredentialUtils = dropboxCredentialUtils;
        this.dropboxAppConfig = dropboxAppConfig;
    }

    private boolean isAwaitinResult = false;

    public void startDropboxAuthorization2PKCE(Context context) {
        DbxRequestConfig requestConfig = new DbxRequestConfig(dropboxAppConfig.getClientIdentifier());

        List<String> scopes = List.of(
                "account_info.read",
                "files.content.write",
                "files.content.read",
                "sharing.read");
        Auth.startOAuth2PKCE(context, dropboxAppConfig.getApiKey(), requestConfig, scopes);
        isAwaitinResult = true;
    }

    public void startDropboxAuthorizationOAuth2(Context context) {
        Auth.startOAuth2Authentication(context, dropboxAppConfig.getApiKey());
        isAwaitinResult = true;
    }

    public void onResume() {
        if (isAwaitinResult) {
            var authDbxCredential = Auth.getDbxCredential();
            isAwaitinResult = false;
            if (authDbxCredential != null) {
                dropboxCredentialUtils.storeCredentialLocally(authDbxCredential);
            }
        }
    }
}
