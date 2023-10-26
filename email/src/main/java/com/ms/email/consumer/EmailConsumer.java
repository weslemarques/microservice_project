package com.ms.email.consumer;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ms.email.dtos.EmailDTO;
import com.ms.email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {


    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void lisenEmailQueue(@Payload EmailDTO emailDTO){

        emailService.sendEmail(emailDTO);

    }
}
