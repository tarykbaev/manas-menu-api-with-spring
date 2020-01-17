package kg.start.service.services;

import kg.start.service.parsing.MenuListParsing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuListServiceImpl implements MenuListService {

    @Autowired
    private MenuListParsing menuListParsing;

    @Override
    public List<String> menuList(String date) {
        return menuListParsing.getMenuList(date);
    }

    @Override
    public List<String> dateList() {
        return menuListParsing.getDateList();
    }
}
