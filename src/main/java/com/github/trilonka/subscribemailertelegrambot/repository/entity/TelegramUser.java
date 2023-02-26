package com.github.trilonka.subscribemailertelegrambot.repository.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@Table(name="tg_user")
@EqualsAndHashCode(exclude = "groupSubs")
public class TelegramUser {

    @Id
    @Column(name="chat_id")
    Long chatId;

    @Column(name="active")
    boolean active;

    @ManyToMany(mappedBy="users", fetch=FetchType.EAGER)
    List<GroupSub> groupSubs;
}
