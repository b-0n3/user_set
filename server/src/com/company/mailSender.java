package com.company;

import org.jetbrains.annotations.NotNull;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class mailSender {
    String host = "smtp.gmail.com";
    final String user = "lalouabd123@gmail.com";//change accordingly
    final String password = "getwaoeruqjapyfe";//change accordingly
    private String email;
    Session session;

    public mailSender(String email) {
        this.email = email;
        Properties props = new Properties();

        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(user, password);
                    }
                });

        System.out.println("conne");
    }

    public void sendRE(@NotNull List<String> list) {


        Iterator<String> msgit = list.iterator();

        StringBuilder msg = new StringBuilder();
        while (msgit.hasNext()) {
            msg.append(msgit.next());
            msg.append('\n');
        }

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Rejected staffs");
            message.setText(msg.toString());


            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAcc(List<String> list) {

        Iterator<String> msgit = list.iterator();

        StringBuilder msg = new StringBuilder();
        while (msgit.hasNext()) {
            msg.append(msgit.next());
            msg.append('\n');
        }

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Accepted  staffs");
            message.setText(msg.toString());


            Transport.send(message);

            System.out.println("message sent successfully...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}