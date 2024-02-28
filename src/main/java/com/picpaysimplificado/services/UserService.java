package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserFactory;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTrasaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType().equals(UserType.MERCHANT)) {
            throw new Exception("Usuário do tipo lojista não autorizado a realizar transação");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public User createUse(UserDTO userDTO) {
        User user = UserFactory.getInstance( UserType.getUserType(userDTO.userType()));
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setEmail(userDTO.email());
        user.setDocument(userDTO.document());
        user.setPassword(userDTO.password());
        user.setBalance(userDTO.balance());
        return userRepository.save(user);
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
