package kg.start.service.parsing;

public interface ObisParsing {
    String getToken(String username, String password);
    Boolean checkUser(String username, String password);
}
