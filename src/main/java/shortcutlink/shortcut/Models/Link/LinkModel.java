package shortcutlink.shortcut.Models.Link;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "link")
public class LinkModel {
    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 2048)
    private String url;

    @Column(name = "token")
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "short_link")
    private LinksEnum shortLink;

    @Column(name = "countAll")
    private Long count;

    @Column(name = "limit_cross")
    private Long limitCross;

    @Column(name = "start_date", nullable = false, updatable = false)
    private LocalDate startDate = LocalDate.now();

    @Column(name = "dead_line_date")
    private LocalDate deadLineDate;

    @Column(name = "uuid_client")
    private String uuid;

    public boolean isExpired() {
        if (deadLineDate == null) {
            // Если нет дедлайна, считаем «не истекло»
            return false;
        }
        return LocalDate.now().isAfter(deadLineDate);
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setShortLink(LinksEnum shortLink) {
        this.shortLink = shortLink;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setLimitCross(Long limitCross) {
        this.limitCross = limitCross;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDeadLineDate(LocalDate deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public LinksEnum getShortLink() {
        return shortLink;
    }

    public Long getCount() {
        return count;
    }

    public Long getLimitCross() {
        return limitCross;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDeadLineDate() {
        return deadLineDate;
    }

    public String getUuid() {
        return uuid;
    }
}
