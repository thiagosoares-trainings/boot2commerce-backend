package com.blackdog.bootcommerce.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.blackdog.bootcommerce.exception.ObjectNotFoundException;
import com.blackdog.bootcommerce.model.Categoria;
import com.blackdog.bootcommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	
	private CategoriaRepository dao;
	
	public CategoriaService(CategoriaRepository dao) {
		super();
		this.dao = dao;
	}

	public Categoria findById(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> categoria = dao.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto " +id+ " não encontrado"));
	}
	
}
