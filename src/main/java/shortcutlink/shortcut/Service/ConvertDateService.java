package shortcutlink.shortcut.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ConvertDateService {
    public LocalDate convertDate(String dateString){
        if (dateString == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateString,formatter);
    }
}
