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
public class GroupCountRequestArgs {

    String query;
    MeGroupInfoType type;
    GroupFilter filter;

    public Map<String, Object> populateQueries() {
        Map<String, Object> queries = new HashMap<>();

        if (nonNull(query)) {
            queries.put("query", query);
        }
        if (nonNull(type)) {
            queries.put("type", type);
        }
        if (nonNull(filter)) {
            queries.put("filter", filter);
        }
        return queries;
    }
}
