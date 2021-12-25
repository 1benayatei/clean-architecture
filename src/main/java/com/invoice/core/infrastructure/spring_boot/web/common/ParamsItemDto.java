package com.invoice.core.infrastructure.spring_boot.web.common;

import com.fasterxml.jackson.databind.JsonNode;

import javax.validation.constraints.NotNull;

public class ParamsItemDto {
    @NotNull
    private String key;

    @NotNull
    private JsonNode value;

    public JsonNode getValue() {
        return value;
    }

    public void setValue(JsonNode value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
