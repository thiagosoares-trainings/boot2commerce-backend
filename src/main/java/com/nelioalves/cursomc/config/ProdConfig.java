package com.nelioalves.cursomc.config;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.nelioalves.cursomc.services.mail.EmailService;
import com.nelioalves.cursomc.services.mail.MockEmailService;
import com.nelioalves.cursomc.services.mail.SmtpEmailService;
import com.nelioalves.cursomc.services.test.InitDBService;

@Configuration
@Profile("prod")
public class ProdConfig {

  @Autowired
  private InitDBService dbService;
  
  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String ddlauto;
  
  @Bean
  public boolean instaciateDatabase() throws ParseException {
    
    if(ddlauto != null && (ddlauto.equals("create") || ddlauto.equals("create-drop") )) {
      dbService.initializeTestDataBase();
    }
    
    return true;
  }
  
  @Bean
  public EmailService emailService() {
    //return new MockEmailService();
    return new SmtpEmailService(); 
  }
  
}
