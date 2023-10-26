package com.ms.user.producer;

import com.ms.user.dtos.EmailDTO;
import com.ms.user.model.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${broker.queue.email.name}")
    private String routingKey;
    public void publishMessageEmail(UserModel userModel){
        var email = new EmailDTO();
        email.setEmailTo(userModel.getEmail());
        email.setUserId(userModel.getUserId());
        email.setSubject("Cadastro relizado com Sucesso");
        email.setText(userModel.getName() + ", seja bem vindo(a)! \\nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!");
        rabbitTemplate.convertAndSend("",routingKey,email);
    }
}
