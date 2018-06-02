package com.nelioalves.cursomc.services.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Value("${spring.mail.username}")
    private String senderName;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando email..." + senderName);
		mailSender.send(msg);
		LOG.info("Email enviado");
	}
}
