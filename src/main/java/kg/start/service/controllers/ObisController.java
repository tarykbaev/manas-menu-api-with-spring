package kg.start.service.controllers;

import kg.start.service.parsing.ObisParsing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObisController {

    @Autowired
    private ObisParsing obis;

    @GetMapping("/login/{username}/{password}")
    public Boolean checkUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        return obis.checkUser(username, password);
    }

    @GetMapping("/token/{username}/{password}")
    public String getToken(@PathVariable("username") String username, @PathVariable("password") String password) {
        return obis.getToken(username, password);
    }
}
