package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository repo;
  
  @Autowired
  private CategoriaRepository categoriaRepo;

  public Produto find(Integer id) {
    Optional<Produto> obj = repo.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto " + id + " não encontrado"));
  }

  public Page<Produto> search(String nome, List<Integer> catIds, Integer page, Integer linesPerPage, String orderBy, String direction) {

    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

    List<Categoria> categorias = categoriaRepo.findAllById(catIds);
    
    //return repo.search(nome, categorias, pageRequest);
    
    return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
  }

}
