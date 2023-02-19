package com.github.trilonka.subscribemailertelegrambot.client.impl;

import com.github.trilonka.subscribemailertelegrambot.client.JavaRushGroupClient;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupCountRequestArgs;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupDiscussionInfo;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupInfo;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupRequestArgs;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JavaRushGroupClientBasic implements JavaRushGroupClient {

    private final String javarushApiGroupPath;

    public JavaRushGroupClientBasic(@Value("${javarush.api.path}") String path) {
        this.javarushApiGroupPath = path + "/groups";
    }

    @Override
    public List<GroupInfo> getGroupList(GroupRequestArgs requestArgs) {
        return Unirest.get(javarushApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupInfo>>() {})
                .getBody();
    }

    @Override
    public List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs) {
        return Unirest.get(javarushApiGroupPath)
                .queryString(requestArgs.populateQueries())
                .asObject(new GenericType<List<GroupDiscussionInfo>>() {
                })
                .getBody();
    }

    @Override
    public Integer getGroupCount(GroupCountRequestArgs countRequestArgs) {
        return Integer.valueOf(
                Unirest.get(String.format("%s/count", javarushApiGroupPath))
                        .queryString(countRequestArgs.populateQueries())
                        .asString()
                        .getBody()
        );
    }

    @Override
    public GroupDiscussionInfo getGroupById(Integer id) {
        return Unirest.get(String.format("%s/group%s", javarushApiGroupPath, id.toString()))
                .asObject(GroupDiscussionInfo.class)
                .getBody();
    }

    @Override
    public Integer findLastPostId(Integer groupSub) {
//        List<PostInfo> posts = Unirest.get(getJavarushApiPostPath)
//                .queryString("order", "NEW")
//                .queryString("groupKid", groupSubId.toString())
//                .queryString("limit", "1")
//                .asObject(new GenericType<List<PostInfo>>() {
//                })
//                .getBody();
//        return isEmpty(posts) ? 0 : Optional.ofNullable(posts.get(0)).map(PostInfo::getId).orElse(0);
        return null;
    }
}
