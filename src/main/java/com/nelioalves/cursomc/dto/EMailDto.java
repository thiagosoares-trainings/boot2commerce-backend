package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EMailDto implements Serializable{

  private static final long serialVersionUID = -4919859822243509802L;
  
  @NotEmpty(message="Preenchimento obrigatório")
  @Email(message="Email inválido")
  private String email;
  
}
