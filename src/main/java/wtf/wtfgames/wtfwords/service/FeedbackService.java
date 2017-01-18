package wtf.wtfgames.wtfwords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wtf.wtfgames.wtfwords.service.basic.MailService;

@Service
public class FeedbackService {
    @Autowired
    MailService mailService;

    @Value("${smtp.support.email}")
    private String supportEmail;

    public void sendFeedback(String fromId, String fromEmail, String text) {
        String subject = "IOS FeedbackRequest from ";

        if (fromEmail != null && fromEmail.length() > 0) {
            subject += fromEmail + ", ";
        }

        subject += fromId;

        mailService.sendEmail(supportEmail, subject, text);
    }
}
