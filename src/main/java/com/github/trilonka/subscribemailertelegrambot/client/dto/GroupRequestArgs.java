package com.github.trilonka.subscribemailertelegrambot.client.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Builder
@Getter
@FieldDefaults(makeFinal = true, level= AccessLevel.PRIVATE)
public class GroupRequestArgs {

    String query;
    MeGroupInfoType type;
    GroupFilter filter;
    Integer offset;
    Integer limit;

    public Map<String, Object> populateQueries() {
        Map<String, Object> queries = new HashMap<>();
        if (nonNull(query)) {
            queries.put("query", query);
        }
        if(nonNull(type)) {
            queries.put("type", type);
        }
        if(nonNull(filter)) {
            queries.put("filter", filter);
        }
        if(nonNull(offset)) {
            queries.put("offset", offset);
        }
        if(nonNull(limit)) {
            queries.put("limit", limit);
        }
        return queries;
    }
}
