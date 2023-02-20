package com.github.trilonka.subscribemailertelegrambot.repository.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@Table(name="group_sub")
@EqualsAndHashCode
public class GroupSub {

    @Id
    Integer id;

    @Column(name="title")
    String title;

    @Column(name="last_article_id")
    Integer lastArticleId;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="group_x_user",
            joinColumns=@JoinColumn(name="group_sub_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    List<TelegramUser> users;

    public void addUser(TelegramUser user) {
        if (isNull(users)) {
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
