package com.hrb.duyi.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class NormalMailUtil {

    /**
     * 发送不带附件的邮件
     * @param toUser 收件人
     * @param subject 主题
     * @param content 内容
     * @return
     */
    private static boolean mailConfig(String toUser,String subject,String content){
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties properties = new Properties();
        //设置邮箱的host，如smtp.qq.com
        properties.put("mail.smtp.host", "smtp.duyiedu.com");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.socketFactory.port","465");
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        try {
            // 发件人
            Address address = new InternetAddress("duyioa@duyi-inc.com");
            message.setFrom(address);
            // 收件人
            Address toAddress = new InternetAddress(toUser);
            // 设置收件人,并设置其接收类型为TO
            message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
            // 主题
            message.setSubject(subject);
            // 时间
            message.setSentDate(new Date());

            Multipart multipart = new MimeMultipart();

            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(content, "text/html; charset=utf-8");
            multipart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            message.setContent(multipart);
            message.saveChanges();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            Transport transport = session.getTransport("smtp");
            //设置host，user,password;如：host:smtp.qq.com,user:xxxx@qq.com,pwd:123123;
            transport.connect("smtp.duyiedu.com", "duyioa@duyi-inc.com", "Dy123456");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static class MailThread implements Runnable{
        private String toUser;
        private String subject;
        private String content;

        public MailThread(String toUser, String subject, String content) {
            this.toUser = toUser;
            this.subject = subject;
            this.content = content;
        }

        public void run() {
            mailConfig(toUser,subject,content);
        }
    }

    public static void sendMail(String toUser, String subject, String content) {
        MailThread mailThread = new MailThread(toUser, subject, content);
        mailThread.run();
    }


}
