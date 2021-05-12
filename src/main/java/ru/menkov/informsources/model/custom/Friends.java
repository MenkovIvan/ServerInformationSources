package ru.menkov.informsources.model.custom;

import javax.persistence.*;

@Entity
@Table(name = "friends",schema = "inf_sources")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer friends;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFriends() {
        return friends;
    }

    public void setFriends(Integer friends) {
        this.friends = friends;
    }
}
