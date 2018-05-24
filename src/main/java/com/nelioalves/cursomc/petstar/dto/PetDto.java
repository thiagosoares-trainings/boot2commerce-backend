package com.nelioalves.cursomc.petstar.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDto implements Serializable{

  private static final long serialVersionUID = 7672683213270945317L;

  private Integer id;
  private String name;
  private String species;
  private String age;
  
  private String imageId;
  
  
}
