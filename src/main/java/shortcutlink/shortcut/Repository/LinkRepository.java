package shortcutlink.shortcut.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shortcutlink.shortcut.Models.Link.LinkModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<LinkModel, Long> {
    List<LinkModel> findAllByUuid(String uuid);
    Optional<LinkModel> findByToken(String token);
}
