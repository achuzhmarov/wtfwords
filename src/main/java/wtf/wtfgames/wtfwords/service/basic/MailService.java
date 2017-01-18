package wtf.wtfgames.wtfwords.service.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    @Autowired
    JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();

        msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        //msg.addFrom(new InternetAddress[] { new InternetAddress(adminEmail) });
        msg.setSubject(subject, "UTF-8");
        msg.setText(text, "UTF-8");
        mailSender.send(msg);
    }
}
