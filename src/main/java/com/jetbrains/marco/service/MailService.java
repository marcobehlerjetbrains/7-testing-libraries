package com.jetbrains.marco.service;

import com.jetbrains.marco.UserDto;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Properties;

public class MailService {

    public boolean sendWelcomeEmail(UserDto userDto) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "localhost");
        prop.put("mail.smtp.port", "25");

        try {
            Session session = Session.getInstance(prop);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("marco@jetbrains.com"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(userDto.email()));
            message.setSubject("Mail Subject");

            String msg = "This is my email!";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent welcome email");
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
