package Sending_Email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {public static void main(String [] args) {
    // Recipient's email ID needs to be mentioned.
    String to = "khnguyendinh@gmail.com";

    // Sender's email ID needs to be mentioned
    String from = "khoas401@gmail.com";

    // Assuming you are sending email from localhost
    String host = "localhost";

    // Get system properties
    Properties prop = System.getProperties();

    // Setup mail server
//    prop.setProperty("mail.smtp.host", host);
//    prop.setProperty("mail.user", "khoas401@gmail.com");
//    prop.setProperty("mail.password", "MaiMai92");
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.host", "smtp.mailtrap.io");
    prop.put("mail.smtp.port", "25");
    prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

    // Get the default Session object.
//    Session session = Session.getDefaultInstance(prop);
    Session session = Session.getInstance(prop, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication("khoas401", "MaiMai92");
            return new PasswordAuthentication("khoas401@gmail.com", "qmggzofncgwaiuqn");
        }
    });

    try {
//        // Create a default MimeMessage object.
//        MimeMessage message = new MimeMessage(session);
//
//        // Set From: header field of the header.
//        message.setFrom(new InternetAddress(from));
//
//        // Set To: header field of the header.
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//        // Set Subject: header field
//        message.setSubject("This is the Subject Line!");
//
//        // Now set the actual message
//        message.setText("This is actual message");
//
//        // Send message
//        Transport.send(message);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Mail Subject");

        String msg = "This is my first email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
        mex.printStackTrace();
    }
}
}
