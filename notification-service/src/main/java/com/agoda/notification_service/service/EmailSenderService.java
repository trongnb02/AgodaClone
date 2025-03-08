package com.agoda.notification_service.service;

import com.agoda.notification_service.dto.EmailRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username:thisismytrashfortest1@gmail.com}")
    private String senderEmail;

    public void sendEmail(EmailRequest emailRequest){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(senderEmail);
            mailMessage.setTo(emailRequest.getReceiverEmail());
            mailMessage.setText(emailRequest.getBody());
            mailMessage.setSubject(emailRequest.getSubject());
            javaMailSender.send(mailMessage);
            log.info("Email sent to " + emailRequest.getReceiverEmail() + " successfully.");
        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }

}
