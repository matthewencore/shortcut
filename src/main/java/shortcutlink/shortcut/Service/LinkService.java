package shortcutlink.shortcut.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shortcutlink.shortcut.Models.DTO.LinkDTO;
import shortcutlink.shortcut.Models.Link.LinkModel;
import shortcutlink.shortcut.Repository.LinkRepository;
import shortcutlink.shortcut.Service.Exception.LimitException;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;

    public void createLink(String token, String uuid, LocalDate dateExp, String url){
        LinkModel model = new LinkModel();
        model.setToken(token);
        model.setLimitCross(999L);
        model.setUuid(uuid);
        model.setDeadLineDate(dateExp);
        model.setUrl(url);

        linkRepository.save(model);
    }

    public List<LinkModel> allUuidLink(String uuid){
        return linkRepository.findAllByUuid(uuid);
    }

    public void deleteLink(Long id){
        linkRepository.deleteById(id);
    }

    public void updateLink(Long id, LinkDTO linkDTO){
        LinkModel model = linkRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ссылка с таким ID не найдена"));

        if (linkDTO.getToken() != null) {
            if (linkDTO.getToken().length() > 8 || linkDTO.getToken().isEmpty()) {
                throw new IllegalArgumentException("Длина токена не может быть больше 8 и пустым");
            }

            if( linkDTO.getToken().matches(".*[а-яА-ЯёЁ].*")){
                throw new IllegalArgumentException("Токен должен быть только из английских символом");
            }

            model.setToken(linkDTO.getToken());
        }

        if (linkDTO.getDeadLineDate() != null) {
            model.setDeadLineDate(linkDTO.getDeadLineDate());
        }

        if (linkDTO.getLimit() != null) {
            if (linkDTO.getLimit() < 0) {
                throw new IllegalArgumentException("Лимит не может быть отрицательным");
            }
            model.setLimitCross(linkDTO.getLimit());
        }

        if (linkDTO.getUrl() != null) {
            model.setUrl(linkDTO.getUrl());
        }

        linkRepository.save(model);

    }

    public String findByTokenUrl(String token){
        LinkModel lModel = linkRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("По токену: " + token + " ничего нет"));
        return lModel.getUrl();
    }

    public boolean findByTokenBool(String token) {
        // Ищем объект по токену
        Optional<LinkModel> optionalLinkModel = linkRepository.findByToken(token);

        // Если optionalLinkModel пустой, значит объекта нет -> вернем true
        // Если объект есть -> вернем false
        return optionalLinkModel.isEmpty();
    }


//    public void checkLimit(String token){
//        LinkModel lModel = linkRepository.findByToken(token)
//                .orElseThrow(() -> new IllegalArgumentException("По токену: " + token + " ничего нет"));
//
//        if (lModel.getCount() > lModel.getLimitCross()){
//            throw new LimitException("К сожалению переход по этой ссылке больше не возможен, превышен лимит.");
//        }
//    }

    public void incrementCount(String token){
        LinkModel lModel = linkRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("По токену: " + token + " ничего нет"));

        if (lModel.isExpired()){
            throw new IllegalArgumentException("Ссылка истекла.");
        }

        long views;
        long limit;

        if (lModel.getCount() == null) {
            views = 0L;
        } else {
            views = lModel.getCount();
        }

        if (lModel.getLimitCross() == null) {
            limit = 0L;
        } else {
            limit = lModel.getLimitCross();
        }

        if (views >= limit){
            throw new LimitException("К сожалению переход по этой ссылке больше не возможен, превышен лимит.");
        }

        // Увеличиваем счетчик и сохраняем объект
        lModel.setCount(views + 1);
        linkRepository.save(lModel); // Сохраняем обнов

    }
}
