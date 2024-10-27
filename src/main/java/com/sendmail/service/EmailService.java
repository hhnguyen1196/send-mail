package com.sendmail.service;

import com.sendmail.request.SendMailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender mailSender;

    private TemplateEngine templateEngine;

    public void sendEmail(SendMailRequest request, String template, Context context) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            String htmlContent = templateEngine.process(template, context);
            helper.setTo(request.getTo());
            helper.setCc(request.getCc());
            helper.setSubject(request.getSubject());
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
