package kg.start.service.controllers;


import kg.start.service.services.MenuListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodMenuController {

    @Autowired
    private MenuListService menuListService;

    @GetMapping("/food/{date}")
    public List<String> getMenuList(@PathVariable("date") String date) {
        return menuListService.menuList(date);
    }

    @GetMapping("/date")
    public List<String> getDateList() {
        return menuListService.dateList();
    }
}
