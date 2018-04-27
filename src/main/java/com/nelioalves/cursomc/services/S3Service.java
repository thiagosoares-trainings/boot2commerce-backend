package com.nelioalves.cursomc.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.nelioalves.cursomc.services.exceptions.FileException;

@Service
public class S3Service {

  private Logger LOG = LoggerFactory.getLogger(S3Service.class);

  @Autowired
  private AmazonS3 s3client;

  @Value("${s3.bucket}")
  private String bucketName;

  public URI uploadFile(MultipartFile multipartFile) {
    try {
      String fileName = multipartFile.getOriginalFilename();
      InputStream is = multipartFile.getInputStream();
      String contentType = multipartFile.getContentType();
      return uploadFile(is, fileName, contentType);
    } catch (IOException e) {
      throw new FileException("Erro de IO: " + e.getMessage());
    }
  }

  public URI uploadFile(InputStream is, String fileName, String contentType) {
    try {
      ObjectMetadata meta = new ObjectMetadata();
      meta.setContentType(contentType);
      LOG.info("Iniciando upload");
      s3client.putObject(bucketName, fileName, is, meta);
      LOG.info("Upload finalizado");
      return s3client.getUrl(bucketName, fileName).toURI();
    } catch (URISyntaxException e) {
      LOG.info("Erro ao converter URL para URI");
      throw new FileException("Erro ao converter URL para URI");
    } catch (AmazonServiceException e) {
      throw new FileException("Erro ao consumir o servico da Amazon." + e.getMessage());
    } catch (AmazonClientException e) {
      throw new FileException("Erro no Cliente do servico da Amazon." + e.getMessage());
    }
  }
  
  public void uploadFileTest(String filePath, String fileName) {
    try {
      
      File file = new File(filePath);
      
      LOG.info("Iniciando upload");
      s3client.putObject(bucketName, fileName, file);
      LOG.info("Upload finalizado");
      
    } catch (AmazonServiceException e) {
      e.printStackTrace();
      throw new FileException("Erro ao consumir o servico da Amazon." + e.getMessage());
    } catch (AmazonClientException e) {
      e.printStackTrace();
      throw new FileException("Erro no Cliente do servico da Amazon." + e.getMessage());
    }
  }
}
