package com.ms.email.consumer;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {


    @RabbitListener(queues = "${broker.queue.email.name}")
    public void lisenEmailQueue(@Payload String emailRecord){
        System.out.println(emailRecord);
    }
}
