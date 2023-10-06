package vn.edu.usth.dropbox;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.dropbox.api.DropboxApiWrapper;
import vn.edu.usth.dropbox.api.DropboxCredentialUtils;
import vn.edu.usth.dropbox.api.DropboxOAuth2Utils;
import vn.edu.usth.dropbox.di.AppGraph;

public class BaseActivity extends AppCompatActivity {

    AppGraph appGraph;

    DropboxOAuth2Utils dropboxOAuth2Util = appGraph.dropboxOAuth2Utils();

    DropboxCredentialUtils dropboxCredentialUtils = appGraph.dropboxCredentialUtils();

    DropboxApiWrapper dropboxApiWrapper = appGraph.dropboxApiWrapper();

    @Override
    public void onResume() {
        super.onResume();
        dropboxOAuth2Util.onResume();
    }

    private Boolean isAuthenticated() {
        return dropboxCredentialUtils.isAuthenticate();
    }
}
