package shortcutlink.shortcut.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@AllArgsConstructor
@Service
public class CreateNewUserUUID {
    public String createNewUser(HttpServletResponse response){
        String userUUID = UUID.randomUUID().toString();

        Cookie cookie = new Cookie("userUUID", userUUID);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 365); // 1 год
        response.addCookie(cookie);

        return userUUID;
    }
}
