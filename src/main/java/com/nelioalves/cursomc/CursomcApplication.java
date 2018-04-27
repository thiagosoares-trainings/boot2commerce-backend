package com.nelioalves.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.nelioalves.cursomc.services.S3Service;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
 
  @Autowired
  private S3Service s3Service; 
  
  public static void main(String[] args) {
    SpringApplication.run(CursomcApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("The App is upping ...");
    
    s3Service.uploadFileTest("/home/thiago/Workspaces/Prodepa/Crypto/Criptor-1.0-master/codigo_fonte/java/src/test/resources/avatars/barackobama-3-1100K.jpg", "Teste.jpg");
    
    
  }

}
