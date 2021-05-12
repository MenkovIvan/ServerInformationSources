package ru.menkov.informsources.model.custom;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name = "friends",schema = "inf_sources")
public class Friends {
    private Integer user_id;
    @Column(name="friends")
    @ElementCollection(targetClass=Integer.class)
    private ArrayList<Integer> friends;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public ArrayList<Integer> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Integer> friends) {
        this.friends = friends;
    }
}
