package ru.menkov.informsources.controllers.commerce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.menkov.informsources.services.commerce.ScoreService;

@RestController
@Slf4j
public class ScoreController {

    private ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService){
        this.scoreService = scoreService;
    }

    @RequestMapping("/score/add")
    public String addFinance(@RequestBody String inputJson){
        log.info("Request to add Score, body = {} ", inputJson);
        return scoreService.addScore(inputJson);
    }

    @RequestMapping("/score/info")
    public String getInfoIDScore(@RequestBody String inputJson){
        log.info("Request to add Score, body = {} ", inputJson);
        return scoreService.getInfoIDScore(inputJson);
    }
}
