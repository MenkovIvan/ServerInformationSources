package ru.menkov.informsources.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.menkov.informsources.services.FriendsService;

@RestController
@Slf4j
public class FriendsController {
    private FriendsService friendsService;

    @Autowired
    public FriendsController(FriendsService friendsService){
        this.friendsService = friendsService;
    }

    @RequestMapping("/friends/add")
    public String addFriends(@RequestBody String inputJson){
        log.info("Request to add friends, body= {" + inputJson + "}");
        return friendsService.addFriends(inputJson);
    }

    @RequestMapping("/friends/delete")
    public String deleteFriends(@RequestBody String inputJson){
        log.info("Request to delete friends, body= {" + inputJson + "}");
        return friendsService.deleteFriends(inputJson);
    }
}
