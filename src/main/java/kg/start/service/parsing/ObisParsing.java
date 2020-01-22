package kg.start.service.parsing;

import java.util.List;

public interface ObisParsing {
    String getToken(String username, String password);
    Boolean checkUser(String username, String password);
    List<String> getFirstPage(String username, String password);
    List<String> getBilgilerPage(String PHPSESSID);
}
