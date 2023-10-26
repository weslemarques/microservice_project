package com.ms.email.service;

import com.ms.email.dtos.EmailDTO;
import com.ms.email.enums.StatusEmail;
import com.ms.email.model.EmailModel;
import com.ms.email.repository.EmailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    @Transactional
    public void sendEmail(EmailDTO emailDTO) {
        EmailModel emailModel = new EmailModel();
        try {
            BeanUtils.copyProperties(emailDTO, emailModel);
            emailModel.setSendDateEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);
            var emailMessage = new SimpleMailMessage();
            emailMessage.setTo(emailModel.getEmailTo());
            emailMessage.setSubject(emailModel.getSubject());
            emailMessage.setText(emailModel.getText());
            emailSender.send(emailMessage);
            emailModel.setStatus(StatusEmail.SEND);
        } catch (MailException e) {
            System.err.println(e);
            emailModel.setStatus(StatusEmail.ERROR);
        }finally {
            emailRepository.save(emailModel);
        }
    }
}
