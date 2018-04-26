package com.nelioalves.cursomc.resources;

import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.dto.CategoriaDto;
import com.nelioalves.cursomc.dto.ClienteDto;
import com.nelioalves.cursomc.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

  @Autowired
  private PedidoService service;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<Pedido> find(@PathVariable Integer id) {
    Pedido obj = service.find(id);
    return ResponseEntity.ok().body(obj);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {

    Pedido obj = service.insert(pedido);
    
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();

  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<Page<Pedido>> findAllPagged(@RequestParam(name = "page", defaultValue = "0") Integer page, 
                                                    @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
                                                    @RequestParam(name = "orderBy", defaultValue = "instante") String orderBy, 
                                                    @RequestParam(name = "direction", defaultValue = "DESC") String direction) {
    
    Page<Pedido> pageList = service.findPaged(page, linesPerPage, orderBy, direction);
    return ResponseEntity.ok().body(pageList);
  }
  
  
}
