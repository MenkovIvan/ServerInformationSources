package ru.menkov.informsources.services;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.menkov.informsources.helpers.RequestStatus;
import ru.menkov.informsources.model.custom.Friends;
import ru.menkov.informsources.repositories.FriendsRepository;

@Service
public class FriendsService extends ru.menkov.informsources.services.Service {
    @Autowired
    private final FriendsRepository friendsRepository;

    private Gson gson = new Gson();

    FriendsService(FriendsRepository friendsRepository){
        this.friendsRepository = friendsRepository;
    }

    public String addFriends(String inputJson){
        Friends friendsFromClient = gson.fromJson(inputJson, Friends.class);

        String message = "Result of add = {";
        Integer status;

        for (int i = friendsFromClient.getFriends().size() - 1; i > 0; i --) {
            if(friendsRepository.existsFriendsByUser_idAndFriends(friendsFromClient.getUser_id(), friendsFromClient.getFriends().get(i))){
                message += "incorrect = " + friendsFromClient.getFriends().get(i) + ", ";
                friendsFromClient.getFriends().remove(i);
            }
            else{
                message += "correct = " + friendsFromClient.getFriends().get(i) + ", ";
            }
        }
        message += "}";
        try{
            friendsRepository.save(friendsFromClient);
            status = RequestStatus.OK.getStatus();
        } catch (Exception e){
            status = RequestStatus.ERROR.getStatus();
        }

        return getJsonString(message,status);
    }

    public String deleteFriends(String inputJson){
        return "";
    }
}
