package coachingmateanalytics.coachingmate.service;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import coachingmateanalytics.coachingmate.common.utils.Consts;
import coachingmateanalytics.coachingmate.dao.UserDao;
import coachingmateanalytics.coachingmate.entity.UserPartner;
import lombok.extern.slf4j.Slf4j;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import com.google.api.services.gmail.Gmail;
//import com.google.api.services.gmail.model.Draft;
//import com.google.api.services.gmail.model.Message;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

import java.util.Date;
//
//@Service("emailService")
//@Slf4j
//
//
//    @Value("${reset.password.email.address}")
//    private String fromEmailAddress;
//    @Value("${reset.password.email.password}")
//    private String password;
//
//
//    @Async("emailPoolTaskExecutor")
//    public void SendEmail(String toEmailAddress) {
//        try{
//            String host = "smtp.gmail.com";
//
//            // Get system properties
//            Properties properties = System.getProperties();
//
//            // Setup mail server
//            properties.put("mail.smtp.host", host);
//            properties.put("mail.smtp.port", "465");
//            properties.put("mail.smtp.ssl.enable", "true");
//            properties.put("mail.smtp.auth", "true");
//
//            // Get the Session object.// and pass username and password
//            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//
//                protected PasswordAuthentication getPasswordAuthentication() {
//
//                    return new PasswordAuthentication(fromEmailAddress, password);
//
//                }
//
//            });
//
//            // Used to debug SMTP issues
//            session.setDebug(true);
//
//            Thread.sleep(3000);
//            log.info("User " + toEmailAddress + " send success");
//        } catch (InterruptedException e) {
//            System.out.println(e);
//        }
//    }
//
//
//    public static MimeMessage createEmail(String to,
//                                          String from,
//                                          String subject,
//                                          String bodyText)
//            throws MessagingException {
//        Properties props = new Properties();
//        Session session = Session.getDefaultInstance(props, null);
//
//        MimeMessage email = new MimeMessage(session);
//
//        email.setFrom(new InternetAddress(from));
//        email.addRecipient(javax.mail.Message.RecipientType.TO,
//                new InternetAddress(to));
//        email.setSubject(subject);
//        email.setText(bodyText);
//        return email;
//    }
//
//    public static Message createMessageWithEmail(MimeMessage emailContent)
//            throws MessagingException, IOException {
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        emailContent.writeTo(buffer);
//        byte[] bytes = buffer.toByteArray();
//        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
//        Message message = createEmail(
//                "575628304@qq.com",
//                "menglingjun96@gmial.com",
//                "Boxjelly Reset Password",
//                "Hello Gmail");
////        message.setRaw(encodedEmail);
//        return message;
//    }
@Component
public class SendEmail {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    MongoTemplate mongoTemplate;

    public void sendVerEmail(String toEmail) throws UnsupportedEncodingException {
        String from = "g.lee@yqfqzmy.monster";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmail);
        message.setSubject("CoachingMate: Verification Code");
        String verCode = Vercode.generateVerCode();
        VercodeTime.verCodeMap.put(toEmail,verCode);
        System.out.println(verCode);
        VercodeTime.currentTimeMap.put(toEmail,new Date().getTime());
//        String url = Consts.FRONT_URL+"verifyEmail/verify/?";
//        String para = "code="+verCode+"&email="+toEmail;
//        String encodedQuery = URLEncoder.encode(para, StandardCharsets.UTF_8.toString());
//        String fullurl = url+encodedQuery;
        message.setText("Please verify your email address:\n"
                + "\nTo continue setting up your CoachingMate account, please verify that this is your email address. Use the code below to continue:\n" + verCode + "\nThe link expires in 5 minutes. If you did not make this request, please disregard this email.\n"
                + "\n(This is an auto-generated email, please do not reply.)");

        mailSender.send(message);
    }
    public void sendResetEmail(String toEmail) throws UnsupportedEncodingException {
        String from = "g.lee@yqfqzmy.monster";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(toEmail);
        message.setSubject("CoachingMate: Reset Password");
        String codeString = Vercode.generateString();
        VercodeTime.stringCodeMap.put(toEmail,codeString);
        System.out.println(codeString);
        VercodeTime.currentTimeMapString.put(toEmail, new Date().getTime());

        String url = "https://ga-box-jelly-ddbh.vercel.app/requestReset/reset/?";
        String para = "email="+toEmail+"&security="+codeString;
        String encodedQuery = Base64.getEncoder().encodeToString(para.getBytes());
//        String encodedQuery = URLEncoder.encode(para, StandardCharsets.UTF_8.toString());
        String fullurl = url+encodedQuery+"#/reset";

        message.setText("You've requested to reset your password:\n"
                + "\nTo continue please click this link:\n"+ fullurl
                + "\nIf you did not make this request, please disregard this email.\n"+"\n(This is an auto-generated email, please do not reply.)");

        mailSender.send(message);
    }




}