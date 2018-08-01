package com.dayi.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件发送工具类
 *
 * @author HuangDongYang<huangdy @ pvc123.com>
 * @date 2018/6/30
 */
@Repository
public class EmailUtil {

    private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    //设置发送人
    private final String EMAIL_FROM = "huangdy@pvc123.com";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage simpleMailMessage;

    /**
     * 发送简单的文字邮件
     * @param subject 主题
     * @param content 正文
     * @param toMail 收件人邮箱
     */
    public void sendSimpleMail(String subject, String content, String toMail){
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setTo(toMail);
        mailSender.send(simpleMailMessage);
        logger.info("邮件发送至：" + toMail);
        logger.info("发送邮件成功");
    }

    /**
     * 发送HTML邮件
     * @param subject 主题
     * @param content 允许HTML标签的文本
     * @param toMail 邮箱
     */
    public void sendHtmlMail(String subject,String content,String toMail) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
        try {
            messageHelper.setTo(toMail);
            messageHelper.setSubject(subject);
            messageHelper.setFrom(EMAIL_FROM);
            messageHelper.setText("<html><head></head><body><h1>"+content+"</h1></body></html>",true);
            mailSender.send(mailMessage);
            logger.info("邮件发送至：" + toMail);
            logger.info("邮件发送成功..");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送HTML邮件(多收件人)
     * @param subject 主题
     * @param content 允许HTML标签的文本
     * @param toMails 多个邮箱用逗号隔开
     */
    public void sendHtmlMails(String subject,String content,String toMails) {

        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
        try {
            //解析以“，”号隔开的邮箱地址字符串
            String[] mails = toMails.split(",");
            InternetAddress[] address = new InternetAddress[mails.length];
            for (int i = 0; i < address.length; i++){
                address[i] = new InternetAddress(mails[i]);
            }

            //messageHelper.setTo(toMail);
            mailMessage.setRecipients(Message.RecipientType.TO, address);
            messageHelper.setSubject(subject);
            messageHelper.setFrom(EMAIL_FROM);
            messageHelper.setText("<html><head></head><body><h1>"+content+"</h1></body></html>",true);
            mailSender.send(mailMessage);
            logger.info("邮件发送至：" + toMails);
            logger.info("邮件发送成功..");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Description: 带图片发邮件形式
     * @param subject 主题
     * @param content 正文
     * @param toMails 多个收件人邮箱用逗号隔开
     * @param picturePath 图片路径
     */
    public void sendPictureMails(String subject, String content, String toMails, String picturePath){
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            //解析以“，”号隔开的邮箱地址字符串
            String[] mails = toMails.split(",");
            InternetAddress[] address = new InternetAddress[mails.length];
            for (int i = 0; i < address.length; i++){
                address[i] = new InternetAddress(mails[i]);
            }

            messageHelper = new MimeMessageHelper(mailMessage,true);
            messageHelper.setFrom(EMAIL_FROM);
            //设置多个收件人
            mailMessage.setRecipients(Message.RecipientType.TO, address);
            messageHelper.setSubject(subject);
            messageHelper.setText("<html><head></head><body><h1 style='font-size:1.5rem;'>" + content +"</h1><img style='margin-top:10px;margin-left:0;' src='cid:imgPic' /></body></html>", true);
            FileSystemResource img = new FileSystemResource(new File(picturePath));
            messageHelper.addInline("imgPic", img);
            //发送邮件
            mailSender.send(mailMessage);
            logger.info("邮件发送至：" + toMails);
            logger.info("图片邮件发送成功..");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Description:带附件发邮件形式
     * @param subject 标题
     * @param content 正文
     * @param toMail 收件人邮箱
     * @param accessoryPath 附件路径
     * @param accessoryName 附件名
     */
    public void sendMailTakeAccessory(String subject, String content, String toMail, String accessoryPath, String accessoryName){
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mailMessage,true,"utf-8");
            messageHelper.setTo(toMail);
            messageHelper.setFrom(EMAIL_FROM);
            messageHelper.setSubject(subject);
            messageHelper.setText("<html><head></head><body><h1>" + content + "</h1></body></html>",true);
            FileSystemResource file = new FileSystemResource(new File(accessoryPath));
            messageHelper.addAttachment(accessoryName, file);
            mailSender.send(mailMessage);
            logger.info("邮件发送至：" + toMail);
            logger.info("附件邮件发送成功..");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}
