package com.nelioalves.cursomc.config;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.nelioalves.cursomc.services.test.InitDBService;

@Configuration
@Profile("test")
public class TestConfig {

  
  @Autowired
  private InitDBService dbService;
  
  
  @Bean
  public boolean instaciateDatabase() throws ParseException {
    
    dbService.initializeTestDataBase();
    
    return true;
  }
  
}
