package ru.menkov.informsources.services.custom;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.menkov.informsources.helpers.RequestStatus;
import ru.menkov.informsources.model.custom.User;
import ru.menkov.informsources.repositories.custom.UserRepository;

@Service
@Slf4j
public class UserService extends ru.menkov.informsources.services.Service {

    private final UserRepository userRepository;

    private Gson gson = new Gson();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String loginUser(String inputJson){

        User userFromClient = gson.fromJson(inputJson, User.class);
        User userInDB = userRepository.findUserByEmail(userFromClient.getEmail());

        String message;
        Integer status;

        if (userRepository.existsUserByEmailAndPassword(userFromClient.getEmail(),userFromClient.getPassword())) {
            message = "OK";
            userRepository.setUserInfoById(true, userFromClient.getEmail());
            status = RequestStatus.OK.getStatus();
            log.info("login is true");
            return getJsonString(message,status,userInDB.getName());
        }
        else if(userInDB != null){
            log.info("false password");
            message = "Password for this User is incorrect";
            status = RequestStatus.ERROR.getStatus();
        }
        else{
            log.info("false email");
            message = "User with this email is not exist";
            status = RequestStatus.ERROR.getStatus();
        }
        return getJsonString(message,status);
    }

    public String registrUser(String inputJson){
        User userFromClient = gson.fromJson(inputJson, User.class);
        User userInDB = userRepository.findUserByName(userFromClient.getName());
        User userInDB2 = userRepository.findUserByEmail(userFromClient.getEmail());

        String message;
        Integer status;

        if (userInDB == null && userInDB2 == null){
            log.info("add user to database");
            userFromClient.setOnline(false);
            userFromClient.setType("user");
            userRepository.save(userFromClient);
            message = "OK";
            status = RequestStatus.OK.getStatus();
        } else{
            message = "User with this email or username is exist, change it";
            status = RequestStatus.ERROR.getStatus();
        }
        return getJsonString(message,status);
    }

    public String getInfoUser(String inputJson){
        User userFromClient = gson.fromJson(inputJson, User.class);
        User userInDb = userRepository.findUserByName(userFromClient.getName());

        String message;
        Integer status;

        if (userInDb != null ){
            log.info("user exist");
            message = "OK";
            status = RequestStatus.OK.getStatus();
        } else{
            log.info("user is not exist");
            message = "user is not exist";
            status = RequestStatus.ERROR.getStatus();
        }
        return getJsonStringWithUser(userInDb,message,status);
    }

    public String setOffline(String inputJson){
        User userFromClient = gson.fromJson(inputJson, User.class);
        User userInDb = userRepository.findUserByEmailAndName(userFromClient.getEmail(), userFromClient.getName());

        String message;
        Integer status;

        if (userInDb != null ){
            log.info("user exist");
            message = "OK, exit from app";
            status = RequestStatus.OK.getStatus();
            userRepository.setUserInfoById(false, userFromClient.getEmail());
        } else{
            log.info("user is not exist");
            message = "user is not exist";
            status = RequestStatus.ERROR.getStatus();
        }
        return getJsonString(message,status);
    }

    private String getJsonStringWithUser(User user, String message, Integer status) {
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);

        jsonObject.add("user",gson.toJsonTree(user));
        String jsonToClient = jsonObject.toString();
        log.info("return to client={}", jsonToClient);

        return jsonToClient;
    }
}