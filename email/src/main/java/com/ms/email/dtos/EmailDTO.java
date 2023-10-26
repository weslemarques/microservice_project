package com.ms.email.dtos;

import java.io.Serializable;
import java.util.UUID;

public class EmailDTO implements Serializable {

    private UUID userId;

    private String emailTo;
    private String Subject;
    private String text;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "userId=" + userId +
                ", emailTo='" + emailTo + '\'' +
                ", Subject='" + Subject + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
