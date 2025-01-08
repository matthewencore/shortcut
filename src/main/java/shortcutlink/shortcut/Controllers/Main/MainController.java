package shortcutlink.shortcut.Controllers.Main;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import shortcutlink.shortcut.Service.CookieService;

@Controller
public class MainController {
    @Autowired
    CookieService cookieService;

    // Главная страница
    @GetMapping(path = "/")
    public String mainPage(HttpServletRequest request, Model model){
        String userUUID = cookieService.getCookie(request,"userUUID");
        model.addAttribute("uuid",userUUID);
        return "index";
    }
}
