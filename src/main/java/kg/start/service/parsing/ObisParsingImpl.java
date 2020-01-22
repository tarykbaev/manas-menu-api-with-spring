package kg.start.service.parsing;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> getFirstPage(String username, String password) {
        List<String> user = new ArrayList<>();
        try {
            Document conn = Jsoup
                    .connect("http://obis.manas.edu.kg/index.php?frm_kullanici=" + username + "&frm_sifre=" + password)
                    .get();
            Elements check = conn.select("div[class=hata]");
            Elements allMenus = conn.select("div[class=baslik]");

            if (check.text().isEmpty()) user.add("true");
            else user.add("false");
            user.add(allMenus.text());

            user.add(getToken(username, password));
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<String> getBilgilerPage(String PHPSESSID) {
        List<String> bilgiler = new ArrayList<>();
        try {
            Document conn = Jsoup
                    .connect("http://obis.manas.edu.kg/index.php?page=bilgiler&PHPSESSID="+ PHPSESSID)
                    .get();
            Elements p1 = conn.select("center");
            Elements p2 = p1.select("table[class=bgc15]");
            Elements p3 = p2.select("td");

            for (Element menu : p3)
                bilgiler.add(menu.text());

            return bilgiler;
        } catch (Exception e) {
            return null;
        }
    }
}
