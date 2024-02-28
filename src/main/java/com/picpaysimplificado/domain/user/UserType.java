package com.picpaysimplificado.domain.user;

import java.util.Arrays;

public enum UserType {
    COMMON("common"),
    MERCHANT("merchant");

    private String type;

    UserType(String type) {
        this.type = type;
    }
    public static UserType getUserType(String type) {
        return  Arrays.stream(UserType.values()).filter(o->o.getType().equalsIgnoreCase(type)).findFirst().orElse(null);
    }
    public String getType() {
        return type;
    }
}
