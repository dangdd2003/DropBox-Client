package vn.edu.usth.dropbox.api;


public class DropboxAppConfig {
    private final String apiKey;

    private final String clientIdentifier;

    public DropboxAppConfig(String aipKey, String clientIdentifier) {
        this.apiKey = aipKey;
        this.clientIdentifier = clientIdentifier;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getClientIdentifier() {
        return clientIdentifier;
    }
}
