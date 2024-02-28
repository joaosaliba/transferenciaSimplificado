package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.transation.Transation;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.TransationDTO;
import com.picpaysimplificado.repositories.TrasationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransationService {

    @Autowired
    private UserService userService;

    @Autowired
    private TrasationRepository trasationRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    private final String autorizationEndPoint ="https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

    @Transactional
    public Transation createTransation(TransationDTO transationDTO) throws Exception {
        User sender = this.userService.findUserById(transationDTO.senderId());
        User receiver = this.userService.findUserById(transationDTO.receiverId());

        userService.validateTrasaction(sender,transationDTO.value());
        boolean isAuthorized =authorazeTransation(sender,transationDTO.value());
        if(!isAuthorized){
            throw  new Exception("Transação não autorizada");
        }
        Transation transation = new Transation();
        transation.setAmount(transationDTO.value());
        transation.setSender(sender);
        transation.setReceiver(receiver);
        transation.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transationDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transationDTO.value()));

        trasationRepository.save(transation);
        userService.saveUser(sender);
        userService.saveUser(receiver);
        this.notificationService.sendNotification(sender,"Transação realizada com sucesso");
        this.notificationService.sendNotification(receiver,"Transação recebida com sucesso");
        return  transation;

    }

    public boolean authorazeTransation(User sender, BigDecimal value){
      ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(this.autorizationEndPoint, Map.class);
        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return message.equalsIgnoreCase("Autorizado");
        }else {
            return false;
        }
    }
}
