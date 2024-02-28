package com.picpaysimplificado.domain.user;

public class UserFactory {
    public  static User getInstance(UserType userType){
        return switch (userType){
            case COMMON -> new UserCommon();
            case MERCHANT -> new UserMerchant();
            default -> throw new IllegalArgumentException("UserType not found");
        };
    }
}
