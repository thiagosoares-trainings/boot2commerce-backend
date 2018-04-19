package com.nelioalves.cursomc.dto;

import java.io.Serializable;
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
  private String nome;
  
  public CategoriaDto(Categoria cat) {
    
    this.id = cat.getId();
    this.nome = cat.getNome();
    
  }
}
