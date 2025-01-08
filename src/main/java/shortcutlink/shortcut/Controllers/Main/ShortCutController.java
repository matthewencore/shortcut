package shortcutlink.shortcut.Controllers.Main;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import jakarta.servlet.http.HttpServletResponse;
import shortcutlink.shortcut.Models.DTO.LinkDTO;
import shortcutlink.shortcut.Models.DTO.Response;
import shortcutlink.shortcut.Models.DTO.ResponseRestAPI;
import shortcutlink.shortcut.Service.*;

@RestController
@RequestMapping("/api")
public class ShortCutController {
    @Autowired
    CreateNewUserUUID userService;
    @Autowired
    LinkCreatorService linkCreatorService;
    @Autowired
    CookieService cookieService;
    @Autowired
    ConvertDateService convertDateService;
    @Autowired
    LinkService linkService;

    @PostMapping(path = "/shorten")
    ResponseEntity<ResponseRestAPI> convertLink(@RequestBody LinkRequest linkRequest,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {
        // Вывод куки текущее
        String userUUID = cookieService.getCookie(request,"userUUID");

        // Регистрация пользователя через клик, генерация UUID
        if (userUUID == null) {
            userUUID = userService.createNewUser(response);
        }

        //System.out.println(request.getServerName());
        System.out.println(userUUID);

        // Ссылка
        String url = linkRequest.getUrl();

        // Токен ссылки, например - youtube.com/short
        String token = linkCreatorService.createToken(url);

        // Дата конца
        LocalDate dateExp = convertDateService.convertDate(linkRequest.getExpDate());

        // Сборка ссылки
        linkService.createLink(token,userUUID,dateExp,url);

        // Формирование ссылки
        String shortCutUrl = String.format("%s:8080/%s",request.getServerName(),token);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseRestAPI("Запрос без ошибок на стороне Spring",shortCutUrl));
    }

    @PostMapping("/delete-link/{id}")
    public void deleteLink(@PathVariable Long id,HttpServletRequest request) {
        String userUUID = cookieService.getCookie(request,"userUUID");
        linkService.deleteLink(id);
    }

    @PostMapping("/update-link/{id}")
    public ResponseEntity<?> updateLink(@PathVariable("id") Long id, @RequestBody LinkDTO linkDTO) {
        // Логика обновления ссылки
        linkService.updateLink(id, linkDTO);
        return ResponseEntity.ok(new Response("Запрос без ошибок на стороне Spring",true));
    }


}
