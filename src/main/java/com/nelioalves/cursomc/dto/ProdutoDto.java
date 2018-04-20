package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import com.nelioalves.cursomc.domain.Produto;
import lombok.Data;

@Data
public class ProdutoDto implements Serializable{

  private static final long serialVersionUID = -4919859822243509802L;
  
  private Integer id;
  private String nome;
  private Double preco;
  
  public ProdutoDto(Produto p) {
    super();
    this.id = p.getId();
    this.nome = p.getNome();
    this.preco = p.getPreco();
  }
}
