package com.ivy.mail;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSend {
	/**
     * 发送邮件的props文件
     */
    private final transient Properties props = System.getProperties();

    /**
     * 邮件服务器登录验证
     */
    private transient MailAuthenticator authenticator;

    /**
     * 邮箱session
     */
    private transient Session session;

    /**
     * 初始化邮件发送器
     *
     * @param smtpHostName SMTP邮件服务器地址
     * @param username     发送邮件的用户名(地址)
     * @param password     发送邮件的密码
     */
    public MailSend(final String smtpHostName, final String username,
                     final String password) {
        init(username, password, smtpHostName);
    }

    /**
     * 初始化邮件发送器
     *
     * @param username 发送邮件的用户名(地址)，并以此解析SMTP服务器地址
     * @param password 发送邮件的密码
     */
    public MailSend(final String username, final String password) {
        //通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
        final String smtpHostName = "smtp." + username.split("@")[1];
        init(username, password, smtpHostName);

    }

    /**
     * 初始化
     *
     * @param username     发送邮件的用户名(地址)
     * @param password     密码
     * @param smtpHostName SMTP主机地址
     */
    private void init(String username, String password, String smtpHostName) {
        // 初始化props
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", smtpHostName);
        // 验证
        authenticator = new MailAuthenticator(username, password);
        // 创建session

        session = Session.getInstance(props, authenticator);
    }

    /**
     * 发送邮件
     *
     * @param recipient 收件人邮箱地址
     * @param subject   邮件主题
     * @param content   邮件内容
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(String recipient, String subject, Object content)
            throws Exception {
        // 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);
        // 设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        // 设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
        // 发送
        Transport.send(message);
    }


    /**
     * 发送邮件
     *
     * @param recipient 收件人邮箱地址
     * @param subject   邮件主题
     * @param content   邮件内容
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(String recipient, String subject, String content, String... files)
            throws Exception {
        // 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);
        // 设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        // 设置收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        Multipart multipart = new MimeMultipart();
        if (files.length > 0) {
            // 设置附件
            addFiles(multipart, files);
            message.setContent(multipart);
        }
        addContent(multipart, content);
        // 发送
        Transport.send(message);
    }

    private void addContent(Multipart multipart, String content) throws Exception {
        BodyPart contentBodyPart = new MimeBodyPart();
        contentBodyPart.setText(content);
        //contentBodyPart.setHeader("Content-Type", "text/html;charset=UTF-8");
        multipart.addBodyPart(contentBodyPart);

    }

    private void addFiles(Multipart multipart, String... files) throws Exception {
        for (String file : files) {
            File attempFile = new File(file);
            MimeBodyPart fileBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            fileBodyPart.setDataHandler(new DataHandler(source));
            fileBodyPart.setFileName(attempFile.getName());
            multipart.addBodyPart(fileBodyPart);
        }
    }

    /**
     * 群发邮件
     *
     * @param recipients 收件人们
     * @param subject    主题
     * @param content    内容
     * @throws AddressException
     * @throws MessagingException
     */
    public void send(List<String> recipients, String subject, Object content)
            throws AddressException, MessagingException {
        // 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);
        // 设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        // 设置收件人们
        final int num = recipients.size();
        InternetAddress[] addresses = new InternetAddress[num];
        for (int i = 0; i < num; i++) {
            addresses[i] = new InternetAddress(recipients.get(i));
        }
        message.setRecipients(MimeMessage.RecipientType.TO, addresses);
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
        // 发送
        Transport.send(message);
    }
}
