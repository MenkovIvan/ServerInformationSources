package ru.menkov.informsources.services.commerce;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.menkov.informsources.helpers.RequestStatus;
import ru.menkov.informsources.model.commerce.Score;
import ru.menkov.informsources.repositories.commerce.ScoreRepository;

@Service
@Slf4j
public class ScoreService extends ru.menkov.informsources.services.Service {

    private ScoreRepository scoreRepository;

    private Gson gson = new Gson();

    @Autowired
    public ScoreService(ScoreRepository scoreRepository){
        this.scoreRepository = scoreRepository;
    }

    public String addScore(String inputJson){
        Score scoreFromClient = gson.fromJson(inputJson, Score.class);

        String message = "Result of add = {";
        Integer status;

        if (scoreRepository.existsScoreById(scoreFromClient.getId())){

            log.info("exist in db");
            message += "incorrect, Source with input user_id is exist";
            status = RequestStatus.ERROR.getStatus();

        } else{
            log.info("not exist");
            scoreRepository.save(scoreFromClient);
            log.info("add to db");
            message += "correct, add Score";
            status = RequestStatus.OK.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String getInfoIDScore(String inputJson){
        Score scoreFromClient = gson.fromJson(inputJson, Score.class);

        String message = "Result of get info score = {";
        Integer status;

        if (scoreRepository.existsScoreById(scoreFromClient.getId()) != null){

            log.info("finance exist in db");
            message += "correct, get Score";
            status = RequestStatus.OK.getStatus();
            Score score = scoreRepository.findScoreById(scoreFromClient.getId());
            message += "}";
            return getJsonStringWithScore(score, message, status);

        } else{
            log.info("finance not exist in db");
            message += "incorrect, Score with input id is not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    private String getJsonStringWithScore(Score score, String message, Integer status){
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);

        jsonObject.add("Score",gson.toJsonTree(score));
        String jsonToClient = jsonObject.toString();
        log.info("return to client={}", jsonToClient);

        return jsonToClient;
    }
}
