package com.stolczmiklos.blog.emailhandler;

import com.stolczmiklos.blog.domain.ActionType;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender implements Runnable {

    private static final String SENDER_EMAIL = "needs_to_be_made@gmail.com";
    private static final String SENDER_PASSWORD = "Something1371";
    private String recipientEmail;
    private String recipientName;
    private String confirmationCode;
    private ActionType actionType;

    public EmailSender(String recipientEmail, String recipientName, ActionType actionType) {
        this.recipientEmail = recipientEmail;
        this.recipientName = recipientName;
        this.actionType = actionType;
    }

    public EmailSender(String recipientEmail, String recipientName, String confirmationCode, ActionType actionType) {
        this.recipientEmail = recipientEmail;
        this.recipientName = recipientName;
        this.confirmationCode = confirmationCode;
        this.actionType = actionType;
    }

    @Override
    public void run() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
                    }
                });
        try {
            Message message = this.getNewMessage(session);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Message getNewMessage(Session session) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER_EMAIL));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(this.recipientEmail)
        );

        message.setSubject(actionType.getEmailSubject());

        if (this.confirmationCode != null) {
            setAccountConfirmationText(message);
        }

        return message;
    }

    private void setAccountConfirmationText(Message message) throws MessagingException {
        message.setText("Kedves " + this.recipientName + "!"
                                + actionType.getMailMainText()
                                + actionType.getBaseUrl()
                                + this.confirmationCode
                                + actionType.getMailSignature()
        );
    }

}