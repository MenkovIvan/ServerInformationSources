package ru.menkov.informsources.controllers.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.menkov.informsources.services.custom.UserService;

@RestController
@Slf4j
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/login")
    public String loginUser(@RequestBody String inputJson){
        log.info("Request to login User, body = { " + inputJson + "}");
        return userService.loginUser(inputJson);
    }

    @RequestMapping("/user/registr")
    public String registrUser(@RequestBody String inputJson){
        log.info("Request to registr User, body= {" + inputJson + "}");
        return userService.registrUser(inputJson);
    }

    @RequestMapping("/user/info")
    public String getInfoUser(@RequestBody String inputJson){
        log.info("Request to get Info about User, body = {" + inputJson + "}");
        return userService.getInfoUser(inputJson);
    }

    @RequestMapping("/user/exit")
    public String exitApp(@RequestBody String inputJson){
        log.info("Request to exit from App, body = {" + inputJson + "}");
        return userService.setOffline(inputJson);
    }
}
