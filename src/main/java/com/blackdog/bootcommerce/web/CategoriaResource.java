package com.blackdog.bootcommerce.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blackdog.bootcommerce.exception.ObjectNotFoundException;
import com.blackdog.bootcommerce.model.Categoria;
import com.blackdog.bootcommerce.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	private CategoriaService service;
	
	public CategoriaResource(CategoriaService service) {
		super();
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> findAll() {
		
		List<Categoria> list = new ArrayList<>();
		
		list.add(new Categoria(1, "Carros", null));
		
		return list; 
	}
	

	@RequestMapping(value = "/{id}",  method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) throws ObjectNotFoundException {
		Categoria cat = service.findById(id);
		return ResponseEntity.ok(cat); 
	}

}
