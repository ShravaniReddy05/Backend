package com.certificate.learning.digitalCertificate.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtilImpl implements EmailUtil{

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendEmailWithAttachment(String toAddress, String subject, String body, String attachment ) {
        MimeMessage message =sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("telstramgt@gmail.com");
            helper.setTo(toAddress);
            helper.setText(body);
            helper.setSubject(subject);
            FileSystemResource fileSystemResource= new FileSystemResource(new File(attachment));
            helper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        sender.send(message);
    }

    @Override
    public void sendEmail(String toAddress, String subject, String body) {
        //for simplemailmessage
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("telstramgt@gmail.com");
        message.setTo(toAddress);
        message.setText(body);
        message.setSubject(subject);
        sender.send(message);

    }
	/*@Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String toEmail, String subject,String body,String attachment)
    {
    	MimeMessage mimeMessage=javaMailSender.createMimeMessage();
    	 try {
    	MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
    	helper.setFrom("telstramgt@gmail.com");
    	helper.setTo(toEmail);
    	helper.setText(body);
    	helper.setSubject(subject);
    	
    	FileSystemResource fileSystemResource= new FileSystemResource(new File(attachment));
    	helper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
    	 }
    	 catch (MessagingException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
    	 
    	javaMailSender.send(mimeMessage);
    	
    }
*/
}
