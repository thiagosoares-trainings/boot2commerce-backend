package com.nelioalves.cursomc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.nelioalves.cursomc.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteNewDto {

  private Integer id;
  
  @NotEmpty(message = "Ops! O nome não pode ser nulo! ")
  @Length(max = 100, min = 5, message = "Ops! O tamanho deve estar entre {min} e {max} caracteres")
  private String nome;
  
  @Email(message = "Email inválido")
  @NotEmpty(message = "Ops! O email não pode ser nulo! ")
  @Length(max = 100, min = 5, message = "Ops! O tamanho deve estar entre {min} e {max} caracteres")
  private String email;
  
  private String cpfOuCnpj;
  private Integer tipo;
  
  
  //Endereco
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cep;
  private Integer cidadeId;
  
  
  //Telefone
  private String telefone1;
  private String telefone2;
  private String telefone3;
  
}
