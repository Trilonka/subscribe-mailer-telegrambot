package com.github.trilonka.subscribemailertelegrambot.client;

import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupCountRequestArgs;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupDiscussionInfo;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupInfo;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupRequestArgs;
import com.github.trilonka.subscribemailertelegrambot.client.impl.GroupClientBasic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.trilonka.subscribemailertelegrambot.client.dto.MeGroupInfoType.TECH;

@DisplayName("Integration-level testing for JavaRushGroupClientImplTest")
public class GroupClientTest {

    private final GroupClient groupClient =
            new GroupClientBasic("https://javarush.com/api/1.0/rest");

    @Test
    public void shouldProperlyGetGroupsWithEmptyArgs() {
        // given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        // when
        List<GroupInfo> groupList = groupClient.getGroupList(args);

        // then
        Assertions.assertNotNull(groupList);
        Assertions.assertFalse(groupList.isEmpty());
    }

    @Test
    public void shouldProperlyGetWithOffSetAndLimit() {
        // given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        // when
        List<GroupInfo> groupList = groupClient.getGroupList(args);

        // then
        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(3, groupList.size());
    }

    @Test
    public void shouldProperlyGetGroupsDiscWithEmptyArgs() {
        // given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        // when
        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(args);

        // then
        Assertions.assertNotNull(groupList);
        Assertions.assertFalse(groupList.isEmpty());
    }

    @Test
    public void shouldProperlyGetGroupDiscWithOffSetAndLimit() {
        // given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        // when
        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(args);

        // then
        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(3, groupList.size());
    }

    @Test
    public void shouldProperlyGetGroupCount() {
        // given
        GroupCountRequestArgs args = GroupCountRequestArgs.builder().build();

        // when
        Integer groupCount = groupClient.getGroupCount(args);

        // then
        Assertions.assertEquals(32, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupTECHCount() {
        // given
        GroupCountRequestArgs args = GroupCountRequestArgs.builder()
                .type(TECH)
                .build();

        // when
        Integer groupCount = groupClient.getGroupCount(args);

        // then
        Assertions.assertEquals(7, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupById() {
        // given
        Integer androidGroupId = 16;

        // when
        GroupDiscussionInfo groupById = groupClient.getGroupById(androidGroupId);

        // then
        Assertions.assertNotNull(groupById);
        Assertions.assertEquals(16, groupById.getId());
        Assertions.assertEquals(TECH, groupById.getType());
        Assertions.assertEquals("android", groupById.getKey());
    }
}
