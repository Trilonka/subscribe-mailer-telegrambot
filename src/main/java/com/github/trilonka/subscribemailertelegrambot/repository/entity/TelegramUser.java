package com.github.trilonka.subscribemailertelegrambot.repository.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@Table(name="tg_user")
public class TelegramUser {

    @Id
    @Column(name="chat_id")
    String chatId;

    @Column(name="active")
    boolean active;

    @ManyToMany(mappedBy="users", fetch=FetchType.EAGER)
    List<GroupSub> groupSubs;
}
