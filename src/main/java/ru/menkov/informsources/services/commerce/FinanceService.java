package ru.menkov.informsources.services.commerce;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.menkov.informsources.helpers.RequestStatus;
import ru.menkov.informsources.model.commerce.Finance;
import ru.menkov.informsources.model.custom.User;
import ru.menkov.informsources.repositories.commerce.FinanceRepository;
import ru.menkov.informsources.repositories.custom.UserRepository;

@Service
@Slf4j
public class FinanceService extends ru.menkov.informsources.services.Service {

    private Gson gson = new Gson();

    private FinanceRepository financeRepository;
    private UserRepository userRepository;

    @Autowired
    public FinanceService(FinanceRepository financeRepository, UserRepository userRepository){
        this.financeRepository = financeRepository;
        this.userRepository = userRepository;
    }

    public String addFinance(String inputJson){
        Finance financeFromClient = gson.fromJson(inputJson, Finance.class);

        String message = "Result of add = {";
        Integer status;

        if (financeRepository.existsFinanceByUserId(financeFromClient.getUserId())){

            log.info("exist in db");
            message += "incorrect, Source with input user_id is exist";
            status = RequestStatus.ERROR.getStatus();

        } else{
            log.info("not exist");
            financeRepository.save(financeFromClient);
            log.info("add to db");
            message += "correct, add Finance";
            status = RequestStatus.OK.getStatus();
        }

        message += "}";

        log.info("end");

        return getJsonString(message, status);
    }

    public String getInfoIDFinance(String inputJson){
        Finance financeFromClient = gson.fromJson(inputJson, Finance.class);

        String message = "Result of get info finance = {";
        Integer status;

        if (financeRepository.existsFinanceById(financeFromClient.getId()) != null){

            log.info("finance exist in db");
            message += "correct, get Finance";
            status = RequestStatus.OK.getStatus();
            Finance finance = financeRepository.findFinanceById(financeFromClient.getId());
            message += "}";
            return getJsonStringWithFinance(finance, message, status);

        } else{
            log.info("user not exist in db");
            message += "incorrect, Finance with input id is not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String getInfoUserNameFinance(String inputJson){
        User userFromClient = gson.fromJson(inputJson, User.class);
        User userFromDb = userRepository.findUserByName(userFromClient.getName());

        String message = "Result of get info finance = {";
        Integer status;

        if (userFromDb != null){

            log.info("user exist in db");
            message += "correct, get Finance ";
            status = RequestStatus.OK.getStatus();
            message += "}";

            Finance finance = financeRepository.findFinanceByUserId(userFromDb.getId());

            return getJsonStringWithFinance(finance, message, status);

        } else{
            log.info("user not exist in db");
            message += "incorrect, User with input name is not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String updateAgreementFinance(String inputJson){
        Finance financeFromClient = gson.fromJson(inputJson, Finance.class);
        Finance financeFromDb = financeRepository.findFinanceByUserId(financeFromClient.getUserId());

        String message = "Result of update agreement finance = {";
        Integer status;

        if (financeFromDb != null){

            log.info("user exist in db");
            message += "correct, update agreement";
            status = RequestStatus.OK.getStatus();
            financeRepository.setFinanceAgreementByUserId(financeFromClient.getUserId(), financeFromClient.getAgreement());

        } else{
            log.info("user not exist in db");
            message += "incorrect, Finance with input user_id is not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    public String updateCardFinance(String inputJson){
        Finance financeFromClient = gson.fromJson(inputJson, Finance.class);
        Finance financeFromDb = financeRepository.findFinanceByUserId(financeFromClient.getUserId());

        String message = "Result of update card finance = {";
        Integer status;

        if (financeFromDb != null){

            log.info("user exist in db");
            message += "correct, update agreement";
            status = RequestStatus.OK.getStatus();
            financeRepository.setFinanceCardByUserId(financeFromClient.getUserId(), financeFromClient.getCard());

        } else{
            log.info("user not exist in db");
            message += "incorrect, Finance with input user_id is not exist";
            status = RequestStatus.ERROR.getStatus();
        }

        message += "}";

        return getJsonString(message, status);
    }

    private String getJsonStringWithFinance(Finance finance, String message, Integer status){
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);

        jsonObject.add("Finance",gson.toJsonTree(finance));
        String jsonToClient = jsonObject.toString();
        log.info("return to client={}", jsonToClient);

        return jsonToClient;
    }
}
