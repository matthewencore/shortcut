package shortcutlink.shortcut.Controllers.Main;

public class LinkRequest {
    private String url;
    private String expDate; // Это поле будет содержать дату окончания

    // Геттеры и сеттеры
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}
