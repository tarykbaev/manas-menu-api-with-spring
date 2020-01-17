package kg.start.service.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Component
public class MenuListParsingImpl implements MenuListParsing {

    @Override
    public List<String> getMenuList(String date) {
        List<String> menus = new ArrayList<>();
        List<String> toDaysMenu = new ArrayList<>();
        Elements allMenus;
        try {
            Document conn = Jsoup.connect("http://bis.manas.edu.kg/menu/").get();
            allMenus = conn.select("font[face=Verdana]");
        } catch (IOException e) {
            return null;
        }

        for (Element menu : allMenus) {
            menus.add(menu.text());
        }

        if (date.equals("today")) {
            Date now = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setTimeZone(TimeZone.getTimeZone("Asia/Bishkek"));
            date = df.format(now);
        }


        int index = menus.indexOf(date);

        if (index < 0) return null;

        toDaysMenu.add(menus.get(index));   // data
        toDaysMenu.add(menus.get(index + 1));   // name
        toDaysMenu.add(menus.get(index + 2));       // kalori
        toDaysMenu.add(menus.get(index + 3));   // name
        toDaysMenu.add(menus.get(index + 4));       // kalori
        toDaysMenu.add(menus.get(index + 5));   // name
        toDaysMenu.add(menus.get(index + 6));       // kalori
        toDaysMenu.add(menus.get(index + 7));   // name
        toDaysMenu.add(menus.get(index + 8));       // kalori

        return toDaysMenu;
    }

    @Override
    public List<String> getDateList() {
        List<String> menus = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        Elements allMenus;
        try {
            Document conn = Jsoup.connect("http://bis.manas.edu.kg/menu/").get();
            allMenus = conn.select("font[face=Verdana]");
        } catch (IOException e) {
            return null;
        }

        for (Element menu : allMenus) {
            menus.add(menu.text());
        }

        for (int i = 0; i < menus.size(); i++) {
            if (i % 12 == 0) {
                dates.add(menus.get(i));
            }
        }
        return dates;
    }
}
