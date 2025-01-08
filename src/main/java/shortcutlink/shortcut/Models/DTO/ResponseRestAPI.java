package shortcutlink.shortcut.Models.DTO;

public class ResponseRestAPI {
    String message;
    String url;

    public ResponseRestAPI(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public ResponseRestAPI(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}