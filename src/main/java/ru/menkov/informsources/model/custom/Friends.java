package ru.menkov.informsources.model.custom;

import javax.persistence.*;

@Entity
@Table(name = "friends",schema = "inf_sources")
public class Friends {
    @Id
    private Integer user_id;

    private Integer friends;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFriends() {
        return friends;
    }

    public void setFriends(Integer friends) {
        this.friends = friends;
    }
}
