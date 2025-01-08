package shortcutlink.shortcut.Controllers.Main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shortcutlink.shortcut.Models.Link.LinkModel;
import shortcutlink.shortcut.Service.CookieService;
import shortcutlink.shortcut.Service.LinkService;

import java.io.IOException;
import java.util.List;

@Controller
public class LKShortCutController {
    @Autowired
    LinkService linkService;

    @Autowired
    CookieService cookieService;

    @GetMapping(path = "/lk/{uuid}")
    public String lk(@PathVariable String uuid, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Проверка на то что это действительно тот самый пользователь
        cookieService.checkCookie(request,response,uuid);

        List<LinkModel> linkModels = linkService.allUuidLink(uuid);

        model.addAttribute("links",linkModels);
        return "lk";
    }
}
