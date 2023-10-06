package vn.edu.usth.dropbox.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.dropbox.core.oauth.DbxCredential;

public class DropboxCredentialUtils {

    private final SharedPreferences sharedPreferences;

    private static final String TAG = "DropBox";

    public DropboxCredentialUtils(Context appContext) {
         sharedPreferences = appContext.getSharedPreferences(
                "dropbox-clone",
                AppCompatActivity.MODE_PRIVATE
        );
    }

    public DbxCredential readCredentialLocally() {
        var serializedCredentialJson= sharedPreferences.getString("credential", null);
        Log.d(TAG, "Local Credential Value from Shared Preferences:" + serializedCredentialJson);
        try {
            return DbxCredential.Reader.readFully(serializedCredentialJson);
        } catch (Exception e) {
            Log.d(TAG, "Something went wrong parsing the credential, clearing it");
            removeCredentialLocally();
            return null;
        }
    }

    public void storeCredentialLocally(DbxCredential dbxCredential) {
        Log.d(TAG, "Storing credential in Shared Preferences");
        sharedPreferences.edit().putString("credential", DbxCredential.Writer.writeToString(dbxCredential)).apply();
    }

    public void removeCredentialLocally() {
        Log.d(TAG, "Clearing credential from Shared Preferences");
        sharedPreferences.edit().remove("credential").apply();
    }

    public boolean isAuthenticate() {
        return readCredentialLocally() != null;
    }

}
