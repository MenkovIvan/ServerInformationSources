package ru.menkov.informsources.model.custom;

import javax.persistence.*;

@Entity
@Table(name = "friends",schema = "inf_sources")
public class Friends {
    @Id
    private Integer id_us;

    private Integer friends;

    public Integer getId_us() {
        return id_us;
    }

    public void setId_us(Integer id_us) {
        this.id_us = id_us;
    }

    public Integer getFriends() {
        return friends;
    }

    public void setFriends(Integer friends) {
        this.friends = friends;
    }
}
