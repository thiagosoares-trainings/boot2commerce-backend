package com.nelioalves.cursomc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.validator.ClienteUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ClienteUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

  private Integer id;
  
  @NotEmpty(message = "Ops! O nome não pode ser nulo! ")
  @Length(max = 100, min = 5, message = "Ops! O tamanho deve estar entre {min} e {max} caracteres")
  private String nome;
  
  @Email(message = "Email inválido")
  @NotEmpty(message = "Ops! O email não pode ser nulo! ")
  @Length(max = 100, min = 5, message = "Ops! O tamanho deve estar entre {min} e {max} caracteres")
  private String email;

  public ClienteDto(Cliente obj) {
    this.id = obj.getId();
    this.nome = obj.getNome();
    this.email = obj.getEmail();
  }
}
