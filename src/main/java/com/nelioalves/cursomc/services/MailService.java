package com.nelioalves.cursomc.services;

import org.springframework.mail.SimpleMailMessage;
import com.nelioalves.cursomc.domain.Pedido;

public interface MailService {

  public void sendOrderConfirmatioMail(Pedido pedido);
  
  public void sendMail(SimpleMailMessage mgs);
}
