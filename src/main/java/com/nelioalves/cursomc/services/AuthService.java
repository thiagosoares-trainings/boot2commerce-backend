package com.nelioalves.cursomc.services;

import java.util.Random;
import org.hibernate.validator.cfg.defs.RangeDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import com.nelioalves.cursomc.services.mail.EmailService;

@Service
public class AuthService {

  
  @Autowired
  private ClienteRepository clienteRepository;
  
  @Autowired
  private BCryptPasswordEncoder pe;
  
  @Autowired
  private EmailService emailService;
  
  private Random random = new Random();
  
  public void sendNewPassword(String email) {
    
    Cliente cliente = clienteRepository.findByEmail(email);
    
    if(cliente == null) {
      throw new ObjectNotFoundException("Cliente not found");
    }
    
    String newPass = newPassword();
    
    cliente.setSenha(pe.encode(newPass));
    
    clienteRepository.save(cliente);
    
    //TODO Enviar o email
    emailService.sendNewPasswordEmail(cliente, newPass);
    
  }

  private String newPassword() {
    
    char[] vet = new char[10];
    for (int i = 0; i < vet.length; i++) {
      vet[i] = randanChar();
    }
    
    return new String(vet);
  }

  private char randanChar() {
    
    int opt = random.nextInt(3);
    if(opt == 0) {
      return (char) (random.nextInt(10) + 48);
    } else if(opt == 1) {
      return (char) (random.nextInt(26) + 65);
    } else {
      return (char) (random.nextInt(26) + 97);
    }
  }
  
}
