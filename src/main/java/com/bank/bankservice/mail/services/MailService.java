package com.bank.bankservice.mail.services;

import com.bank.bankservice.common.dtos.Response.AddCustomerResponse;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final String fromEmail;

    @Autowired
    private Configuration freemarkerConfig;

    public MailService(JavaMailSender mailSender, @Value("${app.mail.from}") String fromEmail) {
        this.mailSender = mailSender;
        this.fromEmail = fromEmail;
    }

    public void sendLoginPasswordMail(String to, String subject, AddCustomerResponse response) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("clientName", response.getFirstName() + " " + response.getLastName());
        model.put("username", response.getUsername());
        model.put("tempPassword", response.getPassword());

        String toAddress = to;
        String fromAddress = fromEmail;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("Login.ftlh"), model), true);

        mailSender.send(message);
    }
}
