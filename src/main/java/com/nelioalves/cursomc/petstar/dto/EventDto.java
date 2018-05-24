package com.nelioalves.cursomc.petstar.dto;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto implements Serializable{

  private static final long serialVersionUID = -4919859822243509802L;
  
  private Integer id;
  private Integer petId;
  private String name;
  private String description;
  private String place;
  private Integer placeId;
  
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date date;
  
  private Double price;
  
  private String imageId;
  
}
