package com.picpaysimplificado.domain.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("COMMON")
public class UserCommon extends User{
    public UserCommon() {
        super();
        this.setUserType( UserType.COMMON);
    }
    public UserCommon(Long id, String firstName, String lastName, String document, String email, String password, BigDecimal balance, UserType usertype) {
        super(id, firstName, lastName, document, email, password, balance,  UserType.COMMON);

    }
    @Override
    public UserType getUserType() {
        return UserType.COMMON;
    }

    @Override
    public void setUserType(UserType userType) {
        super.setUserType(UserType.COMMON);
    }
}
