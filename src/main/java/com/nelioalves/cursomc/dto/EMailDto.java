package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import com.nelioalves.cursomc.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EMailDto implements Serializable{

  private static final long serialVersionUID = -4919859822243509802L;
  
  private String email;
  
}
