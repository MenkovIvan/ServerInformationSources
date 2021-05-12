package ru.menkov.informsources.services;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.menkov.informsources.helpers.RequestStatus;
import ru.menkov.informsources.model.custom.Friends;
import ru.menkov.informsources.repositories.FriendsRepository;
import ru.menkov.informsources.repositories.UserRepository;

@Service
public class FriendsService extends ru.menkov.informsources.services.Service {
    @Autowired
    private final FriendsRepository friendsRepository;
    @Autowired
    private final UserRepository userRepository;

    private Gson gson = new Gson();

    FriendsService(FriendsRepository friendsRepository, UserRepository userRepository){
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }

    public String addFriends(String inputJson){
        Friends friendsFromClient = gson.fromJson(inputJson, Friends.class);

        String message = "Result of add = {";
        Integer status;

        if ((!userRepository.existsUserByUser_id(friendsFromClient.getUser_id())
                || !userRepository.existsUserByUser_id(friendsFromClient.getFriends()))) {

            message += "incorrect, user or friend with input id are not exist";
            status = RequestStatus.ERROR.getStatus();

        } else if (friendsRepository.existsFriendsByUser_idAndFriends(friendsFromClient.getUser_id(), friendsFromClient.getFriends())){
            message += "incorrect, user is follow to this friend yet";
            status = RequestStatus.ERROR.getStatus();
        } else{
            message += "correct = " + friendsFromClient.getFriends();
            friendsRepository.save(friendsFromClient);
            status = RequestStatus.OK.getStatus();
        }
        message += "}";

        return getJsonString(message,status);
    }

    public String deleteFriends(String inputJson){
        Friends friendsFromClient = gson.fromJson(inputJson, Friends.class);

        String message = "Result of add = {";
        Integer status;

        if (friendsRepository.existsFriendsByUser_idAndFriends(friendsFromClient.getUser_id(), friendsFromClient.getFriends())){
            friendsRepository.delete(friendsFromClient);

            message += "correct, user or friend with input id are not exist";
            status = RequestStatus.OK.getStatus();

        } else{

            message += "incorrect, user or friend with input id are not exist";
            status = RequestStatus.ERROR.getStatus();

        }
        message += "}";
        return getJsonString(message,status);
    }
}
