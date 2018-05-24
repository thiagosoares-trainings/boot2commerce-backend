package com.nelioalves.cursomc.petstar.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto implements Serializable{

  private static final long serialVersionUID = 4453718274644135700L;

  private PetDto mainPet;
  private List<EventDto> events;
  
}
