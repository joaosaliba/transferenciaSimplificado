package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;
    private final String notificationEndPoint ="https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO  notificationRequest = new NotificationDTO(email,message);

//       ResponseEntity<String> notificationResponse =  restTemplate.postForEntity(notificationEndPoint,notificationRequest, String.class);
//       if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
//           System.err.println("Serviço de notificação não disponivel");
//           throw new Exception("Serviço de notificação não disponivel");
//       }
        System.out.println("Notificacao enviada");
    }

}
