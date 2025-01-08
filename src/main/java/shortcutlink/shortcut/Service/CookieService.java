package shortcutlink.shortcut.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import shortcutlink.shortcut.Service.Exception.CookieEmpty;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CookieService {
    public String getCookie(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        List<Cookie> cookieList = List.of(request.getCookies());
        Stream<Cookie> userUUID = cookieList
                .stream()
                .filter(x -> x.getName().equals(name));
        Cookie cookie = userUUID.findFirst().orElseThrow(() -> new CookieEmpty(
                String.format("Печеньки | К сожалению по ключу %s ничего не нашлось.", name)));

        return cookie.getValue();
    }

    public void checkCookie(HttpServletRequest request, HttpServletResponse response, String uuid) throws IOException {
        String string = getCookie(request,"userUUID");
        if (string == null){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"К сожалению, файлов куки нет или вы их удалили.");
            return;
        }

        if (!getCookie(request,"userUUID").equals(uuid)){
            response.sendError(HttpServletResponse.SC_FORBIDDEN,"Куки не совпадают.");
            return;
        }
    }
}
