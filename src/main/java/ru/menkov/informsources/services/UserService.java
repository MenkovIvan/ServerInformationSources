package ru.menkov.informsources.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.menkov.informsources.helpers.RequestStatus;
import ru.menkov.informsources.model.custom.User;
import ru.menkov.informsources.repositories.UserRepository;

@Service
@Slf4j
public class UserService {

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
            status = RequestStatus.OK.getStatus();
            log.info("login is true");
            return getJsonString(message,status,userInDB.getUsername());
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
        User userInDB = userRepository.findUserByUsername(userFromClient.getUsername());
        User userInDB2 = userRepository.findUserByEmail(userFromClient.getEmail());

        String message;
        Integer status;

        if (userInDB == null && userInDB2 == null){
            log.info("add user to database");
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
        User userInDb = userRepository.findUserByUsername(userFromClient.getUsername());

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

    private String getJsonString(String message, Integer status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);
        String jsonToClient = jsonObject.toString();

        log.info("return to client={}", jsonToClient);
        return jsonToClient;
    }

    private String getJsonString(String message, Integer status, String name) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);
        jsonObject.addProperty("name",name);
        String jsonToClient = jsonObject.toString();

        log.info("return to client={}", jsonToClient);
        return jsonToClient;
    }

    private String getJsonStringWithUser(User user, String message, Integer status) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("message",message);

        jsonObject.add("user",gson.toJsonTree(user));
        String jsonToClient = jsonObject.toString();
        log.info("return to client={}", jsonToClient);

        return jsonToClient;
    }
}