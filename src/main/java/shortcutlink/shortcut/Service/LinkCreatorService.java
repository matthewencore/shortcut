package shortcutlink.shortcut.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sqids.Sqids;
import java.util.List;

@Service
public class LinkCreatorService {

    @Autowired
    private LinkService linkService;

    public String createToken(String url) {
        Sqids sqids = Sqids.builder().build();
        String token;

        do {
            token = sqids.encode(List.of(System.currentTimeMillis()));
        } while (!linkService.findByTokenBool(token));
        // Генерируем новый токен, пока findByTokenBool(token) == false (т.е. пока токен "занят")

        return token;
    }
}
