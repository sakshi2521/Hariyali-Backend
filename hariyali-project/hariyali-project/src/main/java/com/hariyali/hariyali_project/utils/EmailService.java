package com.hariyali.hariyali_project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
@Service
public class EmailService {

	@Autowired
    private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;

    public void sendSimpleEmail(String toEmail,String subject,String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Sent...");

    }
}
