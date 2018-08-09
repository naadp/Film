package com.yao.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	 

    public static void main(String[] args) throws Exception {
    	send("naadp","这个是主题","这个是内容","naadp@sina.com","g!ds)af(-&s?=dg!","smtp.sina.com","18832017306@163.com");
    }
    
    
    public static void send(String from,String subject, String content,String myEmailAccount, String myEmailPassword,String myEmailSMTPHost,String receiveMailAccount) throws Exception{

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,from,subject,content);

        Transport transport = session.getTransport();

        transport.connect(myEmailAccount, myEmailPassword);

        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
    }
    

    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String from,String subject, String content) throws Exception {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(sendMail, from, "UTF-8"));

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX�û�", "UTF-8"));

        message.setSubject(subject, "UTF-8");

        message.setContent(content, "text/html;charset=UTF-8");

        message.setSentDate(new Date());

        message.saveChanges();

        return message;
    }

}
