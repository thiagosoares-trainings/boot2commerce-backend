package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.nelioalves.cursomc.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto implements Serializable {

  private static final long serialVersionUID = -1205198323279289259L;
  
  private Integer id;
  
  @NotEmpty(message = "Ops! O nome não pode ser nulo! ")
  @Length(max = 100, min = 5, message = "Ops! O tamanho deve estar entre {min} e {max} caracteres")
  private String nome;
  
  public CategoriaDto(Categoria cat) {
    
    this.id = cat.getId();
    this.nome = cat.getNome();
    
  }
}
