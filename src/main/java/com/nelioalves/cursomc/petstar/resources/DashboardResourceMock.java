package com.nelioalves.cursomc.petstar.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.dto.ProdutoDto;
import com.nelioalves.cursomc.petstar.dto.DashboardDto;
import com.nelioalves.cursomc.petstar.dto.EventDto;
import com.nelioalves.cursomc.petstar.dto.PetDto;
import com.nelioalves.cursomc.services.ProdutoService;
import com.nelioalves.cursomc.utils.URL;

@RestController
@RequestMapping(value = "/dashboard")
public class DashboardResourceMock {

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<DashboardDto> find() throws ParseException {
    
    PetDto mainPet = new PetDto(1, "Snoopy", "Poodle", "1 ano e 1 mês", "snoopy.jpg");
    
    Calendar c1 = Calendar.getInstance();
    c1.add(Calendar.DAY_OF_MONTH, 2);
    
    List<EventDto> events = new ArrayList<>();
    events.add(new EventDto(1, 1, "Banho", "Banho e Tosa no Jack PetShop", "Jack PetShop", 1, 
                            c1.getTime(), 100.00, "default_note_image.png"));
    
    events.add(new EventDto(2, 1, "Vacina", "Vacinha contra Raiva no Jack PetShop", "Jack PetShop", 1, 
                            c1.getTime(), 100.00, "default_note_image.png"));
    
    DashboardDto obj = new DashboardDto(mainPet, events);
    
    return ResponseEntity.ok().body(obj);
  }

}
