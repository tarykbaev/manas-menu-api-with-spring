package kg.start.service.parsing;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class ObisParsingImpl implements ObisParsing {

    @Override
    public String getToken(String username, String password) {

        String sessionId;
        try {
            Connection.Response connect = Jsoup.connect("http://obis.manas.edu.kg/index.php")
                    .data("frm_kullanici", username, "frm_sifre", password)
                    .method(Connection.Method.POST)
                    .execute();

            sessionId = connect.cookie("PHPSESSID");
        } catch (Exception e) {
            return "noSessionId";
        }

        return sessionId;
    }

    @Override
    public Boolean checkUser(String username, String password) {
        try {
            Document conn = Jsoup
                    .connect("http://obis.manas.edu.kg/index.php?frm_kullanici=" + username + "&frm_sifre=" + password)
                    .get();
            Elements allMenus = conn.select("div[class=hata]");

            return allMenus.text().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
