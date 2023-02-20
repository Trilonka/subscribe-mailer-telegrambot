package com.github.trilonka.subscribemailertelegrambot.client.dto;

import lombok.Data;

@Data
public class MeGroupInfo {

    private MeGroupInfoStatus status;
    private Integer userGroupId;
}
