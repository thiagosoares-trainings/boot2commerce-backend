package com.nelioalves.cursomc.resources;

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
import com.nelioalves.cursomc.services.ProdutoService;
import com.nelioalves.cursomc.utils.URL;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

  @Autowired
  private ProdutoService service;

  
  @RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
  
  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<Page<ProdutoDto>> findAllPagged(
      @RequestParam(name = "nome", defaultValue = "") String nome,
      @RequestParam(name = "categorias", defaultValue = "") String categorias,
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
      @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
      @RequestParam(name = "direction", defaultValue = "ASC") String direction) {

    List<Integer> catIds = URL.decodeIntList(categorias);
    
    nome = URL.decodeParam(nome);
   
    Page<Produto> pageList = service.search(nome, catIds, page, linesPerPage, orderBy, direction);
    Page<ProdutoDto> pageDto = pageList.map(cat -> new ProdutoDto(cat));
    
    return ResponseEntity.ok().body(pageDto);
  }
}
