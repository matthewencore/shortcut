package shortcutlink.shortcut.Models.DTO;

import java.time.LocalDate;

public class LinkDTO {
    private String token;
    private String url;
    private LocalDate deadLineDate;
    private Long limit;

    public LinkDTO() {
    }

    public LinkDTO(String token, String url, LocalDate deadLineDate, Long limit) {
        this.token = token;
        this.url = url;
        this.deadLineDate = deadLineDate;
        this.limit = limit;
    }

    public String getToken() {
        return token;
    }

    public String getUrl() {
        return url;
    }

    public LocalDate getDeadLineDate() {
        return deadLineDate;
    }

    public Long getLimit() {
        return limit;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDeadLineDate(LocalDate deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }
}
