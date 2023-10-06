package vn.edu.usth.dropbox.di;

import vn.edu.usth.dropbox.api.DropboxApiWrapper;
import vn.edu.usth.dropbox.api.DropboxCredentialUtils;
import vn.edu.usth.dropbox.api.DropboxOAuth2Utils;

public interface AppGraph {

    DropboxApiWrapper dropboxApiWrapper();

    DropboxCredentialUtils dropboxCredentialUtils();

    DropboxOAuth2Utils dropboxOAuth2Utils();
}