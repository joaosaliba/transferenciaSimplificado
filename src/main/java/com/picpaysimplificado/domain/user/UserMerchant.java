package com.picpaysimplificado.domain.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("MERCHANT")
public class UserMerchant extends User {
    public UserMerchant() {
        this.setUserType(UserType.MERCHANT);
    }

    public UserMerchant(Long id, String firstName, String lastName, String document, String email, String password, BigDecimal balance, UserType usertype) {
        super(id, firstName, lastName, document, email, password, balance, UserType.MERCHANT);
    }

    @Override
    public UserType getUserType() {
        return UserType.MERCHANT;
    }
    @Override
    public void setUserType(UserType userType) {
        super.setUserType(UserType.MERCHANT);
    }
}
