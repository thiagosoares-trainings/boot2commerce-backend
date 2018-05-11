package com.nelioalves.cursomc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.validator.ClienteInsert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ClienteInsert
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
  
  @NotEmpty(message = "Ops! O nome não pode ser nulo! ")
  private String cpfOuCnpj;
  
  @NotNull(message = "Ops! O nome não pode ser nulo! ")
  private Integer tipo;
  
  //Endereco
  @NotEmpty(message = "Ops! O nome não pode ser nulo! ")
  private String logradouro;
  
  @NotEmpty(message = "Ops! O nome não pode ser nulo! ")
  private String numero;
  
  private String complemento;
  private String bairro;
  private String cep;
  
  @NotNull(message = "Ops! A cidade não pode ser nulo! ")
  private Integer cidadeId;
  
  //Telefone
  @NotEmpty(message = "Ops! O Telefone principal não pode ser nulo! ")
  private String telefone1;
  private String telefone2;
  private String telefone3;
  
  @NotEmpty(message = "Ops! A senha não pode ser nulo! ")
  private String senha;
  
}
