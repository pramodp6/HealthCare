package com.health.pramod.HealthCare.utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtility {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText("Account Verification Code");
        javaMailSender.send(message);
    }
}