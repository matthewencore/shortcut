package shortcutlink.shortcut.Controllers.Main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shortcutlink.shortcut.Repository.LinkRepository;
import shortcutlink.shortcut.Service.LinkService;

@Controller
public class RedirectController {
    @Autowired
    LinkService linkService;

    @GetMapping(path = "/{token}")
    public String redirect(@PathVariable String token){
        // Находим URL по токену
        String url = linkService.findByTokenUrl(token);

        // Если URL найден, делаем редирект
        if (url != null) {
            // Проверяем лимит ссылки и увеличиваем просмотр на +1
            linkService.incrementCount(token);
            return "redirect:" + url;  // Выполняем редирект на найденный URL
        } else {
            return "redirect:/error"; // Если URL не найден, перенаправляем на страницу ошибки
        }
    }
}
